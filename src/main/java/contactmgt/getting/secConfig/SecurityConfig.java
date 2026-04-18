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
    UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public CommandLineRunner intitData(UserDetailsService userDetailsService) {
        return args -> {
            JdbcUserDetailsManager jdbcUserDetailsManager = (JdbcUserDetailsManager) userDetailsService;
            int[] userNumber = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            JdbcUserDetailsManager userDetailsManager =
                    new JdbcUserDetailsManager(dataSource);
            for (int usernumber : userNumber) {
                UserDetails user = User.withUsername("user" + usernumber)
                        .password(passwordEncoder().encode("password" + usernumber))
                        .roles("USER").build();
                userDetailsManager.createUser(user);
            }
            UserDetails admin1 = User.withUsername("admin1")
                    .password(passwordEncoder().encode("admin1"))
                    .roles("ADMIN").build();
            UserDetails admin2 = User.withUsername("admin2")
                    .password(passwordEncoder().encode("admin2"))
                    .roles("ADMIN").build();
            userDetailsManager.createUser(admin1);
            userDetailsManager.createUser(admin2);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
