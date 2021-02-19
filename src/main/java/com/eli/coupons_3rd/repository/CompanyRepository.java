package com.eli.coupons_3rd.repository;


import com.eli.coupons_3rd.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    boolean existsById(int id);

    Company findById(int companyID);

    boolean existsByEmailAndPassword(String email, String password);

    Company getByEmailAndPassword(String email, String password);


}
