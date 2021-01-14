package tsinghua.hic.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.asm.Advice.This;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.service.ProductService;

@RestController
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(This.class);

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/test")
    @ResponseBody
    public ResponseEntity<Product> test() throws Exception {
        try {
            Optional<Product> productOptional = productService.get("1");
            if (productOptional.isPresent()) {
                return new ResponseEntity<Product>(productOptional.get(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<Product>(new Product(),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return new ResponseEntity<Product>(new Product(), HttpStatus.OK);
    }
}
