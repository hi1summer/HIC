package tsinghua.hic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.asm.Advice.This;

@RestController
public class ProductInfoController {
    private static final Logger log = LoggerFactory.getLogger(This.class);

    @GetMapping("list")
    public ModelAndView add() {
        return new ModelAndView("add");
    }
}
