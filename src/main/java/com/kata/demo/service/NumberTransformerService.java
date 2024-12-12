package com.kata.demo.service;

import org.springframework.stereotype.Service;

import com.kata.demo.exception.NumberOutRangeException;

@Service
public class NumberTransformerService {

    public String transformerNumber(int number) {
    	
    	if(number>=0 && number<=100) {
    	      String resultat = "";


    	        if (number % 3 == 0) {
    	            resultat = resultat.concat("FOO");
    	        }

    	        if (number % 5 == 0) {
    	            resultat = resultat.concat("BAR");
    	        }
    	        
    	        
    	        for(int i =0; i<String.valueOf(number).length();i++) {
    	        	
    	        	if(String.valueOf(number).charAt(i) == '5') {
    	        		resultat = resultat.concat("BAR");
    	        	}
    	        	
    	        	if (String.valueOf(number).charAt(i) == '3') {
    	               	resultat = resultat.concat("FOO");
    	            }
    	        }
    	  

    	        if (String.valueOf(number).contains("7")) {
    	            resultat = resultat.concat("QUIX");
    	        }

    	        if (resultat.isEmpty()) {
    	            return String.valueOf(number);
    	        }

    	        return resultat;
    		
    	}else {
    		throw new NumberOutRangeException();
    	}

  
    }
	
}




