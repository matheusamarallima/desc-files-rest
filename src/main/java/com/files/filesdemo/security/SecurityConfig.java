package com.files.filesdemo.security;

import com.files.filesdemo.config.AudienceValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/**").permitAll() // Permite acesso livre a URLs públicas
                .antMatchers("/files/upload").permitAll() // Permite acesso livre ao endpoint /files/upload
                .anyRequest().authenticated() // Exige autenticação para outras URLs
//                .and()
//                .formLogin() // Configuração de login de formulário
//                .loginPage("/login") // Página personalizada de login (opcional)
//                .defaultSuccessUrl("/dashboard") // Página de redirecionamento após o login bem-sucedido
//                .permitAll() // Permite acesso livre à página de login
//                .and()
//                .logout() // Configuração de logout
//                .logoutUrl("/logout") // URL para realizar logout
//                .logoutSuccessUrl("/login?logout") // Página de redirecionamento após logout bem-sucedido
//                .permitAll() // Permite acesso livre à página de logout
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .csrf().disable(); // Configuração CSRF ativada (proteção contra ataques CSRF)
    }

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
}

