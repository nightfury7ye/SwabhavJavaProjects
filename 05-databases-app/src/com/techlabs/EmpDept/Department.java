package com.techlabs.EmpDept;

public class Department {
	private int deptNO;
	private String dNAME,loc;
	public Department(int deptNO, String dNAME, String loc) {
		super();
		this.deptNO = deptNO;
		this.dNAME = dNAME;
		this.loc = loc;
	}
	public int getDeptNO() {
		return deptNO;
	}
	public void setDeptNO(int deptNO) {
		this.deptNO = deptNO;
	}
	public String getdNAME() {
		return dNAME;
	}
	public void setdNAME(String dNAME) {
		this.dNAME = dNAME;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
