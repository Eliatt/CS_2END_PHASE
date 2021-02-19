package com.eli.coupons_3rd.config;

import com.eli.coupons_3rd.security.ServiceInterceptor;
import com.eli.coupons_3rd.service.AdminService;
import com.eli.coupons_3rd.service.CompanyService;
import com.eli.coupons_3rd.service.CustomerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    public ServiceInterceptor adminSI;
    public ServiceInterceptor companySI;
    public ServiceInterceptor customerSI;

    public InterceptorConfiguration(ServiceInterceptor adminSI, ServiceInterceptor companySI, ServiceInterceptor customerSI) {
        this.adminSI = adminSI;
        this.adminSI.setServiceType(AdminService.class);
        this.companySI = companySI;
        this.companySI.setServiceType(CompanyService.class);
        this.customerSI = customerSI;
        this.customerSI.setServiceType(CustomerService.class);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(adminSI).addPathPatterns("/admin/**");
        registry.addInterceptor(companySI).addPathPatterns("/company/**");
        registry.addInterceptor(customerSI).addPathPatterns("/customer/**");
    }
}
