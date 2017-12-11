package easy.model.orm.dao;

import java.util.List;

import easy.model.orm.entity.CompanyPo;

public interface CompanyDao {
	void insert(CompanyPo companyPo);
	 
	CompanyPo get(Integer company);
	 
	List<CompanyPo> getAll();
	 
	void update(CompanyPo company);
	 
	void delete(Integer id);

}
