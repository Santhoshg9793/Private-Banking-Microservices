/**
 * 
 */
package com.cupid.system.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cupid.system.entity.DueDiligenceStatusEntity;
import com.cupid.system.model.CustomerDueDiligenceStatus;
import com.cupid.system.repository.DueDiligenceStatusRepository;
import com.cupid.system.service.DueDiligenceService;

/**
 * @author San
 *
 */
@Service
public class DueDiligenceServiceImpl implements DueDiligenceService {

	@Autowired
	private DueDiligenceStatusRepository dueDiligenceStatusRepository;

	@Autowired
	private ModelMapper modeMapper;

	@Override
	public CustomerDueDiligenceStatus retreiveDueDiligenceStatus(Integer customerId) {
		DueDiligenceStatusEntity dueDiligenceStatusEntity = dueDiligenceStatusRepository.findByCustomerId(customerId);		
 
		return modeMapper.map(dueDiligenceStatusEntity, CustomerDueDiligenceStatus.class);
	}

	@Override
	public CustomerDueDiligenceStatus computeDueDiligence(Integer customerId) {
		DueDiligenceStatusEntity dueDiligenceStatusEntity = dueDiligenceStatusRepository.findByCustomerId(customerId);
		if (dueDiligenceStatusEntity == null) {
			dueDiligenceStatusEntity=	saveIntialDueDiligenceStatus(customerId,"STANDARD","APPROVAL_AWAITING",null);
		} else {
			dueDiligenceStatusEntity=	saveIntialDueDiligenceStatus(customerId,"ESTEEMED","APPROVED",dueDiligenceStatusEntity.getId());
		}
		return modeMapper.map(dueDiligenceStatusEntity, CustomerDueDiligenceStatus.class);
	}

	private DueDiligenceStatusEntity saveIntialDueDiligenceStatus(Integer customerId,String status,String approvalStatus,Integer id) {
		DueDiligenceStatusEntity dueDiligenceStatusEntity = DueDiligenceStatusEntity.builder().id(id)
				.approvalStatus(approvalStatus).dueDiligenceStatus(status).customerId(customerId).build();
		return dueDiligenceStatusRepository.saveAndFlush(dueDiligenceStatusEntity);
	}

}
