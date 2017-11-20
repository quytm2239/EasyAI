package easy.ai.orm.services.impl;

import java.util.List;

import easy.ai.orm.dao.CompanyDao;
import easy.ai.orm.entity.CompanyPo;
import easy.ai.orm.services.CompanyServices;

public class CompanyServicesImpl implements CompanyServices {
	private CompanyDao companyDao;
	
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
		return companyDao.getAll();
	}

	@Override
	public void update(CompanyPo company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
}