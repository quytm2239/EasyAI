package easy.model.orm.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import easy.model.orm.DataUtils;



/**
 * @author DEV-LongDT
 *
 */

public class DataAccessUtilsImpl implements DataUtils{
	
	@Override
	public SqlSessionFactory getSqlSessionFactory() {
		SqlSessionFactory factory = null;
		try {
			  Reader reader  = Resources.getResourceAsReader("mybatis-config.xml");
			  factory = new SqlSessionFactoryBuilder().build(reader);
			  } catch (IOException e) {
			   throw new RuntimeException(e.getMessage());
			  }
		return factory;
	}

}
