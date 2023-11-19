package com.docker.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantsDTO implements Serializable {
	/**
	 * @author Pritam Gangarde
	 */
	private static final long serialVersionUID = 1L;
	private Integer plantId;
	private String description;
	private Date insertedDate;
	private String plantName;
	private Date updatedDate;
}
