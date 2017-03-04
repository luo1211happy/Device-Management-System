package model;

public class Keeper {
	String sn;
	String name;
	String password;
	String authority;
	int dep_sn;
	
	
	public Keeper(String sn, String name, String password, String authority, int depSn) {
		super();
		this.sn = sn;
		this.name = name;
		this.password = password;
		this.authority = authority;
		dep_sn = depSn;
	}
	

	public Keeper(String sn, String name, String authority, int depSn) {
		super();
		this.sn = sn;
		this.name = name;
		this.authority = authority;
		dep_sn = depSn;
	}


	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public int getDep_sn() {
		return dep_sn;
	}


	public void setDep_sn(int depSn) {
		dep_sn = depSn;
	}
	
    
}
