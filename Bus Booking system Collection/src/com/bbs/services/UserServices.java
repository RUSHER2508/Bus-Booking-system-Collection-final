package com.bbs.services;

import java.util.Date;

import com.bbs.beans.Booking;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;

public interface UserServices {
	public Boolean createUser(User user);
	public Boolean updateUser(Integer userId, String password, String username, String email, Long contact);
	public Boolean deleteUser(Integer userId);
	public Boolean loginUser(Integer userId,String password);
	public User searchUser(Integer userId);
	
	
	//ticket booking
	public Boolean bookTicket(Ticket ticket);
	public Boolean cancelTicket(Integer bookingId);
	public Booking getTicket(Integer bookingId);
	public Integer checkAvailability(int busId,Date availdate);
	Boolean deleteUser(Integer userId, String password);
	boolean nameCheck(String name);
	Integer idCheck(String userId);
	String checkemail(String email);
	Long checkContact(String contact);


}
