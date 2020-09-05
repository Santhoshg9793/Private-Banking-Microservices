/**
 * 
 */
package com.cupid.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author San
 *
 */
@ConfigurationProperties(prefix = "due-diligence-service")
@Component
@Data
public class DueDiligenceConfiguration {

	private String dueDiligenceStatus;
	private String approvalStatus;

}
