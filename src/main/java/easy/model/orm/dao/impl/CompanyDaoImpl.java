package easy.model.orm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import easy.model.orm.DataUtils;
import easy.model.orm.dao.CompanyDao;
import easy.model.orm.entity.CompanyPo;


public class CompanyDaoImpl implements CompanyDao {
	private DataUtils data;
	 
	public CompanyDaoImpl () {
	}
	
	public void insert(CompanyPo companyPo) {
		SqlSession sqlSession = this.data.getSqlSessionFactory().openSession();
		try {
			CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
			companyDao.insert(companyPo);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public CompanyPo get(Integer id) {
		SqlSession sqlSession = this.data.getSqlSessionFactory().openSession();
		try {
			CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
			return companyDao.get(id);
		} finally {
			sqlSession.close();
		}
	}

	public List<CompanyPo> getAll() {
		SqlSession sqlSession = this.data.getSqlSessionFactory().openSession();
		try {
			CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
			return companyDao.getAll();
		} finally {
			sqlSession.close();
		}
	}

	public void update(CompanyPo companyPo) {
		SqlSession sqlSession = this.data.getSqlSessionFactory().openSession();
		try {
			CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
			companyDao.update(companyPo);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public void delete(Integer companyPoId) {
		SqlSession sqlSession = this.data.getSqlSessionFactory().openSession();
		try {
			CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
			companyDao.delete(companyPoId);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	public DataUtils getData() {
		return data;
	}

	public void setData(DataUtils data) {
		this.data = data;
	}

	public static void main(String[] args) {
		CompanyDaoImpl impl = new CompanyDaoImpl();
		List<CompanyPo> lst = impl.getAll();
		System.out.println(lst);
	}
}