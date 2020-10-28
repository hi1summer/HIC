package tsinghua.hic.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.NotFoundException;
import tsinghua.hic.commons.EncryptWithSHA256;
import tsinghua.hic.commons.dynamicdatasource.DSType;
import tsinghua.hic.dao.ProductDao;
import tsinghua.hic.dao.ProductinfoDao;
import tsinghua.hic.dao.ProductinfohashDao;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Productinfo;
import tsinghua.hic.pojo.po.Productinfohash;
import tsinghua.hic.service.HashGenerateService;

@Service
public class HashGenerateServiceImpl implements HashGenerateService {
    private static final Logger logger = LoggerFactory
            .getLogger(HashGenerateServiceImpl.class);
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductinfoDao productinfoDao;
    @Autowired
    private ProductinfohashDao productinfohashDao;

    @Override
    @DSType(isMaster = true)
    public void generate(String productinfoid) throws NotFoundException,
            JsonProcessingException, NoSuchAlgorithmException {
        Optional<Productinfo> productinfoOptional = productinfoDao
                .findById(productinfoid);
        if (!productinfoOptional.isPresent()) {
            throw new NotFoundException("productinfoid not found");
        } else {
            Productinfo productinfo = productinfoOptional.get();
            Product product = productDao.findById(productinfo.getGid()).get();
            List<Productinfo> productinfos = productinfoDao
                    .findByGid(productinfo.getGid());
            ObjectMapper mapper = new ObjectMapper();
            EncryptWithSHA256 encryptWithSHA256 = new EncryptWithSHA256();
            String productinfosString = mapper.writeValueAsString(productinfos);
            String productString = mapper.writeValueAsString(product);
            String digestString = encryptWithSHA256.encrypt(productinfosString);
            String seconddigestString = encryptWithSHA256
                    .encrypt(productString + digestString);
            Productinfohash productinfohash = new Productinfohash();
            productinfohash.setProductinfoid(productinfoid);
            productinfohash.setHash(seconddigestString);
            productinfohashDao.save(productinfohash);
            logger.info("hash(" + productString + "+hash(" + productinfosString
                    + ")" + ")");
            logger.info(seconddigestString);
        }
    }

}
