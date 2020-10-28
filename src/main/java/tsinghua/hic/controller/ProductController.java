package tsinghua.hic.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.asm.Advice.This;
import tsinghua.hic.commons.GenerateQRCode;
import tsinghua.hic.pojo.dto.InfoTypeEnum;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Productinfo;
import tsinghua.hic.pojo.po.Productinfohash;
import tsinghua.hic.service.HashGenerateService;
import tsinghua.hic.service.ProductService;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(This.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private HashGenerateService hashGenerateService;

    @ModelAttribute("infoTypes")
    public List<InfoTypeEnum> populateTypes() {
        return Arrays.asList(InfoTypeEnum.values());
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        Iterable<Product> productList = productService.findall();
        model.addAttribute("list", productList);
        Product product = new Product();

        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        product.setCreateTime(formatter.format(now));
        product.setUpdateTime(formatter.format(now));
        model.addAttribute("product", product);
        return new ModelAndView("list", model);
    }

    @PostMapping("/addproduct")
    public ModelAndView addproductpost(ModelMap model, Product product) {
        try {
            if (product.getPicurl() == null || product.getPicurl().isEmpty()) {
                product.setPicurl("default.jpg");
            }
            if (product.getProductDesc().strip().isEmpty()
                    || product.getProductName().strip().isEmpty()) {
                log.error("产品名称或者描述不能为空");
            } else {
                product = productService.add(product);
                GenerateQRCode generateQRCode = new GenerateQRCode();
                generateQRCode.QREncode(300, 300,
                        "http://" + InetAddress.getLocalHost().getHostAddress()
                                + ":7777/scan/" + product.getGid(),
                        product.getGid());
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/list");
    }

    @GetMapping("/generate/{gid}")
    public ModelAndView generate(@PathVariable String gid) {
        try {
            hashGenerateService.generate(gid);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/list");
    }

    @GetMapping(value = "/qr/{gid}")
    @ResponseBody
    public String getQRCode(@PathVariable(required = true) String gid) {
        GenerateQRCode generateQRCode = new GenerateQRCode();
        try {
            String imgSrc = generateQRCode.QREncode(300, 300,
                    "http://" + InetAddress.getLocalHost().getHostAddress()
                            + ":7777/scan/" + gid,
                    gid);
            return imgSrc;
        } catch (Exception e) {
            log.error("gen failed" + e.getLocalizedMessage());
            return null;
        }
    }

    @GetMapping("/demo")
    public ModelAndView demo() {
        return new ModelAndView("demo");
    }

    @GetMapping("/scan/{gid}")
    public ModelAndView scan(@PathVariable(required = true) String gid,
            ModelMap model) {
        model.addAttribute("gid", gid);
        return new ModelAndView("scan", model);
    }

    @GetMapping("/producer/{gid}")
    public ModelAndView producer(@PathVariable(required = true) String gid,
            ModelMap model) {
        Productinfo productinfo = new Productinfo();
        productinfo.setGid(gid);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        productinfo.setCreateTime(formatter.format(now));
        productinfo.setUpdateTime(formatter.format(now));
        productinfo.setLoc("深圳");
        productinfo.setInfoType((short) InfoTypeEnum.Producer.getTypeIndex());
        model.addAttribute("productinfo", productinfo);
        return new ModelAndView("addproductinfo", model);
    }

    @GetMapping("/express/{gid}")
    public ModelAndView express(@PathVariable(required = true) String gid,
            ModelMap model) {
        Productinfo productinfo = new Productinfo();
        productinfo.setGid(gid);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        productinfo.setCreateTime(formatter.format(now));
        productinfo.setUpdateTime(formatter.format(now));
        productinfo.setLoc("深圳");
        productinfo.setInfoType((short) InfoTypeEnum.Express.getTypeIndex());
        model.addAttribute("productinfo", productinfo);
        return new ModelAndView("addproductinfo", model);
    }

    @GetMapping("/transaction/{gid}")
    public ModelAndView transaction(@PathVariable(required = true) String gid,
            ModelMap model) {
        Productinfo productinfo = new Productinfo();
        productinfo.setGid(gid);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        productinfo.setCreateTime(formatter.format(now));
        productinfo.setUpdateTime(formatter.format(now));
        productinfo.setLoc("深圳");
        productinfo
                .setInfoType((short) InfoTypeEnum.Transaction.getTypeIndex());
        model.addAttribute("productinfo", productinfo);
        return new ModelAndView("addproductinfo", model);
    }

    @PostMapping("/addproductinfo")
    public ModelAndView addproductinfo(ModelMap model,
            Productinfo productinfo) {
        try {
            if (productinfo.getContent().strip().isEmpty()) {
                throw new Exception("产品信息内容不能为空");
            } else {
                productService.add(productinfo);
                hashGenerateService.generate(productinfo.getProductinfoid());
                model.addAttribute("result", "添加成功");
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            model.addAttribute("result", "添加失败");
        }

        return new ModelAndView("result", model);
    }

    @GetMapping("/verifyuser/{gid}")
    public ModelAndView verifyuser(@PathVariable(required = true) String gid,
            ModelMap model) {
        try {
            Productinfo productinfo = new Productinfo();
            productinfo.setGid(gid);
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            productinfo.setCreateTime(formatter.format(now));
            productinfo.setUpdateTime(formatter.format(now));
            productinfo.setLoc("深圳");
            productinfo.setInfoType(
                    (short) InfoTypeEnum.VerifyUser.getTypeIndex());
            productinfo.setContent("用户扫码验证");
            productinfo.setVerifyuserName("扫码用户");
            productinfo.setVerifyuserDesc("收到东西扫码看看");
            productService.add(productinfo);
            hashGenerateService.generate(productinfo.getProductinfoid());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }

        Product product = productService.get(gid).get();
        List<Productinfo> productinfos = productService
                .findProductinfoByGid(gid);

        model.addAttribute("product", product);
        model.addAttribute("productinfos", productinfos);
        return new ModelAndView("verifyuser", model);
    }

    @GetMapping(value = "/hash/{productinfoid}")
    @ResponseBody
    public String getHash(@PathVariable(required = true) String productinfoid) {
        Optional<Productinfohash> productinfohashOptional = productService
                .gethash(productinfoid);
        if (productinfohashOptional.isEmpty()) {
            return "不存在";
        } else {
            return productinfohashOptional.get().getHash();
        }
    }
}
