package com.bbs.dao;

import java.util.ArrayList;
import java.util.Date;

import com.bbs.beans.Booking;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;

public interface DAOUser {
	//user manipulation
		public Boolean createUser(User user);
		public Boolean updateUser(int userId,String password,String username,String email,long contact);
		public Boolean deleteUser(int userId);
		public Boolean loginUser(Integer userId,String password);
		public User searchUser(int userId);
		
		
		//ticket booking
		public Boolean bookTicket(Ticket ticket);
		public Boolean cancelTicket(int bookingId);
		public Booking getTicket(int bookingId);
		public Integer checkAvailability(int busId,Date availdate);

}
