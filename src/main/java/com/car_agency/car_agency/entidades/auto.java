package com.car_agency.car_agency.entidades;


import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class auto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;
    private String name;
    private String marca;
    private String precio;
    private String disenio;
    private String performance;
    private String exclusividad;
    private int color;
    @ElementCollection // 1
    @CollectionTable(name = "colores", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "color") // 3
    private List<String> colores;
    @OneToOne
    private imagen img;

    @OneToMany
    private List<imagen> estilo1;
    @OneToMany
    private List<imagen> estilo2;
    @OneToMany
    private List<imagen> estilo3;
    @OneToMany
    private List<imagen> estilo4;
    @OneToMany
    private List<imagen> estilo5;
    @OneToOne
    private imagen portada;

    
    public auto() {
    }
}
