package tsinghua.hic.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsinghua.hic.dao.ProductDao;
import tsinghua.hic.dao.ProductinfoDao;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Productinfo;
import tsinghua.hic.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductinfoDao ProductinfoDao;

    @Override
    public Iterable<Product> findall() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> get(String gid) {
        return productDao.findById(gid);
    }

    @Override
    public Product add(Product product) {
        productDao.save(product);
        return product;
    }

    @Override
    public Productinfo add(Productinfo productinfo) {
        ProductinfoDao.save(productinfo);
        return productinfo;
    }
}
