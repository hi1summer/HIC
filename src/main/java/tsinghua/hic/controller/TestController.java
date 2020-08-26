/**
 *
 */
package tsinghua.hic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月20日
 * @Desc
 */
@RequestMapping("/")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> home(int index) {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

}
