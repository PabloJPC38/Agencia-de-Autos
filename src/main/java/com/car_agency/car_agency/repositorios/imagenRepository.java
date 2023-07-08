package com.car_agency.car_agency.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.car_agency.car_agency.entidades.imagen;

public interface imagenRepository extends JpaRepository<imagen,String> {
    
}
