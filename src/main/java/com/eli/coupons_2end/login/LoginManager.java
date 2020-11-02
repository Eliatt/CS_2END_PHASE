package com.eli.coupons_2end.login;

import com.eli.coupons_2end.beans.ClientType;
import com.eli.coupons_2end.service.AdminService;
import com.eli.coupons_2end.service.ClientService;
import com.eli.coupons_2end.service.CompanyService;
import com.eli.coupons_2end.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {
    @Autowired
    private AdminService adminService;
    private CompanyService companyService;
    private CustomerService customerService;

    @Autowired
    private ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) throws Exception {

        if (clientType.equals(ClientType.ADMIN)) {
            boolean login = adminService.login(email, password);
            if (login) {
                return adminService;
            }
        }
        if (clientType.equals(ClientType.COMPANY)) {
            companyService = ctx.getBean(CompanyService.class);
            boolean login = companyService.login(email, password);
            if (login) {
                return companyService;
            }

        }
        if (clientType.equals(ClientType.CUSTOMER)) {
            customerService = ctx.getBean(CustomerService.class);


            boolean login = customerService.login(email, password);
            if (login) {
                return customerService;
            }
        }
        return null;
    }
}
