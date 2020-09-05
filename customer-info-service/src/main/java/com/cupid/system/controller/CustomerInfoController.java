package com.cupid.system.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cupid.system.CustomerInfoException;
import com.cupid.system.ServiceResponse;
import com.cupid.system.entity.CustomerInfoEntity;
import com.cupid.system.model.CustomerInfo;
import com.cupid.system.service.CustomerInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class contains API's related to customer information in Private Banking
 * System
 * 
 * @author Santhosh
 *
 */
@Api(value = "Customer profile Information")
@RestController
@RequestMapping(value = "/v1/customer")
public class CustomerInfoController {

	private static Logger log = LoggerFactory.getLogger(CustomerInfoController.class);

	@Autowired
	private CustomerInfoService customerInfoService;

	@ApiOperation(value = "Method to Save Customer Profile Information", notes = "Method to Save Customer Profile Information", response = CustomerInfoEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Created Successfully", response = CustomerInfoEntity.class),
			@ApiResponse(code = 400, message = "Invalid REequest Payload", response = String.class),
			@ApiResponse(code = 500, message = "Server Error", response = String.class) })
	@PostMapping(value = "/info/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse<CustomerInfoEntity> saveCustomerInfoDetails(
			@RequestBody @Valid CustomerInfo customerInfoRequest) throws CustomerInfoException {
		log.info("Inside saveCustomerInfoDetails method");
		CustomerInfoEntity responeEntity = customerInfoService.saveCustomerInfoDetails(customerInfoRequest);
		if (responeEntity != null) {
			return new ServiceResponse<>(HttpStatus.CREATED.value(), "Created Successfully", null,
					responeEntity);
		}
		return new ServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(), null, responeEntity);
	}

	@ApiOperation(value = "Method to Retrieve particular Customer Profile", notes = "Method to Retrieve particular Customer Profile", response = CustomerInfoEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrived Successfully", response = CustomerInfoEntity.class),
			@ApiResponse(code = 400, message = "Invalid REequest Payload", response = String.class),
			@ApiResponse(code = 500, message = "Server Error", response = String.class) })
	@GetMapping(value = "/{customerId}/retriveCustomerInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse<CustomerInfo> retrieveCustomerInfo(@PathVariable Integer customerId) throws CustomerInfoException {
		log.info("Retrieving customer Information for Customer ID:{} ", customerId);
		CustomerInfo responeEntity = customerInfoService.retrieveCustomerInfo(customerId);

		if (responeEntity != null) {
			return new ServiceResponse<>(HttpStatus.OK.value(),"Retrived Successfully", null, responeEntity);
		}
		return new ServiceResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name(), null,
				responeEntity);
	}
	
	@ApiOperation(value = "Method to Update Customer Profile Information", notes = "Method to Update Customer Profile Information", response = CustomerInfoEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated Successfully", response = CustomerInfoEntity.class),
			@ApiResponse(code = 400, message = "Invalid REequest Payload", response = String.class),
			@ApiResponse(code = 500, message = "Server Error", response = String.class) })
	@PutMapping(value = "/info/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse<CustomerInfoEntity> updateCustomerInfoDetails(
			@RequestBody @Valid CustomerInfo customerInfoRequest) throws CustomerInfoException {
		log.info("Inside updateCustomerInfoDetails method");
		CustomerInfoEntity responeEntity = customerInfoService.updateCustomerInfoDetails(customerInfoRequest);
		if (responeEntity != null) {
			return new ServiceResponse<>(HttpStatus.ACCEPTED.value(), "Details Updated Successfully", null,
					responeEntity);
		}
		return new ServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(), null, responeEntity);
	}

}
