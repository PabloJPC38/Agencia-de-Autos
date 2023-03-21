package com.car_agency.car_agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.car_agency.car_agency.servicios.usuarioServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter{

    @Autowired
    private usuarioServices userServ;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        
        auth.userDetailsService(userServ)
                .passwordEncoder(new BCryptPasswordEncoder());
    
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{

        http
                .authorizeRequests().antMatchers("/admin/*").hasAnyRole("ADMIN","PERIOD")
                .antMatchers("/ccs/*","/js/*","/img/*","/**")
                .permitAll()
                .and().formLogin()
                .loginPage("/")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and().csrf()
                .disable();
                
                    
        
    }
}
    

