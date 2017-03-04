package model;

public class Model {
	private int sn;
	private String name;
	private int item_sn;
	private int life;
	private String hasAppendix;
	private int supplier_sn;
	private String supplier;
	
	public Model(String name, int itemSn, int life, String hasAppendix, int supplierSn) {
		super();
		this.name = name;
		item_sn = itemSn;		
		supplier_sn = supplierSn;
		this.life = life;
		this.hasAppendix = hasAppendix;
	}
	
	public Model(int sn, String name, int itemSn, int life, String hasAppendix, int supplierSn) {
		super();	
		this.sn = sn; 		
		this.name = name;
		item_sn = itemSn;
		supplier_sn = supplierSn;
		this.life = life;
		this.hasAppendix = hasAppendix;
	}
	
	public Model(int sn, String name, int itemSn, int life, String hasAppendix, int supplierSn, String supplier) {
		super();	
		this.sn = sn; 		
		this.name = name;
		item_sn = itemSn;
		supplier_sn = supplierSn;
		this.life = life;
		this.hasAppendix = hasAppendix;
		this.supplier = supplier;
	}


	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public int getItem_sn() {
		return item_sn;
	}

	public void setItem_sn(int itemSn) {
		item_sn = itemSn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSupplier_sn() {
		return supplier_sn;
	}

	public void setSupplier_sn(int supplierSn) {
		supplier_sn = supplierSn;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public String getHasAppendix() {
		return hasAppendix;
	}

	public void setHasAppendix(String hasAppendix) {
		this.hasAppendix = hasAppendix;
	}
	
    
	
}
