package fr.projetiwa.covid_alert_position_ms.Config;

import fr.projetiwa.covid_alert_position_ms.models.PositionService;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.LinkedList;

@Configuration
public class AppConfig {
    @Bean("PositionService")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PositionService positionService(){
        return new PositionService(new LinkedList<>());
    }

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    // Specifies the session authentication strategy
    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
}
