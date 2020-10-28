package tsinghua.hic.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import tsinghua.hic.pojo.po.Productinfohash;

public interface ProductinfohashDao
        extends PagingAndSortingRepository<Productinfohash, String> {
    Optional<Productinfohash> findByHash(String hash);
}
