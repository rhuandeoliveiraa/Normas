package br.ufg.normas.configuracao;


import br.ufg.normas.seguranca.ImplUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class  SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private ImplUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy((SessionCreationPolicy.NEVER));


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }



     @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("tk_auth","Content-Type"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}
