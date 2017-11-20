package easy.ai.orm.administrator;

import java.util.List;

import easy.ai.orm.entity.CompanyPo;


public interface DataAccessAdministrator {
	void test();
	List<CompanyPo> getAllcompanies();
}
