package com.eli.coupons_3rd.utils;

import com.eli.coupons_3rd.beans.Category;
import com.eli.coupons_3rd.beans.Company;
import com.eli.coupons_3rd.beans.Coupon;
import com.eli.coupons_3rd.beans.Customer;
import com.eli.coupons_3rd.repository.CompanyRepository;
import com.eli.coupons_3rd.repository.CouponRepository;
import com.eli.coupons_3rd.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(1)
public class DataBaseUtil implements CommandLineRunner {

    public static final String Amp = "https://cdn.pixabay.com/photo/2017/12/23/15/20/instrument-3035398_960_720.jpg";
    public static final String Bike = "https://cdn.pixabay.com/photo/2018/01/06/23/25/snow-3066167_960_720.jpg";
    public static final String Picnic = "https://cdn.pixabay.com/photo/2016/01/19/18/01/food-1150029_960_720.jpg";
    public static final String Books = "https://cdn.pixabay.com/photo/2016/10/26/10/05/book-1771073_960_720.jpg";
    public static final String ToolBox = "https://cdn.pixabay.com/photo/2016/08/10/01/24/toolboxes-1582315_960_720.jpg";
    public static final String Wine = "https://cdn.pixabay.com/photo/2016/10/22/21/44/white-wine-1761771_960_720.jpg";
    public static final String Tent = "https://cdn.pixabay.com/photo/2016/11/21/15/14/camping-1845906_960_720.jpg";
    public static final String Shoes = "https://cdn.pixabay.com/photo/2014/12/31/11/41/shoes-584850_960_720.jpg";
    public static final String HeadSet = "https://cdn.pixabay.com/photo/2016/11/29/09/08/headphone-1868612_960_720.jpg";
    public static final String WristWatch = "https://cdn.pixabay.com/photo/2018/01/18/19/06/time-3091031_960_720.jpg";


    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        RunDataBaseUtil();
    }

    private void RunDataBaseUtil() {
        addCompanies();
        addCustomers();
        addCoupons();
        addPurchases();
    }

    public void addCompanies() {

        Company company1 = new Company("AlphaVox", "alpha@alphavox.com", "Cc12345!");
        Company company2 = new Company("ACE", "ace@ace.com", "Cc12345!");
        Company company3 = new Company("ShuferSal", "shufersal@shufersal.com", "Cc12345!");
        Company company4 = new Company("ZometSfarim", "zomet@sfarim.com", "Cc12345!");
        Company company5 = new Company("Sport Vertheimer", "vsport@gmail.com", "Cc12345!");
        Company company6 = new Company("Hubiza", "hubiza@hubiza.com", "Cc12345!");

        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
        companyRepository.save(company4);
        companyRepository.save(company5);
        companyRepository.save(company6);
    }

    public void addCustomers() {
        Customer customer1 = new Customer("Galit", "Shachar", "galits@gmail.com", "Cc#12345");
        Customer customer2 = new Customer("Yair", "Attias", "yair.attias@gmail.com", "Cc#12345");
        Customer customer3 = new Customer("Yossi", "Huri", "yossih@gmail.com", "Cc#12345");
        Customer customer4 = new Customer("Michal", "Bat Adam", "michalba@gmail.com", "Cc#12345");
        Customer customer5 = new Customer("Berri", "Saharof", "berris@gmail.com", "Cc#12345");
        Customer customer6 = new Customer("Zilla", "Dagan", "zilla.dagan@gmail.com", "Cc#12345");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);
        customerRepository.save(customer6);
    }

    public void addCoupons() {
        Coupon coupon1 = new Coupon(0, 1, Category.SHOPPING, "Guitar Amplifier", "All Tube Guitar Amplifier", Date.valueOf("2020-01-20"),
                Date.valueOf("2022-01-25"), 3, 5800, Amp);
        Coupon coupon2 = new Coupon(0, 5, Category.SPORTS, "Mountain Bicycle", "Professional mountain bicycle", Date.valueOf("2020-01-10"),
                Date.valueOf("2021-12-31"), 10, 3499.99, Bike);
        Coupon coupon3 = new Coupon(0, 6, Category.FOOD, "Vegan Picnic Basket Meal", "Family Size Delicious Vegan Picnic Basket Meal", Date.valueOf("2021-01-01"),
                Date.valueOf("2021-06-25"), 30, 249.99, Picnic);
        Coupon coupon4 = new Coupon(0, 4, Category.LIFE_STYLE, "Book Bundle", "Poems by T.S.ELLIOT Trilogy", Date.valueOf("2020-11-01"),
                Date.valueOf("2022-01-01"), 15, 238.99, Books);
        Coupon coupon5 = new Coupon(0, 2, Category.SHOPPING, "Tool Box", "STANLEY  Tool Box", Date.valueOf("2020-12-31"),
                Date.valueOf("2022-01-01"), 30, 299.99, ToolBox);
        Coupon coupon6 = new Coupon(0, 3, Category.SHOPPING, "Red & White Bottles of Wine ", "Dual Bottles of Wine ", Date.valueOf("2021-01-01"),
                Date.valueOf("2021-01-30"), 0, 49.90, Wine);
        Coupon coupon7 = new Coupon(0, 2, Category.LIFE_STYLE, "Camping Tent ", "Great for outdoors camping ", Date.valueOf("2021-01-01"),
                Date.valueOf("2021-01-30"), 20, 400, Tent);
        Coupon coupon8 = new Coupon(0, 5, Category.SPORTS, "Hiking Shoes", "All Seasons Hiking Shoes", Date.valueOf("2020-01-25"),
                Date.valueOf("2022-01-25"), 30, 5800, Shoes);
        Coupon coupon9 = new Coupon(0, 5, Category.SHOPPING, " Men's Wrist Watch", "Swiss Made Wrist Watch", Date.valueOf("2021-01-31"),
                Date.valueOf("2021-12-31"), 10, 3499.99, WristWatch);
        Coupon coupon10 = new Coupon(0, 2, Category.SHOPPING, "Head Phones", "Audio HeadPhones", Date.valueOf("2021-01-01"),
                Date.valueOf("2021-06-25"), 30, 249.99, HeadSet);


        couponRepository.save(coupon1);
        couponRepository.save(coupon2);
        couponRepository.save(coupon3);
        couponRepository.save(coupon4);
        couponRepository.save(coupon5);
        couponRepository.save(coupon6);
        couponRepository.save(coupon7);
        couponRepository.save(coupon8);
        couponRepository.save(coupon9);
        couponRepository.save(coupon10);
    }

    public void addPurchases() {

        Coupon coupon1 = couponRepository.getOne(1);
        Coupon coupon2 = couponRepository.getOne(2);
        Coupon coupon3 = couponRepository.getOne(3);
        Coupon coupon4 = couponRepository.getOne(4);
        Coupon coupon5 = couponRepository.getOne(5);
        Coupon coupon6 = couponRepository.getOne(6);

        Customer customer1 = customerRepository.getOne(1);
        customer1.purchaseCoupon(coupon1);
        customer1.purchaseCoupon(coupon2);
        customerRepository.saveAndFlush(customer1);

        Customer customer2 = customerRepository.getOne(2);
        customer2.purchaseCoupon(coupon1);
        customer2.purchaseCoupon(coupon4);
        customerRepository.saveAndFlush(customer2);

        Customer customer3 = customerRepository.getOne(3);
        customer3.purchaseCoupon(coupon5);
        customerRepository.saveAndFlush(customer3);

        Customer customer4 = customerRepository.getOne(4);
        customer4.purchaseCoupon(coupon6);
        customerRepository.saveAndFlush(customer4);

        Customer customer5 = customerRepository.getOne(5);
        customer5.purchaseCoupon(coupon5);
        customerRepository.saveAndFlush(customer5);

        Customer customer6 = customerRepository.getOne(6);
        customer6.purchaseCoupon(coupon6);
        customerRepository.saveAndFlush(customer6);

    }


}

