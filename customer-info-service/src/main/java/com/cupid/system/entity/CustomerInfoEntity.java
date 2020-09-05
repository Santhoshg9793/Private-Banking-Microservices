/**
 * 
 */
package com.cupid.system.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author San
 *
 */
@Entity
@Table(name = "CUSTOMER_INFO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_info_seq")
	@SequenceGenerator(name = "customer_info_seq", sequenceName = "customer_info_seq", allocationSize = 1)
	@Column(name = "CUSTOMER_ID")
	private Integer customerId;

	@NotEmpty(message = "First name cannot be null for a customer")
	@Pattern(regexp = "^.*[A-Za-z].*$", message = "Customer name cannot contain numbers and special characters.")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	
	@Email(message="Please provide a valid Email address")
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@NotNull
	@Column(name = "PAN")
	private String pan;	
	
	@Column(name = "CONTACT_NO")
	private String contactNo;	
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	

}
