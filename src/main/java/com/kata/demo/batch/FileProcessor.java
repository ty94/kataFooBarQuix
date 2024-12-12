package com.kata.demo.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.kata.demo.service.NumberTransformerService;



public class FileProcessor  implements ItemProcessor<String, String> {
	
	@Autowired
	private NumberTransformerService numberTransformerService;

	@Override
	public String process(String item) throws Exception {
		
		String result =item +"   "+numberTransformerService.transformerNumber(Integer.parseInt(item));
		
		if(item.length()==2) {
			result = item +"  "+numberTransformerService.transformerNumber(Integer.parseInt(item));
		}
		
		if(item.length()==3) {
			result = item +" "+numberTransformerService.transformerNumber(Integer.parseInt(item));
		}

		return result;
	}

}

