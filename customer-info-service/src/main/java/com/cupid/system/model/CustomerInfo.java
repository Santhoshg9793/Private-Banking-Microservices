/**
 * 
 */
package com.cupid.system.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(value = Include.NON_NULL)
@Validated
public class CustomerInfo {

	private Integer customerId;
	
	
	@Pattern(regexp = "^.*[A-Za-z].*$", message = "Customer name cannot contain numbers and special characters.")
	private String firstName;
	private String lastName;
	private String address;
	private String state;
	private String country;
	
	@Email(message = "Please provide a valid Email address")
	private String emailId;
	
	private String pan;
	
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Please enter valid contact number")
	private String contactNo;
	
	private LocalDate dob;

	private String dueDiligenceStatus;
	private String approvalStatus;

}
