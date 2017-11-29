/**
 * 
 */
package easy.model.core;

import java.util.Map;

import easy.model.orm.PersistentEnum;
import easy.model.orm.PersistentEnums;

/**
 * @author North Big
 *
 * Nov 29, 2017
 */
public enum WordType implements PersistentEnum<Integer> {
	NOUN(1, "danh từ"),
	VERB(2, "động từ"),
	ADJECTIVE(2, "tính từ"),
	ADVERB(2, "phó từ"),
	PRONOUN(2, "đại từ"),
	PREPOSITION(2, "giới từ"),
	NUMBERIC(2, "số từ"),
	AMOUNT(2, "lượng từ"),
	REFERENCE(2, "chỉ từ"),
	IMPRESSIVE(2, "trợ từ"),
	EMOTION(2, "thán từ"),
	STATUSEMOTION(2, "tình thái từ"),
	RELATION(2, "quan hệ từ")
	;

	private static final Map<Integer, WordType> INDEX = PersistentEnums.index(WordType.class);
	
	private final int value;
	private final String displayName;

	private WordType(int value, String displayName) {
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
	public Map<Integer, WordType> getAll() {
		return INDEX;
	}
	
	public static WordType parse(Integer value) {
		return value == null ? null : INDEX.get(value);
	}
}
