package com.cupid.system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.cupid.system.entity.DueDiligenceStatusEntity;
import com.cupid.system.model.CustomerDueDiligenceStatus;
import com.cupid.system.repository.DueDiligenceStatusRepository;
import com.cupid.system.service.implementation.DueDiligenceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DueDiligenceServiceImplTest {


	@Mock
	private DueDiligenceStatusRepository dueDiligenceStatusRepository;

	@Mock
	private ModelMapper modeMapper;
	
	@InjectMocks
	private DueDiligenceServiceImpl dueDiligenceService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRetreiveDueDiligenceStatus() {
		
		DueDiligenceStatusEntity dueDiligenceStatusEntity = DueDiligenceStatusEntity.builder().id(10)
				.customerId(5).dueDiligenceStatus("STANDARD")
				.approvalStatus("APPROVAL_AWAITING").build();
		DueDiligenceStatusEntity dueDiligenceStatusEntity2 = DueDiligenceStatusEntity.builder().id(11)
				.customerId(5).dueDiligenceStatus("STANDARD")
				.approvalStatus("APPROVAL_AWAITING").build();
		List<DueDiligenceStatusEntity> dueDiligenceStatusEnt=new ArrayList<>();
		
		dueDiligenceStatusEnt.add(dueDiligenceStatusEntity2);
		dueDiligenceStatusEnt.add(dueDiligenceStatusEntity);
		
		CustomerDueDiligenceStatus dueDiligenceStatus = CustomerDueDiligenceStatus.builder().id(10)
				.customerId(5).dueDiligenceStatus("STANDARD")
				.approvalStatus("APPROVAL_AWAITING").build();
		
	when(dueDiligenceStatusRepository.findByCustomerId(Mockito.anyInt())).thenReturn(dueDiligenceStatusEnt.stream().findFirst().get());
	
	when(modeMapper.map(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(dueDiligenceStatus);
	
	CustomerDueDiligenceStatus response=dueDiligenceService.retreiveDueDiligenceStatus(5);
	assertNotNull(response);
	assertEquals("STANDARD", response.getDueDiligenceStatus());		
	}
	
	@Test
	public void testcomputeDueDiligence() {

		DueDiligenceStatusEntity dueDiligenceStatusEntity = DueDiligenceStatusEntity.builder().id(10)
				.customerId(5).dueDiligenceStatus("ESTEEMED")
				.approvalStatus("APPROVAL_AWAITING").build();
		
		CustomerDueDiligenceStatus dueDiligenceStatus = CustomerDueDiligenceStatus.builder().id(10)
				.customerId(5).dueDiligenceStatus("ESTEEMED")
				.approvalStatus("APPROVAL_AWAITING").build();
		
	when(dueDiligenceStatusRepository.findByCustomerId(Mockito.anyInt())).thenReturn(dueDiligenceStatusEntity);
	when(modeMapper.map(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(dueDiligenceStatus);
	CustomerDueDiligenceStatus response=dueDiligenceService.computeDueDiligence(5);
	assertNotNull(response);
	assertEquals("ESTEEMED", response.getDueDiligenceStatus());	
	}
	
	
	@Test
	public void testcomputeDueDiligenceNull() {
		
		CustomerDueDiligenceStatus dueDiligenceStatus = CustomerDueDiligenceStatus.builder().id(110)
				.customerId(5).dueDiligenceStatus("STANDARD")
				.approvalStatus("APPROVAL_AWAITING").build();
		
	when(dueDiligenceStatusRepository.findByCustomerId(Mockito.anyInt())).thenReturn(null);
	when(modeMapper.map(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(dueDiligenceStatus);
	CustomerDueDiligenceStatus response=dueDiligenceService.computeDueDiligence(5);
	assertNotNull(response);
	assertEquals("STANDARD", response.getDueDiligenceStatus());	
	}


}
