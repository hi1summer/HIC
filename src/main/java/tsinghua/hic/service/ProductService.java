package tsinghua.hic.service;

import java.util.Optional;

import tsinghua.hic.pojo.po.Product;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年9月23日
 * @Desc
 */
public interface ProductService {
    Optional<Product> get(String gid);
}
