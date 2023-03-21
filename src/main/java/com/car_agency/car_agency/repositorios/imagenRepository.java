package com.car_agency.car_agency.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car_agency.car_agency.entidades.imagen;

@Repository
public interface imagenRepository extends JpaRepository<imagen,String> {
    
}
