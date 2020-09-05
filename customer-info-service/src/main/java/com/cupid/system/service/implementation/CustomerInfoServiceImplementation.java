/**
 * 
 */
package com.cupid.system.service.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cupid.system.CustomerInfoException;
import com.cupid.system.ServiceResponse;
import com.cupid.system.entity.CustomerInfoEntity;
import com.cupid.system.model.CustomerInfo;
import com.cupid.system.model.CustomerDueDiligenceStatus;
import com.cupid.system.repository.CustomerInfoRepository;
import com.cupid.system.service.CustomerInfoService;
import com.cupid.system.service.DueDiligenceService;

/**
 * @author San
 *
 */
@Service
public class CustomerInfoServiceImplementation implements CustomerInfoService {

	private static Logger log = LoggerFactory.getLogger(CustomerInfoServiceImplementation.class);

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private DueDiligenceService dueDiligenceService;

	@Override
	public CustomerInfoEntity saveCustomerInfoDetails(CustomerInfo customerInfoRequest) throws CustomerInfoException {
		CustomerInfoEntity customerInfoEntity = null;
		if (customerInfoRequest.getFirstName() == null) {
			throw new CustomerInfoException("Please check the user details FirstName cannot be Empty!!!");
		}
		customerInfoEntity = CustomerInfoEntity.builder().firstName(customerInfoRequest.getFirstName())
				.lastName(customerInfoRequest.getLastName()).emailId(customerInfoRequest.getEmailId())
				.contactNo(customerInfoRequest.getContactNo()).dob(customerInfoRequest.getDob())
				.address(customerInfoRequest.getAddress()).state(customerInfoRequest.getState())
				.country(customerInfoRequest.getCountry()).pan(customerInfoRequest.getPan()).build();
	
		if(!customerInfoRepository.existsByPan(customerInfoEntity.getPan())) {
		customerInfoEntity = customerInfoRepository.saveAndFlush(customerInfoEntity);
		if (customerInfoEntity.getCustomerId() != null) {
			dueDiligenceService.computeDueDiligence(customerInfoEntity.getCustomerId());
		}
		}else {
			throw new CustomerInfoException("Looks like Customer with the PAN provided already exist in our system!!!");
		}

		return customerInfoEntity;
	}

	@Override
	public CustomerInfo retrieveCustomerInfo(Integer customerId) throws CustomerInfoException {
		Optional<CustomerInfoEntity> customerInfoEntity = customerInfoRepository.findByCustomerId(customerId);
		if(customerInfoEntity.isPresent()) {
		ServiceResponse<CustomerDueDiligenceStatus> dueDiligenceStatus = dueDiligenceService
				.retreiveDueDiligenceStatus(customerId);
		return buildCustomerInfoResponseDto(customerInfoEntity.get(), dueDiligenceStatus.getData());
		}else {
			throw new CustomerInfoException("Customer Details not present in our system!!!");
		}
	}

	private CustomerInfo buildCustomerInfoResponseDto(CustomerInfoEntity customerInfoEntity,
			CustomerDueDiligenceStatus dueDiligenceStatus) {
		return CustomerInfo.builder().firstName(customerInfoEntity.getFirstName())
				.lastName(customerInfoEntity.getLastName()).emailId(customerInfoEntity.getEmailId())
				.contactNo(customerInfoEntity.getContactNo()).dob(customerInfoEntity.getDob())
				.address(customerInfoEntity.getAddress()).state(customerInfoEntity.getState())
				.country(customerInfoEntity.getCountry()).pan(customerInfoEntity.getPan())
				.approvalStatus(dueDiligenceStatus.getApprovalStatus())
				.dueDiligenceStatus(dueDiligenceStatus.getDueDiligenceStatus()).build();
	}

	@Override
	public CustomerInfoEntity updateCustomerInfoDetails(CustomerInfo customerInfoRequest) throws CustomerInfoException {
		validateUpdateRequest(customerInfoRequest);
		Optional<CustomerInfoEntity> customerInfoEntity = customerInfoRepository
				.findByCustomerId(customerInfoRequest.getCustomerId());
		if (customerInfoEntity.isPresent()) {
			prepareUpdatedCustomerInfo(customerInfoEntity.get(), customerInfoRequest);
			customerInfoRepository.saveAndFlush(customerInfoEntity.get());
		} else {
			throw new CustomerInfoException("Please Provide valid customerId!!!");

		}
		return customerInfoEntity.get();
	}

	public void validateUpdateRequest(CustomerInfo customerInfoRequest) throws CustomerInfoException {
		if (customerInfoRequest.getCustomerId() != null && customerInfoRequest.getPan() != null) {
			throw new CustomerInfoException("Cannot Update PAN number!!!");
		}
		if (customerInfoRequest.getCustomerId() == null) {
			throw new CustomerInfoException("Please check the user details CustomerId cannot be Empty!!!");
		}

	}

	private void prepareUpdatedCustomerInfo(CustomerInfoEntity customerInfoEntity, CustomerInfo customerInfoRequest) {

		log.info("Request Payload is {}", customerInfoRequest);

		if (customerInfoRequest.getFirstName() != null) {
			customerInfoEntity.setFirstName(customerInfoRequest.getFirstName());
		}
		if (customerInfoRequest.getLastName() != null) {
			customerInfoEntity.setLastName(customerInfoRequest.getLastName());

		}
		if (customerInfoRequest.getAddress() != null) {
			customerInfoEntity.setAddress(customerInfoRequest.getAddress());

		}
		if (customerInfoRequest.getState() != null) {
			customerInfoEntity.setState(customerInfoRequest.getState());

		}
		if (customerInfoRequest.getCountry() != null) {
			customerInfoEntity.setCountry(customerInfoRequest.getCountry());

		}
		if (customerInfoRequest.getContactNo() != null) {
			customerInfoEntity.setContactNo(customerInfoRequest.getContactNo());
		}
	}

}
