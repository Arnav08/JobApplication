package com.embarkx.firstjobapp.company;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	private CompanyService cs;

	public CompanyController(CompanyService cs) {
		super();
		this.cs = cs;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompany() {
		return new ResponseEntity<List<Company>>(cs.getAllCompanies(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanybyId(@PathVariable Long id) {
		Company cid= cs.getCompanyById(id);
		if(cid == null) {
			return new ResponseEntity<Company>(cid, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Company>(cid, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company comp, @PathVariable Long id) {
		if (cs.updateCompany(comp, id) == true) {
			return new ResponseEntity<String>("Company Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Company not fond !!", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping
	public ResponseEntity<String> saveComp(@RequestBody Company comp) {

		cs.createCompany(comp);
		return new ResponseEntity<String>("Company created successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
		if(cs.deleteCompany(id)) {
		return new ResponseEntity<String>("Company deleted successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Company not found in database with supplied companyID", HttpStatus.NOT_FOUND);
		}
	}
	
	

}
