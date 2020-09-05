package com.cupid.system.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cupid.system.DueDiligenceConfiguration;
import com.cupid.system.model.CustomerDueDiligenceStatus;
import com.cupid.system.service.DueDiligenceService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DueDiligenceController.class)
public class DueDiligenceControllerTest {
	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private DueDiligenceService dueDiligenceService;

	@MockBean
	private DueDiligenceConfiguration dueDiligenceConfiguration;

	@Test
	public void testRetreiveDueDiligenceStatus() throws Exception {
		CustomerDueDiligenceStatus dueDiligenceStatus = CustomerDueDiligenceStatus.builder().dueDiligenceStatus("STANDARD")
				.approvalStatus("APPROVAL_AWAITING").build();
		when(dueDiligenceService.retreiveDueDiligenceStatus(Mockito.anyInt())).thenReturn(dueDiligenceStatus);

		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.get("/v1/customer/1/retriveDueDiligenceStatus"))
				.andReturn();

		assertNotNull(result.getResponse());
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	@Ignore	
	public void testRetreiveDueDiligenceStatusNull() throws Exception {

		when(dueDiligenceService.retreiveDueDiligenceStatus(Mockito.anyInt())).thenReturn(null);

		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.get("/v1/customer/1552/retriveDueDiligenceStatus"))
				.andExpect(status().isOk()).andReturn();
		assertNotNull(result.getResponse());
		JSONAssert.assertEquals("{code:204,message:NO_CONTENT}", result.getResponse().getContentAsString(), false);

	}

	@Test
	public void testcomputeDueDiligence() throws Exception {
		CustomerDueDiligenceStatus dueDiligenceStatus = CustomerDueDiligenceStatus.builder().dueDiligenceStatus("STANDARD")
				.approvalStatus("APPROVAL_AWAITING").build();
		when(dueDiligenceService.computeDueDiligence(Mockito.anyInt())).thenReturn(dueDiligenceStatus);
		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.get("/v1/customer/1/computeDueDiligence"))
				.andReturn();
		assertNotNull(result.getResponse());
		assertEquals(200, result.getResponse().getStatus());
	}

}
