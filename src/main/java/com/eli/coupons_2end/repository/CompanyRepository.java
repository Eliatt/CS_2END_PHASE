package com.eli.coupons_2end.repository;


import com.eli.coupons_2end.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    boolean existsById(int id);

    boolean existsByEmailAndPassword(String email, String password);

    Company getByEmailAndPassword(String email, String password);


}
