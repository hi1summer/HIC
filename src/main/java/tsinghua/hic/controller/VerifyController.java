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

import tsinghua.hic.service.VerifyService;

@RequestMapping("/")
public class VerifyController {
    private static final Logger logger = LoggerFactory
            .getLogger(VerifyController.class);
    @Autowired
    private VerifyService verifyService;

    public ResponseEntity<Boolean> verifyGidHystrix(String gid) {
        logger.error("hystrixverifygid");
        return new ResponseEntity<Boolean>(false, HttpStatus.TOO_MANY_REQUESTS);
    }

    @HystrixCommand(fallbackMethod = "verifyGidHystrix")
    @GetMapping("/verifygid/{gid}")
    public ResponseEntity<Boolean> verifyGid(
            @PathVariable(required = true) String gid) {
        if (gid == null || gid.trim().isEmpty()) {
            logger.warn("bad request");
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        try {
            Boolean exist = verifyService.verify(gid);
            if (exist) {
                logger.info("exist" + gid);
            } else {
                logger.info("not exist" + gid);
            }
            return new ResponseEntity<Boolean>(exist, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<Boolean>(false,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
