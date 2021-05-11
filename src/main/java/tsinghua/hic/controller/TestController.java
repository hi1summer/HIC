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

    @GetMapping(value = "/test2")
    @ResponseBody
    public ResponseEntity<String> testAdd() throws Exception {
        String inputString = "'7c', 'N9', 'cs', 'a7', 'K2', 'UI', 'HR', 'i2', 'fm', 'DZ', '2n', 'Jc', 'qO', 'xE', 'lj', 'to', '0X', 'gw', 'T5', 'c6', 'IU', 'wU', 'pM', 'AU', 'HR', 'DG', 'vY', 'Y0', 'wW', 'Ed', 'ik', 'we', '2u', 'rA', '6M', 'th', 'kq', 'WF', 'Pp', 'Tv', 'hc', '3f', 'Lk', 'g5', 'PF', 'Ht', 'DA', 'Uc', 'kc', 'VD', 'h2', 'Ff', 'XM', 'Ad', 'ld', 'Mi', '8j', 'GD', 'mR', 'ss', 'nI', 'Nq', 'UI', 'N2', 'nw', 'vh', 'BK', 'PR', 'Qj', 'IE', 'tQ', 'ms', 'AO', 'wq', '1R', 'Pf', 'VK', '1W', 'TO', 'u8', 'HI', '3h', 'zK', 'b5', 'sM', 'DZ', 'bB', 'wR', 'qq', 'b6', 'F0', 'Kn', 'Gg', 'oc', 'Jg', 'q1', 'FJ', 'FY', '0n', 'IX', 'z5', '6T', '8X', 'ns', 'Gv', 'Ug', 'AG', '40', 'vo', 'sl', 'nY', 'TZ', 'fW', 'pJ', 'Hc', 'V1', 'iz', 'LC', '7f', '8E'";
        String nameString = "矿泉水";
        String descString = "矿泉水";
        String createTime = "2021-01-01";
        String expireTime = "2021-01-01";
        String[] inputArray = inputString.split(",");
        Integer i = 0;
        for (String input : inputArray) {
            i++;
            input = input.trim();
            input = input.substring(1, input.length() - 1);

            log.info(i + ": " + input);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
