package com.eli.coupons_2end.clrTests;

import com.eli.coupons_2end.beans.Category;
import com.eli.coupons_2end.beans.Company;
import com.eli.coupons_2end.beans.Coupon;
import com.eli.coupons_2end.beans.Customer;
import com.eli.coupons_2end.exceptions.AlreadyExistException;
import com.eli.coupons_2end.exceptions.DoesNotExistException;
import com.eli.coupons_2end.exceptions.FailOperationException;
import com.eli.coupons_2end.exceptions.LoginException;
import com.eli.coupons_2end.repository.CouponRepository;
import com.eli.coupons_2end.service.AdminService;
import com.eli.coupons_2end.service.CompanyService;
import com.eli.coupons_2end.service.CustomerService;
import com.eli.coupons_2end.utils.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ServiceTests implements CommandLineRunner {

    @Autowired
    AdminService adminService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        adminServiceTests(this.adminService);
        companyServiceTests(this.companyService);
        customerServiceTests(this.customerService);

    }

    public void adminServiceTests(AdminService adminService) {
        System.out.println("*****************************************************************************************************************************************************************************************************************************************************************");
        System.out.println(HeadersUtil.ADMIN_SERVICE_TESTS);
        System.out.println("*****************************************************************************************************************************************************************************************************************************************************************");
        System.out.println(" === Successful login ===");
        try {
            System.out.println(adminService.login("admin@admin.com", "admin"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(" === Unsuccessful login ===");
        try {
            System.out.println(adminService.login("admin@admin", "admin"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("*** COMPANY TESTS *** ");

        System.out.println("==== Add Company ===");
        Company testCompany = new Company("Test_Company", "test_company@company.com", "company");
        Company company1 = new Company("Aroma", "aroma@coffee.com", "aroma");
        Company company2 = new Company("Ace", "ace@diy.com", "ace");
        try {
            adminService.addCompany(testCompany);
            adminService.addCompany(company1);
            adminService.addCompany(company2);
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("==== Unsuccessful Adding Company attempt. case: name ===");
        Company candidate1 = new Company("Best_Company", "test_company@company.com", "company");
        try {
            adminService.addCompany(candidate1);
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("==== Unsuccessful Adding Company attempt. case: email ===");
        Company candidate2 = new Company("Test_Company", "test_company@company.com", "company");
        try {
            adminService.addCompany(candidate2);
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("==== Company Update ===");
        Company candidate3 = null;
        try {
            candidate3 = adminService.getOneCompany(3);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        candidate3.setName("Red Sea Resort");
        candidate3.setPassword("888888");
        candidate3.setEmail("rs@resort.com");
        try {
            adminService.updateCompany(candidate3);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Unsuccessful Company Update ===");
        Company candidate4 = null;
        try {
            candidate4 = adminService.getOneCompany(43);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        candidate3.setName("Red Sea Resort");
        candidate3.setPassword("888888");
        candidate3.setEmail("rs@resort.com");
        try {
            adminService.updateCompany(candidate3);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== Is Company By A Given Id Exists? case: Checking true ====");
        System.out.println(adminService.isCompanyExists(2));
        System.out.println("Company by this ID exists ");


        System.out.println("=== Is Company By A Given Id Exists? case: Checking false ====");
        System.out.println(adminService.isCompanyExists(52));
        System.out.println("Company by this ID Does not exists ");

        System.out.println("=== Delete Company ===");
        Company candidate5 = null;
        try {
            candidate5 = adminService.getOneCompany(3);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        try {
            adminService.deleteCompany(candidate5);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(adminService.getAllCompanies());


        System.out.println("=== Unsuccessful Company Delete ===");
        Company candidate6 = null;
        try {
            candidate6 = adminService.getOneCompany(12);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        try {
            adminService.deleteCompany(candidate5);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(adminService.getAllCompanies());


        System.out.println("=== Get one Company ===");
        try {
            System.out.println(adminService.getOneCompany(1));
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Unsuccessful 'Get one' Company attempt ===");
        try {
            System.out.println(adminService.getOneCompany(13));
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== Get All Companies ===");
        System.out.println("All Listed Companies" + adminService.getAllCompanies());


        System.out.println("*** CUSTOMER TESTS *** ");


        System.out.println("=== Adding a Customer ===");

        Customer testCustomer = new Customer(
                "test_customer@customer.com",
                "customer",
                "testFname",
                "testLname"
        );
        try {
            adminService.addCustomer(testCustomer);
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(testCustomer);

        Customer toSave = new Customer(
                "david.levy@gmail.com",
                "123456",
                "Dudi",
                "Levy"
        );
        try {
            adminService.addCustomer(toSave);
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(toSave);

        Customer toSave2 = new Customer(
                "michal.ron@gmail.com",
                "mich007",
                "Michal",
                "Ron"
        );
        try {
            adminService.addCustomer(toSave2);
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(toSave2);


        System.out.println("=== Unsuccessful Adding a Customer ===");
        Customer toSave3 = new Customer(
                "david.levy@gmail.com",
                "123456",
                "David",
                "Levy"
        );
        try {
            adminService.addCustomer(toSave3);
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(toSave3);


        System.out.println("=== Get one Customer ===");
        Customer customer;
        try {
            System.out.println(customer = adminService.getOneCustomer(1));
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Unsuccessful 'Get one' Customer attempt ===");
        Customer customer1;
        try {
            System.out.println(customer1 = adminService.getOneCustomer(39));
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Updating a Customer ===");
        Customer toUpdate = null;
        try {
            toUpdate = adminService.getOneCustomer(2);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        toUpdate.setFirstName("Menashe");
        toUpdate.setLastName("Grosman");
        toUpdate.setEmail("menash.grosman@gmail.com");
        toUpdate.setPassword("*****");
        try {
            adminService.updateCustomer(toUpdate);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(toUpdate);


        System.out.println("=== Unsuccessful Updating a Customer ===");
        Customer toUpdate1 = null;
        try {
            toUpdate1 = adminService.getOneCustomer(12);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        toUpdate.setFirstName("Menashe");
        toUpdate.setLastName("Grosman");
        toUpdate.setEmail("men.grosman@gmail.com");
        try {
            adminService.updateCustomer(toUpdate);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(toUpdate);


        System.out.println("=== Delete a Customer ===");
        try {
            adminService.deleteCustomer(2);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("All listed customers: " + adminService.getAllCustomers());


        System.out.println("=== Unsuccessful Deleting Customer ===");
        try {
            adminService.deleteCustomer(12);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("All listed customers: " + adminService.getAllCustomers());

        System.out.println("=== Get All Customers ===");
        System.out.println("All Listed Customers: " + adminService.getAllCustomers());

        System.out.println("*****************************************************************************************************************************************************************************************************************************************************************");

    }


    public void companyServiceTests(CompanyService companyService) throws DoesNotExistException {
        System.out.println(HeadersUtil.COMPANY_SERVICE_TESTS);
        System.out.println("*****************************************************************************************************************************************************************************************************************************************************************");


        System.out.println(" === Successful login ===");
        try {
            System.out.println(companyService.login("test_company@company.com", "company"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" === Unsuccessful login. Case: Email ===");
        try {
            System.out.println(companyService.login("test_company@company.co", "company"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(" === Unsuccessful login. Case: password ===");
        try {
            System.out.println(companyService.login("test_company@company.com", "compan"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Add Coupon === ");
        Coupon coupon4 = new Coupon(
                1,
                Category.LIFE_STYLE,
                "Aromatic Massage",
                "Relaxing Helling Facial Massage",
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                60,
                249.99,
                "https://cdn.pixabay.com/photo/2016/08/11/02/23/massage-therapy-1584711_960_720.jpg);"
        );
        try {
            companyService.addCoupon(coupon4);
        } catch (FailOperationException e) {
            System.out.println(e.getMessage());
        }

        Coupon coupon2 = new Coupon(
                1,
                Category.FOOD,
                "Vegan Knaffe",
                "Famous Oriental desert now with an all new vegan recipe",
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                100,
                59.99,
                "https://cdn.babamail.co.il/images/recipes_source/1ac794c2-d62f-4cf7-bae7-959b12c644e1.jpg);"
        );
        try {
            companyService.addCoupon(coupon2);
        } catch (FailOperationException e) {
            System.out.println(e.getMessage());
        }

        Coupon coupon3 = new Coupon(
                1,
                Category.FOOD,
                "Falafel Humus",
                "Famous dish now with an all new spicy recipe",
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                100,
                16.99,
                "https://cdn.pixabay.com/photo/2018/10/15/12/42/falafel-3748886_960_720.jpg");
        try {
            companyService.addCoupon(coupon3);
        } catch (FailOperationException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Add Coupon. Case: wrong company ID === ");
        Coupon coupon5 = new Coupon(
                3,
                Category.LIFE_STYLE,
                "Aromatic Massage",
                "Relaxing Helling Facial Massage",
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                60,
                249.99,
                "https://cdn.pixabay.com/photo/2016/08/11/02/23/massage-therapy-1584711_960_720.jpg);"
        );
        try {
            companyService.addCoupon(coupon5);
        } catch (FailOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== Add Coupon. Case: Already Exist === ");
        Coupon coupon6 = new Coupon(
                1,
                Category.LIFE_STYLE,
                "Aromatic Massage",
                "Relaxing Helling Facial Massage",
                LocalDate.now(),
                LocalDate.now().plusYears(2),
                60,
                249.99,
                "https://cdn.pixabay.com/photo/2016/08/11/02/23/massage-therapy-1584711_960_720.jpg);"
        );
        try {
            companyService.addCoupon(coupon6);
        } catch (FailOperationException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Update Coupon === ");
        Coupon candidate = couponRepository.getOne(3);
        candidate.setCategory(Category.SHOPPING);
        candidate.setDescription("All new improved recipe");
        companyService.updateCoupon(candidate);

//todo: write test after solving exception issue
        System.out.println("=== Unsuccessful Coupon Update === ");


        System.out.println("=== Get Coupon === ");
        try {
            companyService.getCoupon(1);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Unsuccessful Get Coupon === ");
        try {
            companyService.getCoupon(11);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("=== Get All Coupons === ");
        System.out.println(companyService.getAllCoupons());


        System.out.println("=== Get Coupons by Category === ");
        System.out.println(companyService.getCouponsByCategory(Category.SHOPPING));


        System.out.println("=== Get Coupons by Max price === ");
        System.out.println(companyService.getCouponsByMaxPrice(100));


        System.out.println("=== Delete Coupon === ");
        try {

            Coupon andidate = companyService.getCoupon(3);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        companyService.deleteCoupon(candidate);
        System.out.println(candidate);


        System.out.println("=== Unsuccessful Delete Coupon attempt === ");
        try {
            Coupon andidate = companyService.getCoupon(38);
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        companyService.deleteCoupon(candidate);
        System.out.println(candidate);

        System.out.println("*****************************************************************************************************************************************************************************************************************************************************************");


    }


    public void customerServiceTests(CustomerService customerService) {
        System.out.println(HeadersUtil.CUSTOMER_SERVICE_TESTS);
        System.out.println("*****************************************************************************************************************************************************************************************************************************************************************");

        System.out.println(" === Successful login ===");
        try {
            System.out.println(customerService.login("test_customer@customer.com", "customer"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(" === Unsuccessful login. Case: Email ===");
        try {
            System.out.println(customerService.login("test_customer@customer.co", "customer"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(" === Unsuccessful login. Case: password ===");
        try {
            System.out.println(customerService.login("test_customer@customer.com", "custome"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(" === Add coupon purchase ===");
        Coupon toAdd = couponRepository.getOne(1);
        try {
            customerService.AddCouponPurchase(toAdd);
        } catch (FailOperationException e) {
            System.out.println(e.getMessage());
        }


    }


}
