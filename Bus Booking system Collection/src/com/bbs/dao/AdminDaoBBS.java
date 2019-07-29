package com.bbs.dao;

import java.util.HashMap;

import com.bbs.beans.Admin;
import com.bbs.beans.Bus;

public interface AdminDaoBBS {

	//bus manipulations
	public Boolean createBus(Bus bus);
	public Boolean updateBus(String source,String destination,Double price,Integer busId);
	public Bus searchBus(int busId);
	public Boolean deletebus(int busId);
	public HashMap<Integer,Bus> busBetween(String source,String destination);

	//admin
	public Boolean adminLogin(int adminId, String password);
	public Admin searchAdmin(Integer adminId);

}
