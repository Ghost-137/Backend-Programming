package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private UserDetailService userDetailService;

    public WebSecurityConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain configure (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).
        formLogin(formlogin -> formlogin.loginPage("/login").defaultSuccessUrl("/booklist", true).permitAll()).
        logout(logout -> logout.permitAll());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

/*

This is for the in-memory user details service. It defines two users: "user" with the role "USER" and "admin" with the roles "USER" and "ADMIN". The configure method sets up the security filter chain, requiring authentication for all requests, and configures form-based login with a custom login page and a default success URL. It also allows all users to access the logout functionality.

public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).formLogin(formlogin -> formlogin.loginPage("/login").defaultSuccessUrl("/booklist", true).permitAll()).logout(logout -> logout.permitAll());
        return http.build();
        
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER", "ADMIN").build();
        users.add(user);
        users.add(admin);
        return new InMemoryUserDetailsManager(users);
    }
}

*/


