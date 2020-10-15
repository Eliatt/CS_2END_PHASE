package com.eli.coupons_2end.service;

import com.eli.coupons_2end.exceptions.LoginException;
import com.eli.coupons_2end.repository.CompanyRepository;
import com.eli.coupons_2end.repository.CouponRepository;
import com.eli.coupons_2end.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    protected CompanyRepository companyRepository;
    protected CustomerRepository customerRepository;
    protected CouponRepository couponRepository;

    @Autowired
    public ClientService(
            CompanyRepository companyRepository,
            CustomerRepository customerRepository,
            CouponRepository couponRepository) {

        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public abstract boolean login(String email, String password) throws LoginException;
}
