/**
 *
 */
package tsinghua.hic.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import tsinghua.hic.pojo.po.Producthash;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
public interface ProducthashDao
        extends PagingAndSortingRepository<Producthash, String> {
    Optional<Producthash> findByHash(String hash);
}
