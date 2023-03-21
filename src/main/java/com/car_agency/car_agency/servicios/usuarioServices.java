package com.car_agency.car_agency.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.car_agency.car_agency.entidades.usuario;
import com.car_agency.car_agency.repositorios.userRepository;

@Service
public class usuarioServices implements UserDetailsService {

    @Autowired
    private userRepository userRepost;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        usuario user = userRepost.Buscar_Por_Email(email);

        if (user != null)
        {

            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p;
//                        new SimpleGrantedAuthority("ROLE_"+usuario.getRol().USER.toString()); //USER

            if (user.getActivo())
            {

                p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());

            } else
            {

                p = new SimpleGrantedAuthority("ROLE_" + "USER");

            }

            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", user);

            return new User(user.getEmail(), user.getPassword(), permisos);

        }
    
        else{
        
            return null;
    }

}
}
