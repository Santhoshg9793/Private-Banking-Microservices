/**
 * 
 */
package com.cupid.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cupid.system.entity.CustomerInfoEntity;

/**
 * @author San
 *
 */

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfoEntity, Integer> {

	Optional<CustomerInfoEntity> findByCustomerId(Integer customerId);
	boolean existsByPan(String pan);

}
