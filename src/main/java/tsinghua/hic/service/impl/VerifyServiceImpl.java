package tsinghua.hic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsinghua.hic.commons.dynamicdatasource.DSType;
import tsinghua.hic.dao.ProducthashDao;
import tsinghua.hic.service.VerifyService;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    private ProducthashDao producthashDao;

    @Override
    @DSType(isMaster = false)
    public Boolean verify(String gid) {
        return producthashDao.findByHash(gid).isPresent();
    }

}
