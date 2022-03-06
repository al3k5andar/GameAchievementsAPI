package spring.gameachievementsapi.security.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring.gameachievementsapi.security.authentication.CustomAuthentication;

import java.util.logging.Logger;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger logger= Logger.getLogger(CustomAuthenticationProvider.class.getName());

    @Value("${key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authenticationKey= authentication.getName();
        logger.info("The key: "+ authenticationKey);

        if(authenticationKey.equals(key))
            return new CustomAuthentication(null, null, null);
        else
            throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
