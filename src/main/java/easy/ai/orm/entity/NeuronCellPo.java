package easy.ai.orm.entity;

public class NeuronCellPo {

	private long id;
	private String name;
	private String description;
	private long[] preCell;
	private long[] postCell;
	private int[] type;

	public NeuronCellPo(String name, String description, long[] preCell, long[] postCell, int[] type) {
		this.name = name;
		this.description = description;
		this.preCell = preCell;
		this.postCell = postCell;
		this.type = type;
	}

	public NeuronCellPo() {

	}

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

	public long[] getPreCell() {
		return preCell;
	}
	public void setPreCell(long[] preCell) {
		this.preCell = preCell;
	}

	public long[] getPostCell() {
		return postCell;
	}
	public void setPostCell(long[] postCell) {
		this.postCell = postCell;
	}

	public int[] getType() {
		return type;
	}
	public void setType(int[] type) {
		this.type = type;
	}
}
