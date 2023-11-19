package com.docker.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PLANTS")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plants implements Serializable{

	/**
	 * @author Pritam Gangarde
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "PLANT_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer plantId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "INSERTED_DATE")
	private Date insertedDate;

	@Column(name = "PLANT_NAME")
	private String plantName;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
}
