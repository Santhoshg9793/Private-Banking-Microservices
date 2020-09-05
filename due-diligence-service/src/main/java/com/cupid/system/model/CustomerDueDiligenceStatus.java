/**
 * 
 */
package com.cupid.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author San
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDueDiligenceStatus {
 
	private Integer id;
	private Integer customerId;
	private String dueDiligenceStatus;
	private String approvalStatus;
	
	

}
