package model;

import java.util.List;

public interface PassengerDAO 
{

	int addPassenger(Passenger passenger);
	List searchPassenger(Passenger passenger);
	
}