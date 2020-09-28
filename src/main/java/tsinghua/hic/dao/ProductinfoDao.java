/**
 *
 */
package tsinghua.hic.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import tsinghua.hic.pojo.po.Productinfo;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
public interface ProductinfoDao
        extends PagingAndSortingRepository<Productinfo, String> {
}
