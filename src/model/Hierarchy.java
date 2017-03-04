package model;

public class Hierarchy {
	private int group;
	private int item;
	private int model;
	
	public Hierarchy(int group, int item, int model) {
		super();
		this.group = group;
		this.item = item;
		this.model = model;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}
	
	
}
