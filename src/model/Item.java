package model;

public class Item {
	private int class_sn;
	private String name;
	private int sn;

	public Item(int sn, int class_sn, String name) {
		super();
		this.sn = sn;
		this.class_sn = class_sn;
		this.name = name;
	}

	public Item(int class_sn, String name) {
		super();
		this.class_sn = class_sn;
		this.name = name;
	}

	public int getClass_sn() {
		return class_sn;
	}

	public String getName() {
		return name;
	}

	public int getSn() {
		return sn;
	}

	public void setClass_sn(int class_sn) {
		this.class_sn = class_sn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

}
