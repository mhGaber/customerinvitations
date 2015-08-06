package singelton;

import java.util.ArrayList;

import models.Customer;

public class AppManager {
	public static AppManager appManager;
	private ArrayList<Customer> customers;

	public static AppManager getInstance() {
		if (appManager == null)
			appManager = new AppManager();
		return appManager;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
}
