/**
 * 
 */
package easy.model.orm.glossary;

import java.util.Map;

import easy.model.orm.PersistentEnum;
import easy.model.orm.PersistentEnums;

/**
 * @author North Big
 *
 * Nov 27, 2017
 */
public enum CategoryType implements PersistentEnum<Integer>{
	GRAMMAR(1, "grammar"),
	;

	private static final Map<Integer, CategoryType> INDEX = PersistentEnums.index(CategoryType.class);
	
	private final int value;
	private final String displayName;

	private CategoryType(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
	
	@Override
	public String getDisplayName() {
		return this.displayName;
	}
	
	@Override
	public Map<Integer, CategoryType> getAll() {
		return INDEX;
	}
	
	public static CategoryType parse(Integer value) {
		return value == null ? null : INDEX.get(value);
	}

}
