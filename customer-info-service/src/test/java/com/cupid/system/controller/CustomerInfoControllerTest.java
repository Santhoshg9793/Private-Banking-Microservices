package com.cupid.system.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cupid.system.entity.CustomerInfoEntity;
import com.cupid.system.model.CustomerInfo;
import com.cupid.system.service.implementation.CustomerInfoServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerInfoController.class)
public class CustomerInfoControllerTest {
	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private CustomerInfoServiceImplementation customerInfoServiceImplementation;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testRetrieveCustomerInfo() throws Exception {		
		CustomerInfo customerInfo=CustomerInfo.builder().customerId(1).firstName("Santhosh").customerId(1).build();
		when(customerInfoServiceImplementation.retrieveCustomerInfo(Mockito.anyInt())).thenReturn(customerInfo);	
		
		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.get("/v1/customer/1/retriveCustomerInfo"))
				.andReturn();
		assertNotNull(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());	
	}
	
	@Test
	public void testRetrieveCustomerInfoNull() throws Exception {		
		
		when(customerInfoServiceImplementation.retrieveCustomerInfo(Mockito.anyInt())).thenReturn(null);		

		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.get("/v1/customer/150/retriveCustomerInfo"))
				.andReturn();
		assertNotNull(result.getResponse().getContentAsString());
		JSONAssert.assertEquals("{code:204,message:NO_CONTENT}", result.getResponse().getContentAsString(), false);
		}
	
	@Test
	public void testsaveCustomerInfoDetails() throws Exception {		
		CustomerInfoEntity customerInfo=CustomerInfoEntity.builder().customerId(1).firstName("Santhosh").customerId(1).build();
		when(customerInfoServiceImplementation.saveCustomerInfoDetails(Mockito.any())).thenReturn(customerInfo);	
		
		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.post("/v1/customer/info/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(new CustomerInfo())))
				.andExpect(status().isOk())
				.andReturn();
		assertNotNull(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());	
	}
	
	@Test
	public void testupdateCustomerInfoDetails() throws Exception {		
		CustomerInfoEntity customerInfo=CustomerInfoEntity.builder().customerId(1).firstName("Santhosh").customerId(1).build();
		when(customerInfoServiceImplementation.updateCustomerInfoDetails(Mockito.any())).thenReturn(customerInfo);	
		
		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.put("/v1/customer/info/update")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(new CustomerInfo())))
				.andExpect(status().isOk())
				.andReturn();
		assertNotNull(result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());	
	}
	
	
	@Test
	public void testsaveCustomerInfoDetailsNull() throws Exception {		
		when(customerInfoServiceImplementation.saveCustomerInfoDetails(Mockito.any())).thenReturn(null);	
		
		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.post("/v1/customer/info/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(new CustomerInfo())))
				.andExpect(status().isOk())
				.andReturn();
		assertNotNull(result.getResponse().getContentAsString());
		JSONAssert.assertEquals("{code:500,message:INTERNAL_SERVER_ERROR}", result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testupdateCustomerInfoDetailsNull() throws Exception {		
		when(customerInfoServiceImplementation.updateCustomerInfoDetails(Mockito.any())).thenReturn(null);	
		
		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.put("/v1/customer/info/update")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(new CustomerInfo())))
				.andExpect(status().isOk())
				.andReturn();
		assertNotNull(result.getResponse().getContentAsString());
		JSONAssert.assertEquals("{code:500,message:INTERNAL_SERVER_ERROR}", result.getResponse().getContentAsString(), false);
	}

}
