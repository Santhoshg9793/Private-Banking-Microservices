/**
 * 
 */
package com.cupid.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author San
 *
 */
@Entity
@Table(name = "DUE_DILIGENCE_STATUS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DueDiligenceStatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DUE_DILIGENCE_STATUS_SEQ")
	@SequenceGenerator(name = "DUE_DILIGENCE_STATUS_SEQ", sequenceName = "DUE_DILIGENCE_STATUS_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CUSTOMER_ID")
	private Integer customerId;

	@Column(name = "DUE_DILIGENCE_STATUS")
	private String dueDiligenceStatus;

	@Column(name = "APPROVAL_STATUS")
	private String approvalStatus;

}
