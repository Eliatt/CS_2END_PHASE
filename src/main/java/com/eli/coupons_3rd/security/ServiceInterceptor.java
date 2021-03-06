package com.eli.coupons_3rd.security;


import com.eli.coupons_3rd.service.ClientService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ServiceInterceptor implements HandlerInterceptor {
    public ServiceInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Setter
    @Getter
    Class<? extends ClientService> serviceType;

    TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");
        ServiceData data = tokenManager.getService(token);
        if (data == null) {
            response.setStatus(400);
            response.getWriter().append("Authorization token missing or corrupted");
            return false;
        }
        ClientService service = data.getService();
        if (!(service.getClass().equals(this.serviceType))) {
            response.setStatus(401);
            response.getWriter().append("Authorization token is not authorized to request here");
            return false;
        }

        request.setAttribute("service", service);
        return true;
    }

}
