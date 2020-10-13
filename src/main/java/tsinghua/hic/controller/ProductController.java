package tsinghua.hic.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.asm.Advice.This;
import tsinghua.hic.pojo.dto.InfoTypeEnum;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Productinfo;
import tsinghua.hic.service.HashGenerateService;
import tsinghua.hic.service.ProductService;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(This.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private HashGenerateService hashGenerateService;

    @ModelAttribute("infoTypes")
    public List<InfoTypeEnum> populateTypes() {
        return Arrays.asList(InfoTypeEnum.values());
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        Iterable<Product> productList = productService.findall();
        model.addAttribute("list", productList);
        return new ModelAndView("list", model);
    }

    @GetMapping("/get/{gid}")
    public ModelAndView get(ModelMap model, @PathVariable String gid) {
        try {
            Product product = productService.get(gid).get();
            model.addAttribute("product", product);
            return new ModelAndView("listproductinfo", model);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ModelAndView("listproductinfo", model);
        }
    }

    @GetMapping("/addproduct")
    public ModelAndView addproduct(ModelMap model) {
        Product product = new Product();
        Date now = new Date();
        product.setCreateTime(now);
        product.setUpdateTime(now);
        model.addAttribute("product", product);
        return new ModelAndView("addproduct", model);
    }

    @PostMapping("/addproduct")
    public ModelAndView addproductpost(ModelMap model, Product product) {
        product = productService.add(product);
        try {
            hashGenerateService.generate(product.getGid());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/list");
    }

    @GetMapping("/addproductinfo/{gid}")
    public ModelAndView addproductinfo(ModelMap model,
            @PathVariable String gid) {
        Productinfo productinfo = new Productinfo();
        Date now = new Date();
        Product product = new Product();
        product.setGid(gid);
        productinfo.setProduct(product);
        productinfo.setCreateTime(now);
        productinfo.setUpdateTime(now);
        model.addAttribute("productinfo", productinfo);
        return new ModelAndView("addproductinfo", model);
    }

    @PostMapping("/addproductinfo")
    public ModelAndView addproductinfopost(Productinfo productinfo) {
        productinfo = productService.add(productinfo);
        try {
            hashGenerateService.generate(productinfo.getProduct().getGid());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return new ModelAndView(
                "redirect:/get/" + productinfo.getProduct().getGid());
    }

    @GetMapping("/generate/{gid}")
    public ModelAndView generate(@PathVariable String gid) {
        try {
            hashGenerateService.generate(gid);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/list");
    }

}
