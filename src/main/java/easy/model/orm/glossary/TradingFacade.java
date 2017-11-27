package easy.model.orm.glossary;


import java.util.Map;

import easy.model.orm.PersistentEnum;
import easy.model.orm.PersistentEnums;

/**
 * 
 * @author Jingqi Xu
 */
public enum TradingFacade implements PersistentEnum<Integer> {
	//
	TIME(1, ""),
	PROFIT(2, ""),
	SYMBOL(3, "");
	
	//
	private static final Map<Integer, TradingFacade> INDEX = PersistentEnums.index(TradingFacade.class);
	
	//
	private final int value;
	private final String displayName;
	
	/**
	 * 
	 */
	private TradingFacade(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	/**
	 * 
	 */
	@Override
	public Integer getValue() {
		return this.value;
	}
	
	@Override
	public String getDisplayName() {
		return this.displayName;
	}
	
	@Override
	public Map<Integer, TradingFacade> getAll() {
		return INDEX;
	}
	
	/**
	 * 
	 */
	public static TradingFacade parse(Integer value) {
		return value == null ? null : INDEX.get(value);
	}
}