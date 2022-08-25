package com.CustomerWordFile.RestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.CustomerWordFile.Dto.CustomerDto;

public class CustomerTemplate {

	public static String GET_CUSTOMER_BY_ID_API = "http://localhost:8082/customers";
	public static String GET_LOAN_BY_ID_API = "http://localhost:8081/getloanbyid";
	
	static RestTemplate restTemplate = new RestTemplate();
	
	public static CustomerDto getCustomerByIdAPI(Long id) {
		CustomerDto customerDto = new CustomerDto();
		ResponseEntity<String> json = restTemplate.getForEntity(GET_CUSTOMER_BY_ID_API+"/"+id, String.class);
		String str = json.getBody().replace("{", "").replace("}", "").replaceAll("\"", "");
		String[] customer = str.split(",");
		for(int i=0; i<3; i++) {
			customer[i] = customer[i].split(":")[1];
		}
		customerDto.setId(Long.valueOf(customer[0]));
		customerDto.setFirstName(customer[1]);
		customerDto.setSecondName(customer[2]);
		customerDto.setLoan(getLoanByIdAPI(id));
		return customerDto;
	}
	
	public static Long getLoanByIdAPI(Long id) {
		ResponseEntity<String> json = restTemplate.getForEntity(GET_LOAN_BY_ID_API+"/"+id, String.class);
		String str = json.getBody().replace("{", "").replace("}", "").replaceAll("\"", "");
		String[] customer = str.split(",");
		Long loan = Long.valueOf(customer[1].split(":")[1]);
		return loan;
	}
}
