package com.IMTCare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTCare.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
   public Admin findByMobileNo(String mobileno);
}
