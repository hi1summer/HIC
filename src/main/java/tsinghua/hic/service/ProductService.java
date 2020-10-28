package tsinghua.hic.service;

import java.util.List;
import java.util.Optional;

import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Productinfo;
import tsinghua.hic.pojo.po.Productinfohash;

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

    List<Productinfo> findProductinfoByGid(String gid);

    Optional<Productinfohash> gethash(String productinfoid);
}
