package spring.gameachievementsapi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import spring.gameachievementsapi.security.filters.CustomAuthenticationFilter;
import spring.gameachievementsapi.security.providers.CustomAuthenticationProvider;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationFilter filter;
    private final CustomAuthenticationProvider provider;

    public ProjectConfig(@Lazy CustomAuthenticationFilter filter,
                         CustomAuthenticationProvider provider) {
        this.filter = filter;
        this.provider = provider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.addFilterAt(filter, BasicAuthenticationFilter.class);

        http.authorizeRequests().anyRequest().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
