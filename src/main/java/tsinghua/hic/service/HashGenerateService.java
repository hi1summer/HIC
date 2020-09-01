package tsinghua.hic.service;

import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.core.JsonProcessingException;

import javassist.NotFoundException;

public interface HashGenerateService {
    void generate(String gid) throws NotFoundException, JsonProcessingException,
            NoSuchAlgorithmException;
}
