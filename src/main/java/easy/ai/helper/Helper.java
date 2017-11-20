package easy.ai.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author DEV-LongDT
 *
 */

public class Helper {
	public static Helper getInstance() {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean-config/beans-config.xml");
		return (Helper) context.getBean("helper");
	}
	
	public String getProp(String fileName, String properties)	{
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
		return prop.getProperty(properties);
	}
}
