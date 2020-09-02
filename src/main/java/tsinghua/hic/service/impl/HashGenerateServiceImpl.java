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
import tsinghua.hic.dao.ProductDao;
import tsinghua.hic.dao.ProducthashDao;
import tsinghua.hic.pojo.po.Product;
import tsinghua.hic.pojo.po.Producthash;
import tsinghua.hic.pojo.po.Productinfo;
import tsinghua.hic.service.HashGenerateService;

@Service
public class HashGenerateServiceImpl implements HashGenerateService {
    private static final Logger logger = LoggerFactory
            .getLogger(HashGenerateServiceImpl.class);
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProducthashDao producthashDao;

    @Override
    public void generate(String gid) throws NotFoundException,
            JsonProcessingException, NoSuchAlgorithmException {
        StringBuilder contentString = new StringBuilder();
        Optional<Product> productOptional = productDao.findById(gid);
        if (!productOptional.isPresent()) {
            throw new NotFoundException("gid not found");
        } else {
            Product product = productOptional.get();
            List<Productinfo> productinfos = product.getProductinfos();
            ObjectMapper mapper = new ObjectMapper();
            contentString.append(mapper.writeValueAsString(product));
            contentString.append(mapper.writeValueAsString(productinfos));

            logger.info("content string: " + contentString.toString());
            EncryptWithSHA256 encryptWithSHA256 = new EncryptWithSHA256();
            String digestString = encryptWithSHA256
                    .encrypt(contentString.toString());
            logger.info("gid + digest string: " + gid + digestString);
            String seconddigestString = encryptWithSHA256
                    .encrypt(gid + digestString);
            logger.info("seconddigestString: " + seconddigestString.toString());
            Producthash producthash = new Producthash();
            producthash.setGid(gid);
            producthash.setHash(seconddigestString);
            producthashDao.save(producthash);
            logger.info(seconddigestString);
        }
    }

}
