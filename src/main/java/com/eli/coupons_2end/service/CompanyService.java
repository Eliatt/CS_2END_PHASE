package com.eli.coupons_2end.service;

import com.eli.coupons_2end.beans.Category;
import com.eli.coupons_2end.beans.Company;
import com.eli.coupons_2end.beans.Coupon;
import com.eli.coupons_2end.exceptions.DoesNotExistException;
import com.eli.coupons_2end.exceptions.FailOperationException;
import com.eli.coupons_2end.exceptions.LoginException;
import com.eli.coupons_2end.repository.CompanyRepository;
import com.eli.coupons_2end.repository.CouponRepository;
import com.eli.coupons_2end.repository.CustomerRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class CompanyService extends ClientService {


    private int companyID;
    private Company company;
    private Coupon coupon;


    public CompanyService() {
        super();
    }


    @Override
    public boolean login(String email, String password) throws LoginException {
        for (Company company : companyRepository.findAll()) {
            if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
                this.companyID = company.getId();
                return true;
            }
        }
        throw new LoginException("Fail Login attempt");
    }


    public void addCoupon(Coupon coupon) throws FailOperationException {

        for (Coupon toSave : couponRepository.findAll()) {
            if (toSave.getCompanyId() == this.companyID) {
                if (toSave.getTitle().equals(coupon.getTitle())) {
                    throw new FailOperationException("Error: Check companyID and/or coupon existence in database  ");
                }
            }
        }
        couponRepository.save(coupon);
        Company company = companyRepository.getOne(this.companyID);
        company.getCoupons().add(coupon);
        companyRepository.saveAndFlush(company);
        System.out.println("Coupon " + coupon.getTitle() + " Added");

    }

    //todo: fix exception DoesNotExistException issue
    public void updateCoupon(Coupon coupon) throws DoesNotExistException {
        if (!couponRepository.existsById(coupon.getId())) {
            throw new DoesNotExistException("Coupon by this ID does not exist");
        }
        System.out.println("Coupon " + coupon.getTitle() + " Successfully updated");
        couponRepository.saveAndFlush(coupon);
    }

    public void deleteCoupon(Coupon coupon) {
        couponRepository.delete(coupon);
    }

    public Coupon getCoupon(int couponId) throws DoesNotExistException {
        if (couponRepository.existsById(couponId)) {
            System.out.println(couponRepository.getOne(couponId));
        } else {
            throw new DoesNotExistException("Coupon does not exist");
        }
        System.out.println("Coupon found. Enable further actions");
        return coupon;
    }

    public Company getCompany() {
        company = companyRepository.getOne(companyID);
        return company;

    }




    public List<Coupon> getAllCoupons() {
        return getCompany().getCoupons();
    }


    public List<Coupon> getCouponsByCategory(Category category) {
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getCategory() == category) {
                coupons.add(coupon);
            }
        }
        return coupons;
    }

    public List<Coupon> getCouponsByMaxPrice(double maxPrice) {
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getPrice() <= maxPrice) {
                coupons.add(coupon);
            }
        }
        return coupons;


    }


}
