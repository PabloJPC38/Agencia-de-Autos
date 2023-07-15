package com.car_agency.car_agency.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import Enum.Rol;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private Boolean activo;


    public usuario(){

        
    }
    
}
