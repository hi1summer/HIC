package tsinghua.hic.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsinghua.hic.commons.dynamicdatasource.DSType;
import tsinghua.hic.dao.ProductDao;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    @DSType(isMaster = false)
    public Optional<Product> get(String gid) {
        return productDao.findById(gid);
    }

    @Override
    public Product update(Product product) {
        return productDao.save(product);

    }
}
