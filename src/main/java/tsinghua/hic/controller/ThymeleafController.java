package tsinghua.hic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import net.bytebuddy.asm.Advice.This;

@Controller
public class ThymeleafController {
    private static final Logger log = LoggerFactory.getLogger(This.class);
    @Value("${hic.url}")
    private String hicUrl;

    @GetMapping("/scan")
    public String scan(ModelMap model) {
        return "scan";
    }
}
