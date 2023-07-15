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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.car_agency.car_agency.entidades.usuario;
import com.car_agency.car_agency.repositorios.userRepository;
import Enum.Rol;

@Service
public class usuarioServices implements UserDetailsService {

    @Autowired
    private userRepository userRepost;

    @Transactional
    public void guardarUsuario(String name, String email, String password) {
        usuario user = new usuario();
        user.setActivo(true);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRol(Rol.USER);
        userRepost.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        usuario user = userRepost.Buscar_Por_Email(email);

        if (user != null) {
            List<GrantedAuthority> permisos = new ArrayList<GrantedAuthority>();
            GrantedAuthority p;
            if (user.getActivo()) {
                p = new SimpleGrantedAuthority("ROLE_"+user.getRol().toString());
            } else {
                p = new SimpleGrantedAuthority("USER");
            }
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", user);

            return new User(user.getEmail(), user.getPassword(), permisos);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
