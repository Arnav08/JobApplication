package com.embarkx.companyms.company;

import java.util.List;

import com.embarkx.companyms.dto.ReviewMessage;


public interface CompanyService {

	List<Company> getAllCompanies();
	Boolean updateCompany(Company comp, Long id);
	void createCompany(Company comp);
	Boolean deleteCompany(Long id);
	Company getCompanyById(Long id);
	public void updateCompanyRating(ReviewMessage reviewMessage);
}
