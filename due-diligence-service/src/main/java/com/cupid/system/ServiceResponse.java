/**
 * 
 */
package com.cupid.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author San
 *
 */
@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ServiceResponse<T> {

	private int code;
	private String message;
	private String error;
	private T data;

}
