package tsinghua.hic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class VerifyController {
    @GetMapping("/verifygid/{gid}")
    public ResponseEntity<Boolean> verifyGid(@PathVariable String gid) {
        System.out.println(gid);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
