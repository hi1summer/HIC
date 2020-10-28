package tsinghua.hic.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import tsinghua.hic.pojo.po.Productinfo;

public interface ProductinfoDao
        extends PagingAndSortingRepository<Productinfo, String> {
    List<Productinfo> findByGid(String gid);
}
