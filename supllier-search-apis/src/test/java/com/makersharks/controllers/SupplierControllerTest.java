package com.makersharks.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.makersharks.payloads.SupplierDto;
import com.makersharks.services.SupplierService;

@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SupplierService supplierService;

	@BeforeEach
	public void setup() {
		// Mock dependencies and initialize MockMvc
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSearchByWebsite() throws Exception {
		// Create a mock SupplierDto
		SupplierDto supplier = new SupplierDto();
		supplier.setWebsite("www.abccorp.com");

		// Mock the behavior of the supplierService
		when(supplierService.searchByWebsite("www.abccorp.com")).thenReturn(Collections.singletonList(supplier));

		// Perform the MockMvc request and verify the response
		mockMvc.perform(get("/api/supplier/query/searchByWebsite").param("website", "www.abccorp.com"))
				.andExpect(status().isOk()).andExpect(content().json("[{'website':'www.abccorp.com'}]"));
	}

	@Test
	public void testSearchByWebsite_NoParameterProvided_ReturnsBadRequest() throws Exception {
		mockMvc.perform(get("/api/supplier/query/searchByWebsite")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSearchByWebsite_WebsiteNotFound_ReturnsEmptyList() throws Exception {
		when(supplierService.searchByWebsite("www.nonexistentwebsite.com")).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/api/supplier/query/searchByWebsite").param("website", "www.nonexistentwebsite.com"))
				.andExpect(status().isOk()).andExpect(content().json("[]"));
	}

}
