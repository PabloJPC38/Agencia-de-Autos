package com.car_agency.car_agency.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car_agency.car_agency.entidades.auto;
import com.car_agency.car_agency.entidades.imagen;
import com.car_agency.car_agency.repositorios.autoRepository;

@Service
public class autoServices {
    
    @Autowired
    private autoRepository autoRepo;
    @Autowired
    private imagenServices imgServ;

    @Transactional
    public void guardarDatos(String name,String marca,String precio,String disenio,String performance,String exclusividad, MultipartFile image,List<String> colores,List<MultipartFile> estilo1,List<MultipartFile> estilo2,List<MultipartFile> estilo3,List<MultipartFile> estilo4,List<MultipartFile> estilo5,MultipartFile portada) throws Exception{
        
        auto car = new auto();
        car.setName(name);
        car.setMarca(marca);
        car.setPrecio(precio);
        car.setDisenio(disenio);
        car.setPerformance(performance);
        car.setExclusividad(exclusividad);
        car.setColores(colores);
        imagen Image = imgServ.guardar(image);
        car.setImg(Image);
        imagen Portada = imgServ.guardar(portada);
        car.setPortada(Portada);

        List<imagen> style1 = new ArrayList<imagen>();

                for (MultipartFile e1 : estilo1) {
                    style1.add(imgServ.guardar(e1));
                }
                car.setEstilo1(style1);
        
        
        List<imagen> style2 = new ArrayList<imagen>();

            for (MultipartFile e2 : estilo2) {
                    style2.add(imgServ.guardar(e2));
            }
            car.setEstilo2(style2);
        
        
        List<imagen> style3 = new ArrayList<imagen>();

            for (MultipartFile e3 : estilo3) {
                style3.add(imgServ.guardar(e3));
            }
            car.setEstilo3(style3);
        
        if (colores.size()!=3){
            
            
            List<imagen> style4 = new ArrayList<imagen>();

                for (MultipartFile e4 : estilo4) {
                    style4.add(imgServ.guardar(e4));
                }
                car.setEstilo4(style4);
        
            
            List<imagen> style5 = new ArrayList<imagen>();

                for (MultipartFile e5 : estilo5) {
                    style5.add(imgServ.guardar(e5));
                }
                car.setEstilo5(style5);    
        }
        autoRepo.save(car);
        System.out.println("Guardado!!");
        
    }


    @Transactional
    public void modificarDatos(String id ,String name,String marca,String precio,String disenio,String performance,String exclusividad, MultipartFile image,List<String> colores,List<MultipartFile> estilo1,List<MultipartFile> estilo2,List<MultipartFile> estilo3,List<MultipartFile> estilo4,List<MultipartFile> estilo5,MultipartFile portada) throws Exception{
        
        Optional <auto> respuesta = autoRepo.findById(id);
        if (respuesta.isPresent()){

            auto car = respuesta.get();
            System.out.println("COLORES:"+colores);
            System.out.println("COLORESSSS:"+car.getColores());
            if (!name.isEmpty()){
                
                car.setName(name);
            }
            
            if (image.getSize() > 0) {
                imagen Img = imgServ.actualizar(image, car.getImg().getId());
                car.setImg(Img);
            }
            
            if(!colores.isEmpty()){
                car.setColores(colores);
            }
            
            if(!marca.isEmpty()){
                car.setMarca(marca);
            }
            
            if(!precio.isEmpty()){
                car.setPrecio(precio);
            }
            
            if(!disenio.isEmpty()){
                car.setDisenio(disenio);
            }
            
            if(!performance.isEmpty()){
                car.setPerformance(performance);
            }
            
            if(!exclusividad.isEmpty()){
                car.setExclusividad(exclusividad);
            }
            
            if (estilo1.stream().anyMatch(e1 -> e1.getSize() > 0)){

                List<imagen> style1 = new ArrayList<imagen>();
                int index = 0;

                for (MultipartFile e1 : estilo1) {
                    
                    style1.add(imgServ.actualizar(e1,car.getEstilo1().get(index).getId()));

                    index++;
                }
                car.setEstilo1(style1);
                
            }
            
            if (estilo2.stream().anyMatch(e2 -> e2.getSize() > 0)){
                
                List<imagen> style2 = new ArrayList<imagen>();
                int index = 0;

                for (MultipartFile e2 : estilo2) {

                    style2.add(imgServ.actualizar(e2,car.getEstilo2().get(index).getId()));

                    index++;
                }
                car.setEstilo2(style2);
            }
            
            if (estilo3.stream().anyMatch(e3 -> e3.getSize() > 0)){
                
                List<imagen> style3 = new ArrayList<imagen>();
                int index = 0;

                for (MultipartFile e3 : estilo3) {
                    
                    style3.add(imgServ.actualizar(e3,car.getEstilo3().get(index).getId()));

                    index++;
                }
                car.setEstilo3(style3);
            }
            
            if (estilo4.stream().anyMatch(e4 -> e4.getSize() > 0)){
                
                List<imagen> style4 = new ArrayList<imagen>();
                int index = 0;

                for (MultipartFile e4 : estilo4) {
                    
                    style4.add(imgServ.actualizar(e4,car.getEstilo4().get(index).getId()));

                    index++;
                }
                car.setEstilo4(style4);   
            }

            if (estilo5.stream().anyMatch(e5 -> e5.getSize() > 0)){
                
                List<imagen> style5 = new ArrayList<imagen>();
                int index = 0;

                for (MultipartFile e5 : estilo5) {
                    
                    style5.add(imgServ.actualizar(e5,car.getEstilo5().get(index).getId()));

                    index++;
                }
                car.setEstilo5(style5); 
            }

            if(portada.getSize() > 0){

                imagen port = imgServ.actualizar(portada, car.getPortada().getId());
                car.setPortada(port);
            }

            autoRepo.save(car);
            System.out.println("Modificado!!");
        }
        
    }

    @Transactional
    public void modificarEstilo(String id,int color){

        Optional <auto> respuesta = autoRepo.findById(id);
        if (respuesta.isPresent()){

            auto car = respuesta.get();
            car.setColor(color);
            autoRepo.save(car);

        }


    }

    @Transactional
    public void eliminarDatos(String id){
        
        
        Optional <auto> respuesta = autoRepo.findById(id);
        
        if (respuesta.isPresent()){
            
            auto car = respuesta.get();
            
            imgServ.eliminarImagen(car.getImg().getId());
            imgServ.eliminarImagen(car.getPortada().getId());
            imgServ.eliminarImagenes(car.getEstilo1());
            imgServ.eliminarImagenes(car.getEstilo2());
            imgServ.eliminarImagenes(car.getEstilo3());
            
            if (car.getColores().size() > 3){
            
                imgServ.eliminarImagenes(car.getEstilo4());
                imgServ.eliminarImagenes(car.getEstilo5());
            }
        }
        
        autoRepo.deleteById(id);
        System.out.println("Eliminado!!");
    }

    @Transactional
    public List<auto> listarDatos(){

        return autoRepo.findAll();
    }

    public auto getOne(String id){
        return autoRepo.getOne(id);
    }

    public Optional<auto> findById(String id){

        return autoRepo.findById(id);
    }

    

}
