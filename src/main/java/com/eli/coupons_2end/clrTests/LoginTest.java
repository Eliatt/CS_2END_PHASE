package com.eli.coupons_2end.clrTests;

import com.eli.coupons_2end.beans.ClientType;
import com.eli.coupons_2end.login.LoginManager;
import com.eli.coupons_2end.service.AdminService;
import com.eli.coupons_2end.service.CompanyService;
import com.eli.coupons_2end.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginTest {


    @Autowired
    private LoginManager loginManager;


    public AdminService adminLogin() {
        AdminService adminService = null;
        try {
            adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (adminService != null) {
            System.out.println("Successful Login");
            return adminService;
        } else {
            return null;
        }
    }

    public CompanyService companyLogin() {
        CompanyService companyService = null;
        try {
            companyService = (CompanyService) loginManager.login("test_company@company.com", "company", ClientType.COMPANY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (companyService != null) {
            System.out.println(companyService.getCompany().getName() + " Successful login");
            return companyService;
        } else {
            return null;
        }
    }

    public CustomerService customerLogin() {
        CustomerService customerService = null;
        try {
            customerService = (CustomerService) loginManager.login("test_customer@customer.com", "customer", ClientType.CUSTOMER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (customerService != null) {
            System.out.println(customerService.getCustomer().getEmail() + " Successful login");
            return customerService;
        } else {
            return null;
        }
    }

}

