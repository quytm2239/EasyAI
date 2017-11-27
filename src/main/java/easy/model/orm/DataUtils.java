package easy.model.orm;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author DEV-LongDT
 *
 */

public interface DataUtils {
	SqlSessionFactory getSqlSessionFactory();
}
