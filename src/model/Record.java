package model;

import java.sql.Date;

public class Record {
	
	private int sn;
	private String device_sn;
	private String fault;
	private String repair;
	private Date fault_date;
	private Date repair_date;
	private String finish;
	
	public Record(String deviceSn, String fault, Date faultDate) {
		super();
		device_sn = deviceSn;
		this.fault = fault;
		fault_date = faultDate;	
	}
	
	public Record(int sn, String deviceSn, String fault, Date faultDate) {
		super();
		this.sn = sn;
		device_sn = deviceSn;
		this.fault = fault;
		fault_date = faultDate;	
	}
	
	public Record(int sn, String deviceSn, String fault, String repair,
			Date faultDate, Date repairDate, String finish) {
		super();
		this.sn = sn;
		device_sn = deviceSn;
		this.fault = fault;
		this.repair = repair;
		fault_date = faultDate;
		repair_date = repairDate;
		this.finish = finish;
	}




	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public String getDevice_sn() {
		return device_sn;
	}
	public void setDevice_sn(String deviceSn) {
		device_sn = deviceSn;
	}
	public String getFault() {
		return fault;
	}
	public void setFault(String fault) {
		this.fault = fault;
	}
	public String getRepair() {
		return repair;
	}
	public void setRepair(String repair) {
		this.repair = repair;
	}
	public Date getFault_date() {
		return fault_date;
	}
	public void setFault_date(Date faultDate) {
		fault_date = faultDate;
	}
	public Date getRepair_date() {
		return repair_date;
	}
	public void setRepair_date(Date repairDate) {
		repair_date = repairDate;
	}
	public String getFinish() {
		return finish;
	}
	public void setFinish(String finish) {
		this.finish = finish;
	}
	
	

}
