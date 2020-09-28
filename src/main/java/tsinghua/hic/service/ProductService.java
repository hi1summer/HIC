package tsinghua.hic.service;

import java.util.Optional;

import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Productinfo;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年9月23日
 * @Desc
 */
public interface ProductService {
    Iterable<Product> findall();

    Optional<Product> get(String gid);

    Product add(Product product);

    Productinfo add(Productinfo productinfo);
}
