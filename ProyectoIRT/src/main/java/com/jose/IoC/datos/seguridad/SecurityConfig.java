package com.jose.IoC.datos.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jose.IoC.datos.servicios.Autenticacion;




@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	
	
	

    @Autowired
    private Autenticacion autenticacion;

    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(autenticacion);
    	
    	auth.authenticationProvider(provider);
    }   



    
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        	.antMatchers("/inicio").authenticated()
        	.antMatchers("/clientes").authenticated()
        	.antMatchers("/clientes/**").hasAuthority("ADMIN")
        	.antMatchers("/vehiculos").authenticated()
        	.antMatchers("/vehiculos/**").hasAuthority("ADMIN")
        	.antMatchers("/marcas").authenticated()
        	.antMatchers("/marcas/**").hasAuthority("ADMIN")
        	.antMatchers("/modelos").authenticated()
        	.antMatchers("/modelos/**").hasAuthority("ADMIN")
        	.antMatchers("/trabajos").authenticated()
        	.antMatchers("/trabajos/**").authenticated()
        	.antMatchers("/usuarios").hasAuthority("ADMIN")
        	.antMatchers("/usuarios/**").hasAuthority("ADMIN")
        	.antMatchers("/elegirVehiculo").hasAuthority("ADMIN")
        	.antMatchers("/comprobarVehiculo").hasAuthority("ADMIN")
        	.antMatchers("/elegirCliente").hasAuthority("ADMIN")
        	.antMatchers("/comprobarCliente").hasAuthority("ADMIN")
	        .and()    	
        .formLogin()
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/inicio")
            .failureUrl("/login?error=true")
            .usernameParameter("usuario")
            .passwordParameter("password")
            .and()
        .logout()
        	.permitAll()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .and()
        .csrf().disable();

    }

}