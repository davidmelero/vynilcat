package com.vynilcat.config.authentication.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;

public class JWTAuthenticationProvider implements AuthenticationProvider {
    
    private JWSVerifier verifier;
    
//    @Autowired
//    private UserRepository userRepository;
    
	public static byte[] getSharedSecret(){
//      SecureRandom random = new SecureRandom();
//      byte[] sharedSecret = new byte[32];
//      random.nextBytes(sharedSecret);
      
      return new byte[]{14, 16, 10, -121, -65, -45, 61, 79, 88, 81, -123, -123, -70, 51, 54, -104, 65, -18, 79, -114, -91, 82, -79, -49, 20, -48, 45, -88, -92, -110, -49, 54};
	}
	
    public JWTAuthenticationProvider() {
    	
//    	SecureRandom random = new SecureRandom();
//    	byte[] sharedSecret = new byte[32];
//    	random.nextBytes(sharedSecret);
        
    	try {
			this.verifier = new MACVerifier(getSharedSecret());
		} catch (JOSEException e) {
			e.printStackTrace();
		}
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTToken jwtToken = (JWTToken) authentication;
        JWT jwt = jwtToken.getJwt();
        
        // Check type of the parsed JOSE object
        if (jwt instanceof PlainJWT) {
            handlePlainToken((PlainJWT) jwt);
        } else if (jwt instanceof SignedJWT) {
            handleSignedToken((SignedJWT) jwt);
        } else if (jwt instanceof EncryptedJWT) {
            handleEncryptedToken((EncryptedJWT) jwt);
        }
        
        Date referenceTime = new Date();

        JWTClaimsSet claims = jwtToken.getClaims();
        
        Date expirationTime = claims.getExpirationTime();
        if (expirationTime == null || expirationTime.before(referenceTime)) {
            throw new RuntimeException("The token is expired");
        }
        
        Date notBeforeTime = claims.getNotBeforeTime();
        if (notBeforeTime == null || notBeforeTime.after(referenceTime)) {
            throw new RuntimeException("Not before is after sysdate");
        }
        
        String issuerReference = "vynilcat-api.com";
        String issuer = claims.getIssuer();
        if (!issuerReference.equals(issuer)) {
            throw new RuntimeException("Invalid issuer");
        }
        
//      LoginUsuario user = userRepository.findByUsername(jwtToken.getClaims().getSubject());
        
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
        jwtToken.setAuthorities(authorities);
        jwtToken.setAuthenticated(true);
        
        return jwtToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTToken.class.isAssignableFrom(authentication);
    }
    
    private void handlePlainToken(PlainJWT jwt) {
        throw new RuntimeException("Unsecured plain tokens are not supported");
    }
    
    private void handleSignedToken(SignedJWT jwt) {
        try {
            if (!jwt.verify(verifier)) {
                throw new RuntimeException("Signature validation failed");
            }
        } catch (JOSEException e) {
            throw new RuntimeException("Signature validation failed");
        }
    }
    
    private void handleEncryptedToken(EncryptedJWT jwt) {
        throw new UnsupportedOperationException("Unsupported token type");
    }
    
}
