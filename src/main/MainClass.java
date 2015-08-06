package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import singelton.AppManager;

public class MainClass {
	public static void main(String[] args) {
		CustomerOperation operation = new CustomerOperation();
		AppManager.getInstance().setCustomers(operation.parseFile());
		operation.orderCustomers();

	}
}
