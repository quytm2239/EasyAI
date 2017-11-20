package easy.ai.orm.administrator.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import easy.ai.orm.administrator.DataAccessAdministrator;
import easy.ai.orm.entity.CompanyPo;
import easy.ai.orm.manager.CompanyManager;


@Component
public class DataAccessAdministratorImpl implements DataAccessAdministrator{
	private CompanyManager companyManager;
	
	public DataAccessAdministratorImpl() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean-config/beans-config.xml");

		this.companyManager = (CompanyManager) context.getBean("companyManerger");
	}
	
	@Override
	public void test() {
		System.out.println("abc");
		System.out.println(companyManager.getAll());
	}

	@Override
	public List<CompanyPo> getAllcompanies() {
		return this.companyManager.getAll();
	}
	
	public CompanyManager getCompanyManager() {
		return companyManager;
	}
	
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}
}
