package com.example.demo.config;

import com.example.demo.util.AESUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author liang
 */
@Configuration
public class EncryptConfig {

    @Value("${encrypt.key:SensitiveKey2026}")
    private String encryptKey;

    @PostConstruct
    public void init() {
        AESUtil.setKey(encryptKey);
    }
}
