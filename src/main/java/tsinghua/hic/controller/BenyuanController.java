package tsinghua.hic.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.asm.Advice.This;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.service.ProductService;

@RestController
public class BenyuanController {
    private static final Logger log = LoggerFactory.getLogger(This.class);

    @Autowired
    private ProductService productService;

    @Value("${hic.url}")
    private String hicUrl;

    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseEntity<String> upload(MultipartFile file) {
        try {
            String filenameString = ResourceUtils.getURL("").getPath()
                    + System.currentTimeMillis() + file.getOriginalFilename();
            BufferedInputStream in = new BufferedInputStream(
                    file.getInputStream());
            BufferedImage bi = ImageIO.read(in);
            int height = bi.getHeight();
            int width = bi.getWidth();
            int newheight = 960 * width / height;
            log.info(filenameString);
            log.info("height" + height + "width" + width);
            BufferedImage tag = new BufferedImage(newheight, 960,
                    BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(bi, 0, 0, newheight, 960, null);

            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(filenameString));
            ImageIO.write(tag, "PNG", out);
            in.close();
            out.close();
            File tempFile = new File(filenameString);
            FileSystemResource resource = new FileSystemResource(tempFile);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setCacheControl("no-cache");
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(
                    param, headers);
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate
                    .postForEntity(hicUrl, files, String.class).getBody();

            JSONObject jsonObject = new JSONObject(result);
            Integer codInteger = jsonObject.getInt("code");
//            String msgString = jsonObject.getString("msg");
            String dataString = jsonObject.getString("data");
            if (codInteger == 0) {
                Optional<Product> product = productService.get(dataString);
                Date nowDate = new Date();
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                if (product.isPresent()) {
                    Product actualProduct = product.get();
                    if (actualProduct.getFirstvalidateTime() == null
                            || actualProduct.getFirstvalidateTime().trim()
                                    .isEmpty()) {
                        actualProduct
                                .setFirstvalidateTime(format.format(nowDate));
                        actualProduct.setValidateCount(0);
                    }
                    actualProduct.setValidateCount(
                            actualProduct.getValidateCount() + 1);
                    actualProduct = productService.update(actualProduct);

                    if (actualProduct.getProductName().contains("美贺庄园")) {
                        byte[] bytearray = actualProduct.getId()
                                .getBytes("utf8");
                        Integer newid = bytearray[0] * 65536
                                + bytearray[1] * 256 | bytearray[2];
                        jsonObject.put("id", actualProduct.getId());
                        jsonObject.put("successcontent",
                                actualProduct.getProductName() + "\n"
                                        + newid.toString() + "\n"
                                        + actualProduct.getProductDesc());
                        jsonObject.put("jiuzhuang", true);
                    } else if (actualProduct.getProductName().contains("矿泉水")) {
                        jsonObject.put("number",
                                actualProduct.getProductDesc());
                        jsonObject.put("water", true);
                    } else if (actualProduct.getProductName().contains("眼影盒")) {
                        jsonObject.put("number",
                                actualProduct.getProductDesc());
                        jsonObject.put("box", true);
                    } else {
                        if (actualProduct.getProductName().contains("展览图片")) {
                            jsonObject.put("exhibition", true);
                        }
                        jsonObject.put("id", actualProduct.getId());
                        jsonObject.put("successcontent",
                                actualProduct.getProductName() + "\n"
                                        + actualProduct.getId() + "\n"
                                        + actualProduct.getCreateTime() + "\n"
                                        + actualProduct.getExpireTime());
                        jsonObject.put("bottomtime",
                                actualProduct.getFirstvalidateTime() + "\n"
                                        + format.format(nowDate));
                        jsonObject.put("bottomcount",
                                actualProduct.getValidateCount() + "次");
                    }
                } else {
                    jsonObject.put("notexist", true);
                }
            } else {
                jsonObject.put("picinvalid", true);
            }
//            tempFile.delete();
            log.info(jsonObject.toString());
            return new ResponseEntity<String>(jsonObject.toString(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
