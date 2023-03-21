package com.car_agency.car_agency.controladores;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car_agency.car_agency.entidades.auto;
import com.car_agency.car_agency.entidades.imagen;
import com.car_agency.car_agency.servicios.autoServices;
import com.car_agency.car_agency.servicios.imagenServices;

@Controller
@RequestMapping("/imagen")
public class imagenController {

    @Autowired
    autoServices autoServ;
    
    @Autowired
    imagenServices imagenServ;

    @GetMapping("/auto/{id}")
    public ResponseEntity<byte[]> imagenAuto (@PathVariable String id){
        auto car = autoServ.getOne(id);
        
       byte[] imagen= car.getImg().getCont();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.IMAGE_JPEG);
       
        
        
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }

    @GetMapping("/auto-portada/{id}")
    public ResponseEntity<byte[]> autoPortada (@PathVariable String id){
        auto car = autoServ.getOne(id);
        
       byte[] imagen= car.getPortada().getCont();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.IMAGE_JPEG);
       
        
        
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }

    @GetMapping("/auto-estilo1/{id}")
    public ResponseEntity<List<byte[]>> autoEstilo1 (@PathVariable String id){
        auto car = autoServ.getOne(id);

        List<byte[]> imagenes = new ArrayList<>();
        for (imagen estilo : car.getEstilo1()) {

            imagenes.add(estilo.getCont());
        }
        HttpHeaders headers = new HttpHeaders();
       
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
    }

    @GetMapping("/auto-estilo2/{id}")
    public ResponseEntity<List<byte[]>> autoEstilo2 (@PathVariable String id){
        auto car = autoServ.getOne(id);

        List<byte[]> imagenes = new ArrayList<>();
        for (imagen estilo : car.getEstilo2()) {

            imagenes.add(estilo.getCont());
        }
        HttpHeaders headers = new HttpHeaders();
       
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
    }
   

    @GetMapping("/auto-estilo3/{id}")
    public ResponseEntity<List<byte[]>> autoEstilo3 (@PathVariable String id){
        auto car = autoServ.getOne(id);

        List<byte[]> imagenes = new ArrayList<>();
        for (imagen estilo : car.getEstilo3()) {

            imagenes.add(estilo.getCont());
        }
        HttpHeaders headers = new HttpHeaders();
       
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
    }



    /**************************************************/

    @GetMapping("/auto-estilo/{id}")
    public void showProductImage(@PathVariable String id,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg"); // Or whatever format you wanna use

        imagen img = imagenServ.findById(id);
        InputStream is = new ByteArrayInputStream(img.getCont());

       /*  Product product = productRepository.findById(id);

        InputStream is = new ByteArrayInputStream(product.getImage());
        */
        IOUtils.copy(is, response.getOutputStream());
    }

/* 
    @GetMapping("/auto-estilo1/{id}")
    public ResponseEntity<byte[]> autoEstilo1 (@PathVariable String id){
        auto car = autoServ.getOne(id);
        
       byte[] imagen= car.getEstilo1().getCont();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.IMAGE_JPEG);
       
        
        
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }
    @GetMapping("/auto-estilo2/{id}")
    public ResponseEntity<byte[]> autoEstilo2 (@PathVariable String id){
        auto car = autoServ.getOne(id);
        
       byte[] imagen= car.getEstilo2().getCont();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.IMAGE_JPEG);
       
        
        
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }
    @GetMapping("/auto-estilo3/{id}")
    public ResponseEntity<byte[]> autoEstilo3 (@PathVariable String id){
        auto car = autoServ.getOne(id);
        
       byte[] imagen= car.getEstilo3().getCont();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.IMAGE_JPEG);
       
        
        
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }
*/
    
    
}
