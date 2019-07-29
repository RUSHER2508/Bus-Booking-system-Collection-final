package com.bbs.app;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.bbs.beans.Booking;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;
import com.bbs.exception.BusCreateFailException;
import com.bbs.exception.BusDeleteFailException;
import com.bbs.exception.BusNotFoundException;
import com.bbs.exception.DeleteUserException;
import com.bbs.exception.LoginException;
import com.bbs.exception.RegisterException;
import com.bbs.exception.TicketBookingException;
import com.bbs.exception.TicketCancelException;
import com.bbs.exception.UpdateException;
import com.bbs.services.AdminServices;
import com.bbs.services.AdminServicesImpl;
import com.bbs.services.UserServices;
import com.bbs.services.UserServicesImpl;

public class App
{
	static int userId;
	static int adminId;
	static String password;
	static UserServices services = new UserServicesImpl();
	static AdminServices adminservices = new AdminServicesImpl();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Boolean loop = true;
		while(loop){

			//the MAIN MENU//
			System.out.println("*********************************************");
			System.out.println("****      RUSHER BUS BOOKING SYSTEM      ****");
			System.out.println("*********************************************");
			System.out.println("** [1] Login                               **");
			System.out.println("** [2] Create Profile                      **");
			System.out.println("** [3] Admin Login                         **");
			System.out.println("** [4] Exit                                **");
			System.out.println("*********************************************");
			System.out.println("*********************************************");
			System.out.println("Enter your choice");
			int firstChoice = sc.nextInt();
			Boolean login=false;
			if(firstChoice==1)
			{

				try {
					login = loginUser();
				} catch (LoginException e) {
					e.printStackTrace();
				} 
				if(login){
					System.out.println("Login Successful");
					boolean loop1 = true ;
					while(loop1)
					{
						System.out.println("** [1] Search Profile               **");
						System.out.println("** [2] Update Profile               **");
						System.out.println("** [3] Delete Profile               **");
						System.out.println("** [4] Book Ticket                  **");
						System.out.println("** [5] Cancel Ticket                **");
						System.out.println("** [6] Get Ticket                   **");
						System.out.println("** [7] Check availability           **");
						System.out.println("** [8] Exit                         **");


						System.out.println("***************************************");
						System.out.println("***************************************\n");
						{
							System.out.print("ENTER CHOICE: ");
							Integer	choice = sc.nextInt();

							if(choice ==1) {
								try{
									searchUser();
								}
								catch(Exception e){
									e.printStackTrace();
								}
							}
							else if(choice ==2) {
								try {
									updateUser();
								} catch (UpdateException e) {
									e.printStackTrace();
								}
							}
							else if(choice ==3) {
								try {
									deleteUser();
								} catch (DeleteUserException e) {
									e.printStackTrace();
								}
							}

							else if(choice ==4) {
								try {
									bookTicket();
								} catch (TicketBookingException e) {
									e.printStackTrace();
								}
							}
							else if(choice ==5) {
								try {
									cancelTicket();
								} catch (TicketCancelException e) {
									e.printStackTrace();
								}
							}
							else if(choice ==6) {
								getTicket();
							}

							else if(choice ==7) {
								checkAvailability();
							}					
							else if(choice == 8) {
								System.out.println("Thank you for visiting");
								loop1=false;
							}
							else
							{
								System.out.println("Login Failed");
								loop1 = false;
							}

						}

					}

				}
			}
			else if(firstChoice == 2) {
				try {
					createUser();
				} catch (RegisterException e) {
					e.printStackTrace();
				}

			}
			else if(firstChoice ==3) {
				Boolean admin = false;
				try {
					admin = adminLogin();
				}
				catch(LoginException e) {
					e.printStackTrace();
				}
				if(admin) {
					System.out.println("Login Succesful");
					boolean loop2 = true ;
					while(loop2) {
						System.out.println("** [1] Create bus               **");
						System.out.println("** [2] Update bus               **");
						System.out.println("** [3] Search bus               **");
						System.out.println("** [4] Delete bus               **");
						System.out.println("** [5] Exit                                **");


						System.out.println("***************************************");
						System.out.println("***************************************\n");

						System.out.print("ENTER CHOICE: ");
						Integer	choice = sc.nextInt();
						if(choice ==1) {
							try {
								createBus();
							}catch(BusCreateFailException e) {
								e.printStackTrace();
							}
						}
							else if(choice ==2) {
								try {
									updateBus();
								}catch(UpdateException e) {
									e.printStackTrace();
								}
							}
							else if(choice ==3) {
							try {
								searchBus();
							} catch (BusNotFoundException e) {
								e.printStackTrace();
							}
						}
							else if(choice == 4) {
								try {
									deleteBus();
								}catch(BusDeleteFailException e) {
									e.printStackTrace();
								}
							}
							else if(choice ==5){
								System.out.println("Thank you for visiting");
								loop2 = false;
							}
							else {
								System.out.println("Unsuccessfull");
							}

					}

				}

			}
			else if(firstChoice ==4) {
				loop =  false;
				System.err.println("Thank you for visiting");

			}

		}
		
		

	}	
	




	private static void updateUser() throws UpdateException {
		User user = new User();
		System.out.println("Enter the username");
		String username=sc.next();
		System.out.println("Enter the email");
		String email = sc.next();
		System.out.println("Enter contact");
		Long contact = sc.nextLong();
		boolean b = services.updateUser(userId, password, username, email, contact);
		if(b) {
			System.out.println("SuccessFully Updated");
		}
		else {
			System.err.println("Failed to Update");
			throw new UpdateException("Updation Fail Exception");
		}


	}

	private static boolean loginUser()throws LoginException {

		boolean checkLogin = true;
		while(checkLogin)
		{
			System.out.println("Enter userid:");
			Integer tempId=services.idCheck(sc.next());
			if(tempId!=null) {
				userId=tempId;
				checkLogin = false;
			}else {
				System.err.print("User id should be number !");

			}
		}
		System.out.println("Enter password:");
		password=sc.next();	
		Boolean login = services.loginUser(userId, password);
		if(login)
		{
			return true;
		}else {
			throw new LoginException("Login Failed Exception");
		}

	}

	private static void deleteUser() throws DeleteUserException {

		if(services.deleteUser(userId, password)){
			System.out.println("Profile sucessfully Deleted");
		}else{
			throw new DeleteUserException("User Profile deletion Failed");
		}
	}
	private static void searchBus() throws BusNotFoundException {
		boolean busCheck = true;
		Integer busId = 0;
		while(busCheck)
		{
			System.out.println("Enter BusId");
			Integer tempId=services.idCheck(sc.next());
			if(tempId!=null) {
				busId = tempId;
				busCheck = false;
			}else {
				System.err.println("User id should be number !");

			}
			Bus b = adminservices.searchBus(busId);
			System.out.println(b);

		}


	}
	private static void searchUser() {
		User search = services.searchUser(userId);
		System.out.println(search);

	}
	private static void checkAvailability() {
		System.out.println("Enter bus id");
		int busId = sc.nextInt();
		System.out.println("Enter available  in yyyy-mm-dd");
		String date1 = sc.next();
		try {
			java.util.Date availdate1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			java.sql.Date availdate = new Date(availdate1.getTime());
			 int i = services.checkAvailability(busId, availdate);
			System.out.println(i);

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	private static void bookTicket() throws TicketBookingException {
		Ticket ticket = new Ticket();

		ticket.setUserId(userId);
		System.out.println("Enter bus id");
		ticket.setBusId(sc.nextInt());
		System.out.println("Enter the source");
		ticket.setSource(sc.next());
		System.out.println("Enter the destination");
		ticket.setDestination(sc.next());
		System.out.println("Enter the seats");
		ticket.setNumofSeats(sc.nextInt());
		System.out.println("Enter date");
		ticket.setAvailDate(sc.next());
		Boolean state = services.bookTicket(ticket);
		if(state != null)
		{
			System.out.println("Booking Successfull");
		}
		else
		{
		
			throw new TicketBookingException("Ticket Booking Fail Exception");
		}	
	}
	private static void getTicket() {
		System.out.println("Enter booking id");
		int bookingId = sc.nextInt();
	    Booking booking = services.getTicket(bookingId);
		if(booking != null) {
			System.out.println(booking);
		}else {
			System.out.println("No Tickets Found");
		}	
	}
	private static void cancelTicket() throws TicketCancelException {
		System.out.println("Enter BookingId");
		int bookingId = sc.nextInt();
		Booking ticket = services.getTicket(bookingId);
		System.out.println(ticket);
		System.err.println("Press  yes to confirm ");
		String decision = sc.next();
		if(decision.equalsIgnoreCase("yes")) {
			Boolean cancelTicket = services.cancelTicket(bookingId);
			if(cancelTicket) {
				System.out.println("Ticket Successfully Cancelled");
			}else {
				System.err.println("No Tickets Found");
			}
		}
		else {
			throw new TicketCancelException("Ticket cancel exception occured");
		}
	}

	private static void createUser() throws RegisterException {
		User user = new User();
		boolean checkLogin = true;
		while(checkLogin)
		{
			System.out.println("Enter userid:");
			Integer tempId=services.idCheck(sc.next());
			if(tempId!=null) {
				userId = tempId;
				user.setUserId(userId);
				checkLogin = false;
			}else {
				System.err.println("User id should be number !");

			}
		}
		System.out.println("Enter Username:");
		user.setUsername(sc.next());
		boolean checkEmail = true;
		while(checkEmail) {
			System.out.println("Enter Email:");
			String temp=services.checkemail(sc.next());
			if(temp !=null) {
				user.setEmail(temp);
				checkEmail = false;
			}else {
				System.err.println("Wrong Email Format!! e.g(example@email.com)");
			}
		}

		boolean checkContact = true;
		while(checkContact) {
			System.out.println("Enter Contact No.:");
			Long temp=services.checkContact(sc.next());
			if(temp !=null) {
				user.setContact(temp);
				checkContact = false;
			}else {
				System.err.println("Contact should be of 10 digits!!");
			}
		}
		System.out.println("Enter Password:");
		user.setPassword(sc.next());
		boolean registration = services.createUser(user);
		if(registration) {
			System.out.println("Registration Successful");
		}
		else {
			throw new RegisterException("Registration Fail Exception");
		}

	}
	private static Boolean adminLogin() throws LoginException {

		System.out.println("Enter Admin id:");
		int adminid = Integer.parseInt(sc.next());
		System.out.println("Enter Admin password:");
		String password = sc.next();
		if(adminservices.adminLogin(adminid,password))
		{
			return true;
		}else {
			throw new LoginException("Admin Login Fail Exception");
		}
	}
	private static void createBus() throws BusCreateFailException{
		Bus bus = new Bus();
		System.out.println("Enter Bus Id");
		bus.setBusId(Integer.parseInt(sc.next()));
		System.out.println("Enter BusName");
		bus.setBusName(sc.next());
		System.out.println("Enter Bus type");
		bus.setBusType(sc.next());
		System.out.println("Enter Source");
		bus.setSource(sc.next());
		System.out.println("Enter Destination");
		bus.setDestination(sc.next());
		System.out.println("Enter Total Seats");
		bus.setTotalSeats(Integer.parseInt(sc.next()));
		System.out.println("Enter Price");
		bus.setPrice(Double.parseDouble(sc.next()));

		boolean create = adminservices.createBus(bus);
		if(create) {
			System.out.println("Bus created");
		}
		else {
			throw new BusCreateFailException("Fail to Create Bus Exception");
		}

	}

	private static void updateBus() throws UpdateException {
		System.out.println("Enter the source");
		String source = sc.next();
		System.out.println("Enter the destination");
		String destination = sc.next();
		System.out.println("Enter the price");
		double price = sc.nextDouble();
		System.out.println("Enter bus id");
		Integer busId = sc.nextInt();
		Boolean b1 = adminservices.updateBus(source, destination, price, busId);
		System.out.println(b1);
		System.out.println(adminservices.searchBus(busId));

		boolean updatebus = adminservices.updateBus(source, destination, price, busId);
		if(updatebus)
		{
			System.out.println("Bus Successfully Updated");
		}
		else {
			throw new UpdateException("Fail to Update Bus Exception");
		}	
	}

	private static void deleteBus() throws BusDeleteFailException {
		System.out.println("Enter Bus Id");
		int busId = Integer.parseInt(sc.next());
		boolean delbus = adminservices.deletebus(busId);
		if(delbus)
		{
			System.out.println("Bus Successfully Deleted");
		}
		else {
			throw new BusDeleteFailException("Fail to Delete Bus Exception");
		}
	}


}
