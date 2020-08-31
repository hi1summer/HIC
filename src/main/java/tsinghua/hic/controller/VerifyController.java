package tsinghua.hic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tsinghua.hic.service.VerifyService;

@RequestMapping("/")
public class VerifyController {

    @Autowired
    private VerifyService verifyService;

    @GetMapping("/verifygid/{gid}")
    public ResponseEntity<Boolean> verifyGid(@PathVariable String gid) {
        verifyService.verify(gid);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
