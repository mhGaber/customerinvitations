package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONObject;

import singelton.AppManager;
import utils.Constants;

import models.Customer;
import models.CustomerComparator;

public class CustomerOperation {
	ArrayList<Customer> customersList;

	public ArrayList<Customer> parseFile() {
		FileInputStream fstream;
		try {
			String filePath = new File("").getAbsolutePath();
			System.out.println(filePath);
			customersList = new ArrayList<>();

			BufferedReader br = new BufferedReader(new FileReader(filePath
					+ Constants.FILE_NAME));
			String strLine;

			while ((strLine = br.readLine()) != null) {
				JSONObject customJsonObject = new JSONObject(strLine);
				Customer customerObject = new Customer();
				customerObject.setId(customJsonObject.getInt("user_id"));
				customerObject.setLat(customJsonObject.getDouble("latitude"));
				customerObject.setLon(customJsonObject.getDouble("longitude"));
				customerObject.setName(customJsonObject.getString("name"));
				customersList.add(customerObject);
			}

			// Close the input stream
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customersList;
	}

	public void orderCustomers() {
		int size = AppManager.getInstance().getCustomers().size();
		Collections.sort(customersList, new CustomerComparator());
		boolean isExist = true;
		for (int i = 0; i < size; i++) {
			Customer customer = AppManager.getInstance().getCustomers().get(i);
			if (distance(customer.getLat(), customer.getLon()) < 100) {
				if (isExist) {
					System.out.println("Invited customers are:");
					isExist = false;
				}
				System.out.println(customer.getId() + " " + customer.getName());

			}
		}
		if (isExist) {
			System.out.println("There's no customers within 100km circle");
		}
	}

	private int distance(double lat1, double lon1) {
		double theta = lon1 - Constants.LON;
		double dist = Math.sin(deg2rad(lat1))
				* Math.sin(deg2rad(Constants.LAT)) + Math.cos(deg2rad(lat1))
				* Math.cos(deg2rad(Constants.LAT)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		dist = dist * 1.609344;

		return (int) (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
