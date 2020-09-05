/**
 * 
 */
package com.cupid.system.service;

import com.cupid.system.CustomerInfoException;
import com.cupid.system.entity.CustomerInfoEntity;
import com.cupid.system.model.CustomerInfo;

/**
 * @author San
 *
 */
public interface CustomerInfoService {
	
	CustomerInfoEntity saveCustomerInfoDetails(CustomerInfo customerInfoRequest) throws CustomerInfoException;
	
	CustomerInfo retrieveCustomerInfo(Integer customerId) throws CustomerInfoException;
	
	CustomerInfoEntity updateCustomerInfoDetails(CustomerInfo customerInfoRequest) throws CustomerInfoException;



}
