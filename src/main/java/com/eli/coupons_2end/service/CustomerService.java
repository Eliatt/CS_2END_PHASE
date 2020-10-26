package com.eli.coupons_2end.service;

import com.eli.coupons_2end.beans.Category;
import com.eli.coupons_2end.beans.Coupon;
import com.eli.coupons_2end.beans.Customer;
import com.eli.coupons_2end.exceptions.DoesNotExistException;
import com.eli.coupons_2end.exceptions.FailOperationException;
import com.eli.coupons_2end.exceptions.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class CustomerService extends ClientService {

    private int customerId;

    public CustomerService() {
        super();
    }


    @Override
    public boolean login(String email, String password) throws LoginException {
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                this.customerId = customer.getId();
                return true;
            }
        }
        throw new LoginException("Fail Login attempt");
    }

    public Customer getCustomer() {
        Customer toGet = customerRepository.getOne(this.customerId);
        return toGet;
    }

    public boolean AddCouponPurchase(Coupon coupon) throws FailOperationException {
        for (Coupon toAdd : getCustomerCoupons()) {
            if (!couponRepository.existsById(coupon.getId())) {
                throw new FailOperationException("Coupon Does not Exist");
            }
            if (toAdd.getId() == couponRepository.getByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle()).getId()) {
                throw new FailOperationException(
                        "Customer Already purchased this coupon");
            }
        }
        if (coupon.getAmount() == 0) {
            throw new FailOperationException("This coupon out of stock");
        }
        if (coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new FailOperationException("This coupon has expired");

        } else {
            Coupon toAdd = couponRepository.getByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle());
            toAdd.setAmount(toAdd.getAmount() - 1);
            couponRepository.saveAndFlush(toAdd);
            Customer customer = customerRepository.getOne(this.customerId);
            customer.getCoupons().add(coupon);
            customerRepository.saveAndFlush(customer);
            return true;
        }
    }

    public List<Coupon> getCustomerCoupons() {
        return getCustomer().getCoupons();
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
            Customer customer = customerRepository.getOne(this.customerId);
            customer.getCoupons().remove(coupon);
            customerRepository.saveAndFlush(customer);
            System.out.println("Coupon " + coupon.getTitle() + " Successfully removed");
        }
        return true;
    }
}




