package easy.ai.orm.services;

import java.util.List;

import easy.ai.orm.entity.CompanyPo;


public interface CompanyServices {
	void insert(CompanyPo companyPo);
	
	CompanyPo get(Integer company);
	
	List<CompanyPo> getAll();
	 
	void update(CompanyPo company);
	 
	void delete(Integer id);

}
