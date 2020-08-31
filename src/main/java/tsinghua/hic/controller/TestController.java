/**
 *
 */
package tsinghua.hic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tsinghua.hic.dao.ProductDao;

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

    @GetMapping("/test")
    public ResponseEntity<String> home(int index) {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @GetMapping("/test1")
    public ResponseEntity<String> home2() {
        productDao.findAll().forEach(p -> {
            System.out.println(p.toString());
        });
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

}
