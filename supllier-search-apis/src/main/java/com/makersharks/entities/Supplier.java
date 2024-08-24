package com.makersharks.entities;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer supplier_id;
	@Column(name = "company_name", nullable = false)
	private String company_name;
	@Column(name = "website", nullable = false)
	private String website;
	@Column(name = "location", nullable = false)
	private String location;
	@Column(name = "nature_of_business", nullable = false)
	private String natureofBusiness;

	@ElementCollection
	@CollectionTable(name = "manufacturing_processes", joinColumns = @JoinColumn(name = "supplier_id"))
	@Column(name = "process")
	private List<String> manufacturingProcesses;

}
