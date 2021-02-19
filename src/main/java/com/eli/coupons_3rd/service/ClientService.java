package com.eli.coupons_3rd.service;

import com.eli.coupons_3rd.exceptions.LoginException;
import com.eli.coupons_3rd.repository.CompanyRepository;
import com.eli.coupons_3rd.repository.CouponRepository;
import com.eli.coupons_3rd.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;


    public abstract boolean login(String email, String password) throws LoginException;
}
