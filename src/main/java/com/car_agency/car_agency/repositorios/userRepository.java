package com.car_agency.car_agency.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.car_agency.car_agency.entidades.usuario;


public interface userRepository extends JpaRepository<usuario,String>{
    
    @Query("SELECT u FROM usuario u WHERE u.email = :email" )
    public usuario Buscar_Por_Email(String email);
}
