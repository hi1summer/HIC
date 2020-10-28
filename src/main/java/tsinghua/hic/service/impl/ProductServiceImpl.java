package tsinghua.hic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsinghua.hic.commons.dynamicdatasource.DSType;
import tsinghua.hic.dao.ProductDao;
import tsinghua.hic.dao.ProductinfoDao;
import tsinghua.hic.dao.ProductinfohashDao;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Productinfo;
import tsinghua.hic.pojo.po.Productinfohash;
import tsinghua.hic.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductinfoDao ProductinfoDao;
    @Autowired
    private ProductinfohashDao productinfohashDao;

    @Override
    @DSType(isMaster = false)
    public Iterable<Product> findall() {
        return productDao.findAll();
    }

    @Override
    @DSType(isMaster = false)
    public Optional<Product> get(String gid) {
        return productDao.findById(gid);
    }

    @Override
    @DSType(isMaster = true)
    public Product add(Product product) {
        productDao.save(product);
        return product;
    }

    @Override
    @DSType(isMaster = true)
    public Productinfo add(Productinfo productinfo) {
        ProductinfoDao.save(productinfo);
        return productinfo;
    }

    @Override
    @DSType(isMaster = false)
    public List<Productinfo> findProductinfoByGid(String gid) {
        return ProductinfoDao.findByGid(gid);
    }

    @Override
    @DSType(isMaster = false)
    public Optional<Productinfohash> gethash(String productinfoid) {
        return productinfohashDao.findById(productinfoid);
    }
}
