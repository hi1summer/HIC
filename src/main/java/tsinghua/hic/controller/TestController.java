/**
 *
 */
package tsinghua.hic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import tsinghua.hic.commons.GenerateQRCode;
import tsinghua.hic.dao.ProductDao;
import tsinghua.hic.service.HashGenerateService;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月20日
 * @Desc
 */
@RequestMapping("/")
public class TestController {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private HashGenerateService hashGenerateService;

    private static final Logger log = LoggerFactory
            .getLogger(TestController.class);

    public ResponseEntity<String> home2Hystrix() {
        log.error("hystrixtest2");
        return new ResponseEntity<String>("HYSTRIX",
                HttpStatus.TOO_MANY_REQUESTS);
    }

    public ResponseEntity<String> home3Hystrix(String gid) {
        log.error("hystrixtest3");
        return new ResponseEntity<String>("HYSTRIX",
                HttpStatus.TOO_MANY_REQUESTS);
    }

    @HystrixCommand(fallbackMethod = "home2Hystrix")
    @GetMapping("/test1")
    public ResponseEntity<String> home2() {
        ObjectMapper mapper = new ObjectMapper();
        productDao.findAll().forEach(p -> {
            try {
                log.info(mapper.writeValueAsString(p));
            } catch (JsonProcessingException e) {
                log.error(e.getLocalizedMessage());
            }
        });
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "home3Hystrix")
    @GetMapping("/test2/{gid}")
    public ResponseEntity<String> home3(
            @PathVariable(required = true) String gid) {
        try {
            hashGenerateService.generate(gid);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/qr/{gid}")
    @ResponseBody
    public String getQRCode(@PathVariable(required = true) String gid) {
        GenerateQRCode generateQRCode = new GenerateQRCode();
        try {
            String imgSrc = generateQRCode.QREncode(300, 300,
                    "http://192.168.1.112:8000/verifygid/" + gid);
            return imgSrc;
        } catch (Exception e) {
            log.error("gen failed" + e.getLocalizedMessage());
            return null;
        }
    }

    @GetMapping(value = "/qr2/{gid}")
    @ResponseBody
    public String getQRCode2(@PathVariable(required = true) String gid) {
        GenerateQRCode generateQRCode = new GenerateQRCode();
        try {
            String imgSrc = generateQRCode.QREncode(300, 300,
                    "demo texthttp://192.168.1.112:8000/verifygid/" + gid);
            return imgSrc;
        } catch (Exception e) {
            log.error("gen failed" + e.getLocalizedMessage());
            return null;
        }
    }

    @GetMapping("/demo")
    public String demo(Model model) {
        model.addAttribute("name", "world");

        return "template/demo";
    }

}
