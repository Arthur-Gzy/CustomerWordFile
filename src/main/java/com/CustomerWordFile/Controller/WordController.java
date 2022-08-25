package com.CustomerWordFile.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.CustomerWordFile.Dto.CustomerDto;
import com.CustomerWordFile.FileService.WordReader;
import com.CustomerWordFile.RestTemplate.CustomerTemplate;

@RestController
public class WordController {
	
	@GetMapping("/update/{id}")
	public String updateWordDocument(@PathVariable("id") Long id) {
		CustomerDto cus = new CustomerDto();
		cus = CustomerTemplate.getCustomerByIdAPI(id);
		WordReader.updateWordDoc(cus);
		return "Word Document Updated";
	}

}
