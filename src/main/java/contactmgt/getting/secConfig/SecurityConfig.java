package contactmgt.getting.secConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    SecurityFilterChain defualtSecurityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/contacts/public/info").permitAll()
                        .anyRequest().authenticated());

        httpSecurity.sessionManagement(
                session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        httpSecurity.headers(headers -> headers.frameOptions(
                frameOptions -> frameOptions.sameOrigin()
        ));

        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
