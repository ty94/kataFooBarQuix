package com.kata.demo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.demo.service.NumberTransformerService;

@RestController
@RequestMapping("/transform")
public class TransformationController {

	 	@Autowired
	    private NumberTransformerService transformerService;
	 	
	 
	    @GetMapping("/{number}")
	    public ResponseEntity<String> transformNumber(@PathVariable int number){
	    	

	        return ResponseEntity.ok(transformerService.transformerNumber(number));
	    }
}
