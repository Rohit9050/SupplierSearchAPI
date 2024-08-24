package com.makersharks.payloads;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplierDto {

	private Integer supplier_id;

	@NotEmpty(message = "Company name cannot be blank")
	private String company_name;
	@NotEmpty(message = "Website cannot be blank")
	private String website;
	@NotEmpty(message = "Location cannot be blank")
	@Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
	private String location;
	@NotEmpty(message = "Nature of business cannot be blank")
	@Pattern(regexp = "small_scale|medium_scale|large_scale", message = "Nature of business must be small_scale, medium_scale, or large_scale")
	private String natureofBusiness;

	@NotEmpty(message = "Manufacturing processes cannot be empty")
	@Size(min = 1, message = "At least one manufacturing process is required")

	private List<String> manufacturingProcesses;

}
