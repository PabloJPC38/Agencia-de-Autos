package com.car_agency.car_agency.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car_agency.car_agency.entidades.imagen;
import com.car_agency.car_agency.repositorios.imagenRepository;

@Service
public class imagenServices {

    @Autowired
    private imagenRepository imagenRepo;
    
    public imagen guardar(MultipartFile img) throws Exception{
        if (img != null) {
            try {
                
                imagen Img = new imagen();
                
                Img.setCont(img.getBytes());
                Img.setName(img.getOriginalFilename());
                return imagenRepo.save(Img);
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    

    public imagen actualizar(MultipartFile img, String idImagen) throws Exception{
         if (img != null) {
            try {
                
                imagen Img = new imagen();
                
                if (idImagen != null) {
                    Optional<imagen> respuesta = imagenRepo.findById(idImagen);
                    
                    if (respuesta.isPresent()) {
                        Img = respuesta.get();
                    }
                }
                
                
                Img.setCont(img.getBytes());
                Img.setName(img.getOriginalFilename());
                
                return imagenRepo.save(Img);
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
        
    }
    
    @Transactional(readOnly = true)
	public List<imagen> listarTodos() {
		return imagenRepo.findAll();
	}

    @Transactional
    public imagen findById(String id){

        Optional<imagen> img = imagenRepo.findById(id);
        
        if (img.isPresent()){
            return img.get();
        }
        return null;
    }
    
       
}
