/**
 *
 */
package tsinghua.hic.dao;

import org.springframework.data.repository.CrudRepository;

import tsinghua.hic.pojo.po.Transaction;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
public interface TransactionDao extends CrudRepository<Transaction, String> {

}
