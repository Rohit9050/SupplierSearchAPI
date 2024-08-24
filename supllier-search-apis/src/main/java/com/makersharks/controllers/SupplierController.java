package com.makersharks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makersharks.payloads.ApiResponse;
import com.makersharks.payloads.SupplierDto;
import com.makersharks.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/supplier/query")

public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@PostMapping("/")
	public ResponseEntity<SupplierDto> createSupplier(@Valid @RequestBody SupplierDto supplierDto) {
		SupplierDto createSupplierDto = this.supplierService.createSupplier(supplierDto);

		return new ResponseEntity<SupplierDto>(createSupplierDto, HttpStatus.CREATED);
	}

	@GetMapping("/{supplierId}")
	public ResponseEntity<SupplierDto> getSupplier(@PathVariable Integer supplierId) {
		SupplierDto supplier = this.supplierService.getSupplierById(supplierId);
		return new ResponseEntity<SupplierDto>(supplier, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<SupplierDto>> searchSuppliers(
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "natureOfBusiness", required = false) String natureOfBusiness,
			@RequestParam(value = "manufacturingProcesses", required = false) List<String> manufacturingProcesses) {

		List<SupplierDto> suppliers = this.supplierService.searchSuppliers(location, natureOfBusiness,
				manufacturingProcesses);
		return new ResponseEntity<>(suppliers, HttpStatus.OK);
	}

	@GetMapping("/")

	public ResponseEntity<List<SupplierDto>> getAllSuppliers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		List<SupplierDto> allSuppliers = this.supplierService.getAllSuppliers(pageNumber, pageSize);
		return new ResponseEntity<>(allSuppliers, HttpStatus.OK);
	}

	@PutMapping("/{supplierId}")
	public ResponseEntity<SupplierDto> updateSupplier(@Valid @PathVariable Integer supplierId,
			@RequestBody SupplierDto supplierDto) {

		SupplierDto updatedSupplier = this.supplierService.updateSupplier(supplierId, supplierDto);
		return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
	}

	@DeleteMapping("/{supplierId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer supplierId) {
		this.supplierService.deleteSupplier(supplierId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Supplier Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/searchByWebsite")
	public List<SupplierDto> searchByWebsite(@RequestParam String website) {
		return this.supplierService.searchByWebsite(website);
	}

}
