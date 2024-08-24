package com.makersharks.services;

import java.util.List;

import com.makersharks.payloads.SupplierDto;

public interface SupplierService {

	// Create a new supplier
	SupplierDto createSupplier(SupplierDto supplierDto);

	// Retrieve a supplier by ID
	SupplierDto getSupplierById(Integer supplierId);

	// Retrieve a list of suppliers based on location, nature of business, and
	// manufacturing processes
	List<SupplierDto> searchSuppliers(String location, String natureOfBusiness, List<String> manufacturingProcesses);

	// Retrieve all suppliers
	List<SupplierDto> getAllSuppliers(Integer pageNumber, Integer pageSize);

	// Update supplier details
	SupplierDto updateSupplier(Integer supplierId, SupplierDto supplierDto);

	// Delete a supplier by ID
	void deleteSupplier(Integer supplierId);

	List<SupplierDto> searchByWebsite(String website);

}
