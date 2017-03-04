package model;

public class Supplier {
	private String name;
	private int sn;
	
	
	public Supplier(String name) {
		super();
		this.name = name;
	}
	public Supplier(int sn,String name) {
		super();
		this.name = name;
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public int getSn() {
		return sn;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	
}
