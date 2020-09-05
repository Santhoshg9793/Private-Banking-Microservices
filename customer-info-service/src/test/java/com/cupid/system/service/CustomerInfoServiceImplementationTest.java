package com.cupid.system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.cupid.system.CustomerInfoException;
import com.cupid.system.ServiceResponse;
import com.cupid.system.entity.CustomerInfoEntity;
import com.cupid.system.model.CustomerInfo;
import com.cupid.system.model.CustomerDueDiligenceStatus;
import com.cupid.system.repository.CustomerInfoRepository;
import com.cupid.system.service.implementation.CustomerInfoServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerInfoServiceImplementationTest {

	@InjectMocks
	private CustomerInfoServiceImplementation customerInfoServiceImplementation;

	@MockBean
	private CustomerInfoRepository customerInfoRepository;

	@MockBean
	private DueDiligenceService dueDiligenceService;

	@Test
	void testRetrieveCustomerInfo() throws CustomerInfoException {
		ServiceResponse<CustomerDueDiligenceStatus> ress = new ServiceResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(),
				null, new CustomerDueDiligenceStatus().builder().dueDiligenceStatus("Standard").build());
		CustomerInfoEntity customerInfo = CustomerInfoEntity.builder().firstName("Santhosh").customerId(1).build();
		Optional<CustomerInfoEntity> optcustomerInfo = Optional.of(customerInfo);
		when(customerInfoRepository.findByCustomerId(Mockito.anyInt())).thenReturn(optcustomerInfo);
		when(dueDiligenceService.retreiveDueDiligenceStatus((Mockito.anyInt()))).thenReturn(ress);
		CustomerInfo res = customerInfoServiceImplementation.retrieveCustomerInfo(1);

		assertNotNull(res);
		assertEquals("Standard", res.getDueDiligenceStatus());
	}

	@Test
	void testRetrieveCustomerInfoNull() throws CustomerInfoException {
		Optional<CustomerInfoEntity> optcustomerInfo = Optional.of(new CustomerInfoEntity());
		ServiceResponse<CustomerDueDiligenceStatus> ress = new ServiceResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(),
				null, new CustomerDueDiligenceStatus().builder().dueDiligenceStatus("Standard").build());
		when(customerInfoRepository.findByCustomerId(Mockito.anyInt())).thenReturn(optcustomerInfo);
		when(dueDiligenceService.retreiveDueDiligenceStatus((Mockito.anyInt()))).thenReturn(ress);
		CustomerInfo res = customerInfoServiceImplementation.retrieveCustomerInfo(100);
		// assertThrows(CustomerInfoException, executable)
		assertNotNull(res);
		assertEquals("Standard", res.getDueDiligenceStatus());
	}

	@Test
	void testSaveCustomerInfoDetails() throws CustomerInfoException {
		CustomerInfoEntity customerInfoEntity = CustomerInfoEntity.builder().customerId(1).firstName("Santhosh")
				.customerId(1).build();
		CustomerInfo customerInfo = CustomerInfo.builder().customerId(1).firstName("Santhosh").customerId(1).build();

		when(customerInfoRepository.existsByPan(Mockito.anyString())).thenReturn(false);
		when(customerInfoRepository.saveAndFlush(Mockito.any())).thenReturn(customerInfoEntity);
		when(dueDiligenceService.computeDueDiligence((Mockito.anyInt()))).thenReturn(null);
		CustomerInfoEntity res = customerInfoServiceImplementation.saveCustomerInfoDetails(customerInfo);

		assertNotNull(res);
		assertEquals("Santhosh", res.getFirstName());
	}

	@Test
	void testupdateCustomerInfoDetails() throws CustomerInfoException {

		CustomerInfoEntity customerInfoEntity = CustomerInfoEntity.builder().customerId(1).firstName("San")
				.lastName("G").address("Test Address").contactNo("123555").country("India").dob(LocalDate.now())
				.emailId("san@gg.com").build();
		Optional<CustomerInfoEntity> optcustomerInfo = Optional.of(customerInfoEntity);
		CustomerInfo customerInfo = CustomerInfo.builder().customerId(1).firstName("San").lastName("G")
				.address("Test Address").contactNo("123555").country("India").dob(LocalDate.now()).emailId("san@gg.com")
				.build();
		when(customerInfoRepository.findByCustomerId(Mockito.anyInt())).thenReturn(optcustomerInfo);
		when(customerInfoRepository.saveAndFlush(Mockito.any())).thenReturn(optcustomerInfo.get());
		CustomerInfoEntity res = customerInfoServiceImplementation.updateCustomerInfoDetails(customerInfo);
		assertNotNull(res);
		assertEquals("San", res.getFirstName());
	}

}
