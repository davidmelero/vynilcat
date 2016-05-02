package com.vynilcat.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vynilcat.config.authentication.jwt.JWTAuthenticationProvider;
import com.vynilcat.sys.ContactMessage;

@Service
public class RestService {
	
	public ResponseEntity<ContactMessage> send(ContactMessage cm) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		ResponseEntity<ContactMessage> response = null;
		try{
			String username = cm.getUser().getUserName();
	        String token = "Bearer " + this.signAndSerializeJWT(getClaimsSet(username), JWTAuthenticationProvider.getSharedSecret());
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", token);
			List<MediaType> accepts = new ArrayList<MediaType>();
			accepts.add(MediaType.APPLICATION_JSON);
			headers.setAccept(accepts);
			HttpEntity<Object> entity = new HttpEntity<Object>(cm, headers);
			String url = "http://192.168.1.101:8080/vynilcat/rest/sendContact/{username}";
			
			response = restTemplate.exchange(url,HttpMethod.POST,entity,ContactMessage.class,username);
					

		}catch(HttpClientErrorException exc){
			exc.printStackTrace();
		}
		
		return response;
	}
	
	private JWTClaimsSet getClaimsSet(String username){
		Date now = new Date();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject(username)
				.issueTime(now)
				.issuer("vynilcat-api.com")
				.expirationTime(new DateTime().plusHours(1).toDate())
				.notBeforeTime(now)
				.build();
        
        return claimsSet;
	}
	
    private String signAndSerializeJWT(JWTClaimsSet claimsSet, byte[] secret) {
        JWSSigner signer;
		try {
			signer = new MACSigner(secret);
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            
            return signedJWT.serialize();
        } catch (KeyLengthException e1) {
			e1.printStackTrace();
		} catch(JOSEException e) {
            e.printStackTrace();
        }
		
		return null;
    }
	
}
