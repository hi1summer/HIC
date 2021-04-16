package tsinghua.hic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.bytebuddy.asm.Advice.This;

@Controller
public class ScanController {
    private static final Logger log = LoggerFactory.getLogger(This.class);

    @GetMapping("/camera")
    public String camera() {
        return "camera";
    }
}
