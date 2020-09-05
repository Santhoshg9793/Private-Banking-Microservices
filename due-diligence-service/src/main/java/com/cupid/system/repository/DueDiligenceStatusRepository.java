/**
 * 
 */
package com.cupid.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cupid.system.entity.DueDiligenceStatusEntity;

/**
 * @author San
 *
 */
@Repository
public interface DueDiligenceStatusRepository extends JpaRepository<DueDiligenceStatusEntity, Integer> {
	
	DueDiligenceStatusEntity findByCustomerId(Integer customerId);

}
