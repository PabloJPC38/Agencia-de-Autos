package com.car_agency.car_agency.controladores;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.car_agency.car_agency.entidades.auto;
import com.car_agency.car_agency.entidades.imagen;
import com.car_agency.car_agency.servicios.autoServices;

@Controller
@RequestMapping("/")
public class autoController {
 
    @Autowired
    private autoServices autoServ;

    @GetMapping("/index")

    public String Index(){

        return "index.html";
    }


    @GetMapping("/registrar")

    public String registrarUsuario(){

        return "registrar.html";
    }

    @GetMapping("/")
    public String paginaPrincipal(ModelMap model){

        List<auto> autos = autoServ.listarDatos();
        model.addAttribute("autos",autos);

        return "principal.html";
    }

    @GetMapping("lista")
    public String listarAutos(ModelMap model){

        List<auto> autos = autoServ.listarDatos();
        model.addAttribute("autos",autos);

        return "lista.html";
    }

    @PostMapping("lista/nuevo")
    public String guardarDatos(@RequestParam String name, String marca,String precio,String disenio,String performance,String exclusividad, MultipartFile image,@RequestParam List<String> colores,@RequestParam List<MultipartFile> estilo1,List<MultipartFile> estilo2,List<MultipartFile> estilo3,List<MultipartFile> estilo4,List<MultipartFile> estilo5,MultipartFile portada) throws Exception{

        autoServ.guardarDatos(name,marca,precio,disenio,performance,exclusividad,image,colores,estilo1,estilo2,estilo3,estilo4,estilo5,portada);
        return "redirect:../lista";

    }

    @PostMapping("lista/actualizar")
    public String actualizarDatos(@RequestParam String id,String name, String marca,String precio,String disenio,String performance,String exclusividad, MultipartFile image,@RequestParam List<String> colores, List<MultipartFile> estilo1,List<MultipartFile> estilo2,List<MultipartFile> estilo3,List<MultipartFile> estilo4,List<MultipartFile> estilo5,MultipartFile portada) throws Exception{
        
        autoServ.modificarDatos(id, name,marca,precio,disenio,performance,exclusividad,image,colores,estilo1,estilo2,estilo3,estilo4,estilo5,portada);
        return "redirect:../lista";
    }


    @PostMapping("lista/eliminar")
    public String eliminarDatos(String id){

        autoServ.eliminarDatos(id);
        return "redirect:../lista";
    }
    
    @GetMapping("auto/{id}")
    public ModelAndView autoDetalles(@PathVariable String id){
        
        auto car = autoServ.getOne(id);
        int cant = 5; 
        car.getEstilo1().sort(Comparator.comparing(imagen::getName));
        car.getEstilo2().sort(Comparator.comparing(imagen::getName));
        car.getEstilo3().sort(Comparator.comparing(imagen::getName));
        
        if (car.getColores().size() == 3){

            cant = 3;
        }
        
        else{
            
            car.getEstilo4().sort(Comparator.comparing(imagen::getName));
            car.getEstilo5().sort(Comparator.comparing(imagen::getName));
        }
        
        //System.out.println("CANT:"+cant);
        return new ModelAndView("auto").addObject("auto", car).addObject("cant", cant);

    }

    @GetMapping("estilo/{id}/{color}")
    public String autoColor(@PathVariable String id,@PathVariable int color) throws Exception{

        autoServ.modificarEstilo(id,color);      
        return "redirect: ../../../../auto/"+id+"#estilo";
    }

}
