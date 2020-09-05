/**
 * 
 */
package com.cupid.system.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cupid.system.ServiceResponse;
import com.cupid.system.model.CustomerDueDiligenceStatus;

/**
 * @author San
 *
 */
@FeignClient(name = "cupid-gateway-service", contextId = "due-diligence-service")
@RibbonClient(name = "due-diligence-service")
public interface DueDiligenceService {

	@RequestMapping(value = "/due-diligence-service/v1/customer/{customerId}/retriveDueDiligenceStatus")
	public ServiceResponse<CustomerDueDiligenceStatus> retreiveDueDiligenceStatus(@PathVariable Integer customerId);
	
	@RequestMapping(value = "/due-diligence-service/v1/customer/{customerId}/computeDueDiligence")
	public ServiceResponse<CustomerDueDiligenceStatus> computeDueDiligence(@PathVariable Integer customerId);
}
