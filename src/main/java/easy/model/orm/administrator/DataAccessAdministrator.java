package easy.model.orm.administrator;

import java.util.List;

import easy.model.orm.entity.CompanyPo;


public interface DataAccessAdministrator {
	void test();
	List<CompanyPo> getAllcompanies();
}
