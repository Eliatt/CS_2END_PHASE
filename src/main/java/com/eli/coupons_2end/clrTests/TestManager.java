package com.eli.coupons_2end.clrTests;

import com.eli.coupons_2end.service.AdminService;
import com.eli.coupons_2end.service.CompanyService;
import com.eli.coupons_2end.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestManager implements CommandLineRunner {

    @Autowired
    private ServiceTests serviceTests;
    @Autowired
    private LoginTest loginTest;

    @Override
    public void run(String... args) throws Exception {

        AdminService adminService = loginTest.adminLogin();
        if (adminService != null) {
            serviceTests.adminServiceTests(adminService);
        }
        CompanyService companyService = loginTest.companyLogin();
        if (companyService != null || adminService != null) {
            serviceTests.companyServiceTests(companyService);
        }
        CustomerService customerService = loginTest.customerLogin();
        if (customerService != null || companyService != null || adminService != null) {
            serviceTests.customerServiceTests(customerService);
        }

    }
}
