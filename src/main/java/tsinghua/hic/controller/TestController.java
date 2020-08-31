/**
 *
 */
package tsinghua.hic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/test2/{gid}")
    public ResponseEntity<String> home3(
            @PathVariable(required = true) String gid) {
        try {
            hashGenerateService.generate(gid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

}
