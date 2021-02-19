package com.eli.coupons_3rd.login;

import com.eli.coupons_3rd.exceptions.LoginException;
import com.eli.coupons_3rd.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {

    @Autowired
    private ApplicationContext ctx;

    public <T extends ClientService> T login(String email, String password, Class<T> serviceClass) throws LoginException {
        if (ctx.containsBean(serviceClass.getName())) {
            throw new RuntimeException("Could not catch type " + serviceClass.getTypeName().toString());
        }
        T service = ctx.getBean(serviceClass);
        if (service.login(email, password)) {
            return service;
        }
        return null;
    }
}
