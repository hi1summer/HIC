/**
 *
 */
package tsinghua.hic.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import tsinghua.hic.pojo.po.Express;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
public interface ExpressDao
        extends PagingAndSortingRepository<Express, String> {

}
