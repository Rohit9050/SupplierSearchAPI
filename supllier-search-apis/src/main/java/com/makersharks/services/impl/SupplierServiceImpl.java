package com.makersharks.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.makersharks.entities.Supplier;
import com.makersharks.exceptions.ResourceNotFoundException;
import com.makersharks.payloads.SupplierDto;
import com.makersharks.repositories.SupplierRepo;
import com.makersharks.services.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepo supplierRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SupplierDto createSupplier(SupplierDto supplierDto) {
		Supplier supplier = this.modelMapper.map(supplierDto, Supplier.class);

		Supplier savedSupplier = this.supplierRepo.save(supplier);
		return this.modelMapper.map(savedSupplier, SupplierDto.class);

	}

	@Override
	public SupplierDto getSupplierById(Integer supplierId) {
		Supplier supplier = this.supplierRepo.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("supplier", "id", supplierId));
		return this.modelMapper.map(supplier, SupplierDto.class);
	}

	@Override
	public List<SupplierDto> searchSuppliers(String location, String natureOfBusiness,
			List<String> manufacturingProcesses) {
		// Retrieve all suppliers from the repository
		List<Supplier> suppliers = this.supplierRepo.findAll().stream()
				.filter(s -> (location == null || s.getLocation().equalsIgnoreCase(location))
						&& (natureOfBusiness == null || s.getNatureofBusiness().equalsIgnoreCase(natureOfBusiness))
						&& (manufacturingProcesses == null || manufacturingProcesses.isEmpty()
								|| s.getManufacturingProcesses().stream().anyMatch(manufacturingProcesses::contains)))
				.collect(Collectors.toList());
		if (suppliers.isEmpty()) {

			throw new ResourceNotFoundException("Supplier", "criteria", "N/A");
		}

		// Map the filtered list of Supplier entities to a list of SupplierDto objects
		return suppliers.stream().map(supplier -> this.modelMapper.map(supplier, SupplierDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<SupplierDto> getAllSuppliers(Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Supplier> pageSupplier = this.supplierRepo.findAll(p);
		List<Supplier> suppliers = pageSupplier.getContent();

		// Map the list of Supplier entities to a list of SupplierDto objects
		return suppliers.stream().map(supplier -> this.modelMapper.map(supplier, SupplierDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public SupplierDto updateSupplier(Integer supplierId, SupplierDto supplierDto) {
		// Find the existing supplier by ID or throw an exception if not found
		Supplier supplier = this.supplierRepo.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", supplierId));

		// Update the supplier fields with the provided data from the SupplierDto
		supplier.setCompany_name(supplierDto.getCompany_name());
		supplier.setWebsite(supplierDto.getWebsite());
		supplier.setLocation(supplierDto.getLocation());
		supplier.setNatureofBusiness(supplierDto.getNatureofBusiness());
		supplier.setManufacturingProcesses(supplierDto.getManufacturingProcesses());

		// Save the updated supplier entity
		Supplier updatedSupplier = this.supplierRepo.save(supplier);

		// Return the updated supplier mapped to SupplierDto
		return this.modelMapper.map(updatedSupplier, SupplierDto.class);
	}

	@Override
	public void deleteSupplier(Integer supplierId) {
		Supplier supplier = this.supplierRepo.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("supplier", "id", supplierId));
		this.supplierRepo.delete(supplier);

	}

	@Override
	public List<SupplierDto> searchByWebsite(String website) {
		List<Supplier> suppliers = this.supplierRepo.findByWebsiteIgnoreCase(website);

		if (suppliers.isEmpty()) {
			throw new ResourceNotFoundException("Supplier", "website", website);
		}

		return suppliers.stream().map(supplier -> this.modelMapper.map(supplier, SupplierDto.class))
				.collect(Collectors.toList());
	}

}
