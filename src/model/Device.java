package model;

import java.sql.Date;

public class Device {
	private String sn;
	private int model_sn;
	private Date buy_date;
	private String status;
    private String isElectric;
    private int position;
    private String keeper;
    private double elapsed_year;
    
	public Device(int modelSn, Date buyDate, String keeper) {
		super();
		model_sn = modelSn;
		buy_date = buyDate;
		this.keeper = keeper;
	}
	
	

	public Device(String sn, int modelSn, Date buyDate, String status,
			String isElectric, int position, String keeper, double elapsed_year) {
		super();
		this.sn = sn;
		model_sn = modelSn;
		buy_date = buyDate;
		this.status = status;
		this.isElectric = isElectric;
		this.position = position;
		this.keeper = keeper;
		this.elapsed_year = elapsed_year;
	}



	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getModel_sn() {
		return model_sn;
	}

	public void setModel_sn(int modelSn) {
		model_sn = modelSn;
	}

	public Date getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(Date buyDate) {
		buy_date = buyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsElectric() {
		return isElectric;
	}

	public void setIsElectric(String isElectric) {
		this.isElectric = isElectric;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}



	public double getElapsed_year() {
		return elapsed_year;
	}



	public void setElapsed_year(double elapsedYear) {
		elapsed_year = elapsedYear;
	}

    
}
