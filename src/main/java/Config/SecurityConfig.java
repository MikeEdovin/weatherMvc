package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;


@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public PasswordEncoder getPwEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    UserDetailsService authentication(PasswordEncoder pwEncoder){
        UserDetails peter = User.builder()
                .username("peter")
                .password(pwEncoder.encode("ppassword"))
                .roles("USER")
                .build();
        UserDetails jodie = User.builder()
                .username("jodie")
                .password(pwEncoder.encode("jpassword"))
                .roles("USER", "ADMIN")
                .build();
        System.out.println(" >>> Peter's password: " + peter.getPassword());
        System.out.println(" >>> Jodie's password: " + jodie.getPassword());
        return new InMemoryUserDetailsManager(peter, jodie);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/h2-console/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                //.antMatchers(HttpMethod.GET,"/**").hasAnyRole("USER","ADMIN")
                //.antMatchers(HttpMethod.POST,"/**").hasAnyRole("USER","ADMIN")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
        return http.build();
    }



}


