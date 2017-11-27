package easy.model.orm.manager.impl;

import java.util.List;

import easy.model.orm.dao.CompanyDao;
import easy.model.orm.entity.CompanyPo;
import easy.model.orm.manager.CompanyManager;

/**
 * @author DEV-LongDT
 *
 */

public class CompanyManagerImpl implements CompanyManager{
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
