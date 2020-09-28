/**
 *
 */
package tsinghua.hic.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import tsinghua.hic.pojo.po.Product;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
public interface ProductDao
        extends PagingAndSortingRepository<Product, String> {

}
