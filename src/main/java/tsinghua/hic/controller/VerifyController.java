package tsinghua.hic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import net.bytebuddy.asm.Advice.This;
import tsinghua.hic.service.VerifyService;

@RequestMapping
public class VerifyController {
    private static final Logger log = LoggerFactory.getLogger(This.class);
    @Autowired
    private VerifyService verifyService;

    public ResponseEntity<Boolean> verifyGidHystrix(String gid) {
        log.error("hystrixverifygid");
        return new ResponseEntity<Boolean>(false, HttpStatus.TOO_MANY_REQUESTS);
    }

    @HystrixCommand(fallbackMethod = "verifyGidHystrix")
    @GetMapping("/verifygid/{gid}")
    public ResponseEntity<Boolean> verifyGid(
            @PathVariable(required = true) String gid) {
        if (gid == null || gid.trim().isEmpty()) {
            log.warn("bad request");
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        try {
            Boolean exist = verifyService.verify(gid);
            if (exist) {
                log.info("exist" + gid);
            } else {
                log.info("not exist" + gid);
            }
            return new ResponseEntity<Boolean>(exist, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<Boolean>(false,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
