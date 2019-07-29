package com.bbs.app;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

import com.bbs.beans.Admin;
import com.bbs.beans.Booking;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;
import com.bbs.dao.AdminDaoBBS;
import com.bbs.dao.AdminDaoImpl;
import com.bbs.dao.DAOUser;
import com.bbs.services.AdminServices;
import com.bbs.services.AdminServicesImpl;
import com.bbs.services.UserServicesImpl;
import com.bbs.services.UserServices;

public class App2 {

	public static void main(String[] args) {
		UserServices services = new UserServicesImpl();
		User user = new User();
		AdminServices adservices = new AdminServicesImpl();
		Admin admin = new Admin();
		Bus bus = new Bus();
		Scanner sc=new Scanner(System.in);
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
		if(firstChoice == 1) {
		System.out.println("Enter User Id");
		Integer userId = sc.nextInt();
		
		System.out.println("Enter the Password");
		String password = sc.next();
		boolean a = services.loginUser(userId, password);
		Boolean loop1 = true;
		if(a)
		{
			System.out.println("Login Succesfull");
			while(loop1){
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

					//if CHOICE is "1" Searches User//
					if (choice == 1) {
				
						User search = services.searchUser(userId);
						System.out.println(search);
					}
					else if(choice == 2){
						
						System.out.println("Enter the username");
						String username=sc.next();
						System.out.println("Enter the email");
						String email = sc.next();
						System.out.println("Enter contact");
						Long contact = sc.nextLong();
						boolean c = services.updateUser(userId, password, username, email, contact);
						if(c)
							
						{
							System.out.println("Profile Updated");
						}else
						{
							System.out.println("Failed to update the profile");
						}
					}
					
					else if(choice==3) {
						boolean d = services.deleteUser(userId);
						if(d)
						{
							System.out.println("Profile Deleted");
						}else
						{
							System.out.println("Failed to delete the profile");
						}
					}
					else if(choice == 4) {
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
							System.out.println("failed");
						}

					}
					else if(choice == 5) {
						System.out.println("Enter booking id");
						int bookingId = sc.nextInt();
						Boolean c = services.cancelTicket(bookingId);
						if(c) {
							System.out.println("Done");
						}
						else {
							System.out.println("Error");
						}
					}
					else if(choice == 6) {
						System.out.println("Enter booking id");
						int bookingId = sc.nextInt();
					    Booking b = services.getTicket(bookingId);
					    System.out.println(b);

					}
					
					else if(choice == 7) {
						System.out.println("Enter bus id");
						int busId = sc.nextInt();
						System.out.println("Enter available  in yyyy-mm-dd");
						String date1 = sc.next();
						try {
							java.util.Date availdate1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
							java.sql.Date availdate = new Date(availdate1.getTime());
							 int i = services.checkAvailability(busId, availdate);
							System.out.println(i);

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					else if(choice == 8) {
						System.out.println("Thank you for visiting");
						loop1=false;
					}
				}


			}
			}
		}else
		{
			System.out.println("Login Failed");
			loop1 = false;
		}

		
		}
		else if(firstChoice == 2){
			User user1 = new User();
			System.out.println("Enter user id");
			user1.setUserId(sc.nextInt());
			System.out.println("Enter the username");
			user1.setUsername(sc.next());
			System.out.println("Enter the email id");
			user1.setEmail(sc.next());
			System.out.println("Enter the password");
			user1.setPassword(sc.next());
			System.out.println("Enter the contact");
			user1.setContact(sc.nextLong());
			boolean b = services.createUser(user1);
			if(b)
			{
				System.out.println("Profile Created");
			}else
			{
				System.out.println("Failed to create the profile");
				
			}
			
	}
		else if(firstChoice == 3) 
		{
			System.out.println("Welcome to Admin Login");
			System.out.println("Enter User Id");
			Integer adminId = sc.nextInt();
			System.out.println("Enter the Password");
			String password = sc.next();
			Boolean a = adservices.adminLogin(adminId, password);
			Boolean loop3 = true;
if(a) {
	System.out.println("Login successfull");
while(loop3) {
	System.out.println("** [1] Create bus               **");
	System.out.println("** [2] Update bus               **");
	System.out.println("** [3] Search bus               **");
	System.out.println("** [4] Delete bus               **");
	System.out.println("** [5] Search between source and destination        **");
	System.out.println("** [6] Exit                                **");


	System.out.println("***************************************");
	System.out.println("***************************************\n");

	{
		System.out.print("ENTER CHOICE: ");
		Integer	choice = sc.nextInt();

		if(choice==1) {
			Bus bus1 = new Bus();
			System.out.println("Enter bus id");
			bus1.setBusId(sc.nextInt());
			System.out.println("Enter bus name");
			bus1.setBusName(sc.next());
			System.out.println("Enter bus source");
			bus1.setSource(sc.next());
			System.out.println("Enter the destination");
			bus1.setDestination(sc.next());
			System.out.println("Enter bus type");
			bus1.setBusType(sc.next());
			System.out.println("Enter the total seats");
			bus1.setTotalSeats(sc.nextInt());
			Boolean b = adservices.createBus(bus1);
			System.out.println(b);
	}
		else if(choice == 2) {
			System.out.println("Enter the source");
			String source = sc.next();
			System.out.println("Enter the destination");
			String destination = sc.next();
			System.out.println("Enter the price");
			double price = sc.nextDouble();
			System.out.println("Enter bus id");
			Integer busId = sc.nextInt();
			Boolean b1 = adservices.updateBus(source, destination, price, busId);
			System.out.println(b1);
			System.out.println(adservices.searchBus(busId));

		}
		else if(choice == 3) {
			System.out.println("Enter the bus id");
			int busId = sc.nextInt();
			Bus b = adservices.searchBus(busId);
			System.out.println(b);

		}
		else if(choice == 4) {
			System.out.println("Enter the bus id");
			int busId = sc.nextInt();
			Boolean b = adservices.deletebus(busId);
		}

		else if(choice == 5) {
			System.out.println("Enter source");
			String source = sc.nextLine();
			System.out.println("Enter the destination");
			String destination = sc.nextLine();
			HashMap<Integer, Bus> b = adservices.busBetween(source, destination);
			System.out.println(b);
					
		}

		else if(choice == 6) {
			System.out.println("Exit");
			loop3= false;
		}

else {
	System.out.println("Unsuccessfull");
}
	}
	}
	}
		}
else if(firstChoice ==4) {
	loop =  false;
	System.out.println("Thank you for visting");

}

		
	}
		
}
	
}


