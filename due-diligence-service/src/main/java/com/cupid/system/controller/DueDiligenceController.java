/**
 * 
 */
package com.cupid.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cupid.system.DueDiligenceConfiguration;
import com.cupid.system.ServiceResponse;
import com.cupid.system.entity.DueDiligenceStatusEntity;
import com.cupid.system.model.CustomerDueDiligenceStatus;
import com.cupid.system.service.DueDiligenceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author San
 *
 */
@Api(value = "Customer profile Information")
@RestController
@EnableHystrix
@RequestMapping(value = "/v1/customer")
public class DueDiligenceController {

	private static Logger log = LoggerFactory.getLogger(DueDiligenceController.class);

	@Autowired
	private DueDiligenceService dueDiligenceService;

	@Autowired
	private DueDiligenceConfiguration dueDiligenceConfiguration;

	@ApiOperation(value = "Method to Retreive Due Diligence Report for a Customer", notes = "Method to Retreive Due Diligence Report for a Customer", response = DueDiligenceStatusEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrived Successfully", response = DueDiligenceStatusEntity.class),
			@ApiResponse(code = 400, message = "Invalid REequest Payload", response = String.class),
			@ApiResponse(code = 500, message = "Server Error", response = String.class)

	})
	@GetMapping(value = "/{customerId}/retriveDueDiligenceStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "retreiveDueDiligenceStatusFallback")
	public ServiceResponse<CustomerDueDiligenceStatus> retreiveDueDiligenceStatus(@PathVariable Integer customerId) {
		log.info("Retrieving Due Diligence Status for customer ID: {}" , customerId);
		CustomerDueDiligenceStatus responeEntity = dueDiligenceService.retreiveDueDiligenceStatus(customerId);
		
		responeEntity.getDueDiligenceStatus();// Added to simulate fault tolerance incase if we pass data not on DB
		if (responeEntity != null) {
			return new ServiceResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), null,
					responeEntity);
		}
		return new ServiceResponse<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name(),
				null, responeEntity);
	}

	public ServiceResponse<CustomerDueDiligenceStatus> retreiveDueDiligenceStatusFallback(@PathVariable Integer customerId) {
		CustomerDueDiligenceStatus fallbackResponeEntity = CustomerDueDiligenceStatus.builder()
				.id(customerId)
				.dueDiligenceStatus(dueDiligenceConfiguration.getDueDiligenceStatus())
				.approvalStatus(dueDiligenceConfiguration.getApprovalStatus()).build();
		return new ServiceResponse<>(HttpStatus.OK.value(), "Default Status Expect change in status",
				null, fallbackResponeEntity);

	}
	
	@ApiOperation(value = "Method to Compute Due Diligence Report for a Customer", notes = "Method to Compute Due Diligence Report for a Customer", response = CustomerDueDiligenceStatus.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrived Successfully", response = CustomerDueDiligenceStatus.class),
			@ApiResponse(code = 400, message = "Invalid REequest Payload", response = String.class),
			@ApiResponse(code = 500, message = "Server Error", response = String.class)

	})
	@GetMapping(value = "/{customerId}/computeDueDiligence", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse<CustomerDueDiligenceStatus> computeDueDiligence(@PathVariable Integer customerId) {
		log.info("Computing Due Diligence Status for customer ID:{} " , customerId);
		CustomerDueDiligenceStatus responeEntity = dueDiligenceService.computeDueDiligence(customerId);				
			return new ServiceResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), null,
					responeEntity);
		
	}

}
