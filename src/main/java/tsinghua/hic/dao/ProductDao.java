package tsinghua.hic.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import tsinghua.hic.pojo.po.Product;

public interface ProductDao
        extends PagingAndSortingRepository<Product, String> {

}
