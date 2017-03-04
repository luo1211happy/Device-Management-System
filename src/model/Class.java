package model;

public class Class {
	private int sn;
	private String name;
	
	
	public Class(int sn, String name) {
		super();
		this.sn = sn;
		this.name = name;
	}

	public Class(String name) {
		super();
		this.name = name;
	}
	
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
