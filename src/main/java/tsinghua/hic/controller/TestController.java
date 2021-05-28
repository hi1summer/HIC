package tsinghua.hic.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping(value = "/test2")
    @ResponseBody
    public ResponseEntity<String> testAdd() throws Exception {
        String inputString = "'QgpmTt', 'k4AiAe', 'zRrB6F', '9ccP3e', 'IqLCOb', 'WFvgnz', 'QSNqIJ', 'YMaxIM', '1vtfby', 'ntO1Vv', 'z6treP', 'oOIYOc', 'mJES0g', 'nCtpe2', '0MsxUA', 'Fr3Hrz', 'rGc6qc', '6FMyId', 'qfRAcp', 'x9CaiL', 'qqaCfi', '5uJmho', 'SkB2bO', '0f5Xh8', 'STgQtN', 'nhZB3d', '8mc2e2', 'nLJhXK', 'WJj9VP', 'A68HXZ', 'MYyJs4', '6ylr4J', 'd7bgur', 'ktaB4Z', 'Ik9gX2', 'FrvF0L', 'zasHqq', 'UwCoF4', 'wq5Njh', 'YBj8QS', 'SSYiaQ', 'BM8H46', 'L69mFu', 'hK1SZI', 'JNBnRY', 'rKFZUF', 'bas2gE', 'GeHnwp', 'wJB5uU', '6yW1LN'";
        String nameString = "眼影盒";
        String createTime = "2021-01-01";
        String expireTime = "2021-01-01";
        String[] inputArray = inputString.split(",");
        Set<String> inputSet = new HashSet<>(Arrays.asList(inputArray));
        Integer i = 21000;
        for (String input : inputSet) {
            i++;
            input = input.trim();
            input = input.substring(1, input.length() - 1);
            Product product = new Product();
            product.setId(input);
            product.setCreateTime(createTime);
            product.setExpireTime(expireTime);
            product.setProductName(nameString);
            product.setProductDesc(i.toString());
            product.setValidateCount(0);
            productService.add(product);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
