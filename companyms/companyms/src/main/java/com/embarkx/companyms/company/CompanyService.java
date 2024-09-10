package com.embarkx.companyms.company;

import java.util.List;


public interface CompanyService {

	List<Company> getAllCompanies();
	Boolean updateCompany(Company comp, Long id);
	void createCompany(Company comp);
	Boolean deleteCompany(Long id);
	Company getCompanyById(Long id);
}
