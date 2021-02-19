package com.eli.coupons_3rd.config;

import com.eli.coupons_3rd.security.ServiceData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfiguration {
    @Bean
    public Map<String, ServiceData> clientServiceMap() {
        return new HashMap<>();
    }

}
