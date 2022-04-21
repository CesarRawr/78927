package com.access.control.services;

import java.security.Key;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;

import java.util.Base64;
import java.security.NoSuchAlgorithmException;

public class TokenService {
	public String generateToken() throws NoSuchAlgorithmException {
		//Creating a KeyGenerator object
      	KeyGenerator keyGen = KeyGenerator.getInstance("DES");

     	//Creating a SecureRandom object
     	 SecureRandom secRandom = new SecureRandom();

     	//Initializing the KeyGenerator
      	keyGen.init(secRandom);

      	//Creating/Generating a key
      	Key key = keyGen.generateKey();
      	String stringKey = Base64.getEncoder().encodeToString(key.getEncoded());

      	return stringKey;
	}
}
