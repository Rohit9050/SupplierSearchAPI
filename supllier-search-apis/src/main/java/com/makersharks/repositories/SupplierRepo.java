package com.makersharks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makersharks.entities.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

	List<Supplier> findByWebsiteIgnoreCase(String website);

}
