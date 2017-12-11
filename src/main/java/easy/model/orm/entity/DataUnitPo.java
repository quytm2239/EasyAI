package easy.model.orm.entity;

public class DataUnitPo {

	private long id;
	private String name;
	private String description;
	
	private int category;
	private int wordType;
	private long[] preNode;
	private long[] postNode;
	
	// Index in a sentence
	private int index;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getWordType() {
		return wordType;
	}

	public void setWordType(int wordType) {
		this.wordType = wordType;
	}

	public long[] getPreNode() {
		return preNode;
	}

	public void setPreNode(long[] preNode) {
		this.preNode = preNode;
	}

	public long[] getPostNode() {
		return postNode;
	}

	public void setPostNode(long[] postNode) {
		this.postNode = postNode;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
