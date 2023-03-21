package com.car_agency.car_agency.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_agency.car_agency.entidades.auto;

public interface autoRepository extends JpaRepository<auto,String>{
    
    
}
