/**
 * 
 */
package com.cupid.system.service;

import com.cupid.system.model.CustomerDueDiligenceStatus;

/**
 * @author San
 *
 */
public interface DueDiligenceService {
	
	CustomerDueDiligenceStatus retreiveDueDiligenceStatus(Integer customerId);
	
	CustomerDueDiligenceStatus computeDueDiligence(Integer customerId);

}
