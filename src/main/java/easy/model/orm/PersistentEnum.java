package easy.model.orm;

import java.util.Map;

/**
 * 
 * @author Jingqi Xu
 */
public interface PersistentEnum<T> {
	
	T getValue();
	
	String getDisplayName();

	Map<T, ? extends PersistentEnum<T>> getAll();
}
