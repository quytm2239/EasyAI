package easy.ai.orm.manager;

import java.util.List;

import easy.ai.orm.entity.CompanyPo;


/**
 * @author longDt
 *
 */
public interface CompanyManager {
	void insert(CompanyPo companyPo);
	 
	CompanyPo get(Integer company);
	 
	List<CompanyPo> getAll();
	 
	void update(CompanyPo company);
	 
	void delete(Integer id);

}
