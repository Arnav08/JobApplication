package com.embarkx.companyms.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.embarkx.companyms.company.Company;
import com.embarkx.companyms.company.CompanyRepository;
import com.embarkx.companyms.company.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository compRepo;

	public CompanyServiceImpl(CompanyRepository compRepo) {
		super();
		this.compRepo = compRepo;
	}

	@Override
	public List<Company> getAllCompanies() {

		return compRepo.findAll();
	}

	@Override
	public Boolean updateCompany(Company comp, Long id) {
		Optional<Company> companyOptional = compRepo.findById(id);
		System.out.println("Company Foun in db::  " + companyOptional);

		if (companyOptional.isPresent()) {
			Company companyToUpdate = companyOptional.get();
			companyToUpdate.setDescription(comp.getDescription());
			companyToUpdate.setName(comp.getName());
			compRepo.save(companyToUpdate);
			return true;
		}
		return false;
	}

	@Override
	public void createCompany(Company comp) {
		compRepo.save(comp);

	}

	@Override
	public Boolean deleteCompany(Long id) {
		if (compRepo.existsById(id)) {
			compRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Company getCompanyById(Long id) {
		Company compId = compRepo.findById(id).orElse(null);
		return compId;
	}

}
