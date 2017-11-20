package easy.ai.orm.manager.impl;

import java.util.List;

import easy.ai.orm.entity.CompanyPo;
import easy.ai.orm.manager.CompanyManager;
import easy.ai.orm.services.CompanyServices;

/**
 * @author DEV-LongDT
 *
 */

public class CompanyManagerImpl implements CompanyManager{
	private CompanyServices companyServices;
	
	@Override
	public void insert(CompanyPo companyPo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CompanyPo get(Integer company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyPo> getAll() {
		return companyServices.getAll();
	}

	@Override
	public void update(CompanyPo company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public void setCompanyServices(CompanyServices companyServices) {
		this.companyServices = companyServices;
	}

}
