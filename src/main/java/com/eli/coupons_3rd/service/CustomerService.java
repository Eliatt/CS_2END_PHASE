package com.eli.coupons_3rd.service;

import com.eli.coupons_3rd.beans.Category;
import com.eli.coupons_3rd.beans.Coupon;
import com.eli.coupons_3rd.beans.Customer;
import com.eli.coupons_3rd.exceptions.DoesNotExistException;
import com.eli.coupons_3rd.exceptions.FailOperationException;
import com.eli.coupons_3rd.exceptions.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Scope("prototype")
public class CustomerService extends ClientService {

    private int customerID;

    @Override
    public boolean login(String email, String password) throws LoginException {
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                this.customerID = customer.getId();
                return true;
            }
        }
        throw new LoginException("Fail Login attempt");
    }

    public Customer getCustomer() {
        Customer toGet = customerRepository.getOne(this.customerID);
        return toGet;
    }

    public void purchaseCoupon(Coupon coupon)
            throws FailOperationException {
        if (companyRepository.existsById(coupon.getId()) == false) {
            throw new FailOperationException("Coupon by this ID not found");
        }
        Coupon dataBaseCoupon = couponRepository.getOne(coupon.getId());
        if (getCustomerCoupons().contains(dataBaseCoupon)) {
            throw new FailOperationException("Customer already purchased such coupon");
        }
        if (dataBaseCoupon.getAmount() <= 0) {
            throw new FailOperationException("No such coupon left");
        }
        if (dataBaseCoupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new FailOperationException("This Coupon is out of date");
        }
        Customer customer = this.getCustomerDetails();
        customer.purchaseCoupon(dataBaseCoupon);
        customerRepository.saveAndFlush(customer);
        dataBaseCoupon.setAmount(dataBaseCoupon.getAmount() - 1);
        couponRepository.saveAndFlush(dataBaseCoupon);
    }

    public Set<Coupon> getCustomerCoupons() {
        return customerRepository.getOne(this.customerID).getCoupons();
    }


    public List<Coupon> customerCouponsByCategory(Category category) {
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon : getCustomerCoupons()) {
            if (coupon.getCategory() == category) {
                coupons.add(coupon);
            }
        }
        return coupons;
    }

    public List<Coupon> customerCouponsByMaxPrice(double maxPrice) {
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon : getCustomerCoupons()) {
            if (coupon.getPrice() <= maxPrice) {
                coupons.add(coupon);
            }
        }
        return coupons;
    }


    public boolean RemoveCouponPurchase(Coupon coupon) throws DoesNotExistException {
        for (Coupon toRemove : getCustomerCoupons()) {
            if (!couponRepository.existsById(coupon.getId())) {
                throw new DoesNotExistException("Coupon Does not Exist");
            }
            toRemove = couponRepository.getOne(coupon.getId());
            toRemove.setAmount(toRemove.getAmount() + 1);
            couponRepository.saveAndFlush(toRemove);
            Customer customer = customerRepository.getOne(this.customerID);
            customer.getCoupons().remove(coupon);
            customerRepository.saveAndFlush(customer);
            System.out.println("Coupon " + coupon.getTitle() + " Successfully removed");
        }
        return true;
    }

    public Customer getCustomerDetails() {
        return customerRepository.getOne(this.customerID);
    }
}




