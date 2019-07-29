package com.bbs.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.bbs.beans.Availability;
import com.bbs.beans.Booking;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;
import com.bbs.repo.Repository;

public class UserDaoImpl implements DAOUser {
	Repository repository = new Repository();
	HashMap<Integer, User> userMapDb = Repository.user;
	HashMap<Integer, Availability> availableMapDb=Repository.avail;
	HashMap<Integer, Booking> bookingMapDb=Repository.book;
	HashMap<Integer, Ticket> ticketMapDb = Repository.ticket;
	HashMap<Integer, Bus> busMapDb = Repository.bus;

	Scanner sc = new Scanner(System.in);


	User user = new User();
	@Override
	public Boolean createUser(User user) {

		userMapDb.put(user.getUserId(), user);
		if(user != null) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean updateUser(int userId,String password,String username,String email,long contact) {

		User user = searchUser(userId);
		if(user != null) {

			user.setUserId(userId);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setContact(contact);
			return true;

		}

		return false;
	}

	@Override
	public Boolean deleteUser(int userId) {
		User user = searchUser(userId);
		if(user != null) {
			userMapDb.remove(userId);
			return  true;
		}

		return false;
	}

	@Override
	public Boolean loginUser(Integer userId, String password) {

		User user = searchUser(userId);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public User searchUser(int userId) {

		User user = null;
		Iterator it = userMapDb.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if(pair.getKey().equals(userId)) {
				return (User) pair.getValue();
			}

		}
		return user;
	}

	@Override
	public Boolean bookTicket(Ticket ticket) {

		String source = ticket.getSource();
		String destination = ticket.getDestination();
		Integer busId=ticket.getBusId();
		String date1=ticket.getAvailDate();
		java.util.Date date2;
		Random randomNumGenerator = new Random();
		Integer bookingId = randomNumGenerator.nextInt(100);
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			Date date=new Date(date2.getTime());
			System.out.println(source);
			System.out.println(busId);

			Bus bus=busMapDb.get(busId);
			System.out.println(bus);
			Booking booking=new Booking();

			if(source.equalsIgnoreCase(bus.getSource()) && destination.equalsIgnoreCase(bus.getDestination()))
			{
				Integer available = checkAvailability(busId , date);
				if(available >= ticket.getNumofSeats())
				{
					available = available-ticket.getNumofSeats();
					Availability available1=availableMapDb.get(bus.getBusId());
					available1.setAvailSeats(available);
					System.out.println("Available Seats : "+available);
					booking.setBusId(ticket.getBusId());
					booking.setDate(ticket.getAvailDate());
					booking.setSource(ticket.getSource());
					booking.setDestination(ticket.getDestination());
					booking.setNumOfSeats(ticket.getNumofSeats());
					booking.setBookingId(bookingId);
					booking.setUserId(user.getUserId());
					booking.setJourneyDate(booking.getJourneyDate());
					bookingMapDb.put(booking.getBookingId(), booking);

					System.out.println(bookingMapDb.get(booking.getBookingId()));
					return true;
				}
				else
				{
					System.out.println("No tickets Available");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  



		return false;
	}

	@Override
	public Boolean cancelTicket(int bookingId) {

		Booking booking=getTicket(bookingId);
			booking = bookingMapDb.remove(booking.getBookingId());
			if(bookingMapDb != null)
			{
				Availability available = availableMapDb.get(booking.getBusId());
				available.setAvailSeats(available.getAvailSeats()-booking.getNumOfSeats());
				return true;

			}
		



		return null;
	}

	@Override
	public Booking getTicket(int bookingId) {
		Iterator it=bookingMapDb.entrySet().iterator();
		Booking booking=new Booking();
		while(it.hasNext())
		{
			Map.Entry pair=(Map.Entry)it.next();
			if(pair.getKey().equals(bookingId))
			{
				return (Booking) pair.getValue();
			}
		}
		return null;
	}

	@Override
	public Integer checkAvailability(int busId, java.util.Date availdate) {

		Availability avail = availableMapDb.get(busId);
		Booking book = new Booking();
		if(availdate.toString().equals(avail.getJourneyDate())) {

			return avail.getAvailSeats();
		}
		return null;

	}



}
