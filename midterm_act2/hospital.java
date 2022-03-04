package midterm_act2;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Model{
	private String ID = "0";
	private String firstname = "0";
	private String lastname = "0";
	private String disease = "0";
	private String birthdate = "0";
	private String dischargedate = "0";
	
	/*documentation : petient(connection con)
	 * "retrieve all the patient in the database
	 * require 1 argument of connection and returns ResultSet*/
	
	public ResultSet patients(Connection con) {
		try {
			Statement token = con.createStatement();
			String query  = "select *, TIMESTAMPDIFF(YEAR, Birthdate, CURDATE()) AS age FROM patient";
			return token.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ResultSet patient(Connection con) {
		Scanner sc = new Scanner(System.in);
		//validation First name
		while(true) {
			System.out.println("Enter First Name: ");
			this.firstname = sc.nextLine();
			if(!this.firstname.matches(".*[0-9].*") && this.firstname.length() >2 ) {
				break;
			}
		}
		while(true) {
			System.out.println("Enter Last Name: ");
			this.lastname = sc.nextLine();
			if(!this.lastname.matches(".*[0-9].*") && this.lastname.length() >2 ) {
				break;
			}
		}
		try {
			String query = "SELECT *, TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) AS age FROM patient";
			PreparedStatement token = con.prepareStatement(query);
			token.setString(1, this.firstname);
			token.setString(2, this.lastname);
			System.out.println("");
			return token.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*documentation: pediatric patients (Connection con)
	 * "retrieve all the pediatric patient in the database
	 * requires 1 argument of Connection and Returns ResultSet*/
	public ResultSet pediatric_patients(Connection con) {
		try {
			Statement token = con.createStatement();
			String query = "SELECT *, TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) AS age FROM patient WHERE TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) <= 12;";
			return token.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*documentation: display patient (ResultSet patients)
	 * "Display patients data in a readable format
	 * require 1 argmunent of ResultSet*/
	public void display_patient(ResultSet patient){
		try {
		if (patient.next() == false) {
			System.out.println("No Patient in the Database!");
			System.out.println("---------------------------------------------------------------");
		} else {
			do {
				String fullname = patient.getString("firstname")+ " " + patient.getString("lastname");
				String disease = patient.getString("disease");
				String age = patient.getString("age");
				String birthdate = format_date(patient.getString("birthdate"));
				String dischargedate = format_date(patient.getString("dischargedate"));
				String admissiondate = format_date(patient.getString("admissiondate"));
				
				System.out.println("Full Name: " + fullname);
				System.out.println("Disease: " + disease);
				System.out.println("Age: " + age);
				System.out.println("Birthday: " + birthdate);
				System.out.println("Date of Admission: " + admissiondate);
				System.out.println("Date of Discharge: " + dischargedate);
				System.out.println("---------------------------------------------------------------");
			} while(patient.next());
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	/*documentation: format_date(String Raw_date)
	 * "format date into readable format
	 * require 1 argument of String and Return String*/
	public static String format_date(String Raw_date) {
		SimpleDateFormat old_date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat new_date_format = new SimpleDateFormat("MMMM dd y");
		try {
			return new_date_format.format(old_date_format.parse(Raw_date));
		} catch (Exception e) {
			return Raw_date;
		}
	}
	/*documentation: add_patient(Connection con)
	 * "get and validate's user input"
	 * require 1 argument of connection*/
	public void add_patient(Connection con) {
		Scanner scan = new Scanner(System.in);
		
		//validate firstname
		while(true) {
			System.out.println("Enter First Name: ");
			this.firstname = scan.nextLine();
			if(!this.firstname.matches(".*[0-9].*") && this.firstname.length() > 2) {
				break;
			}
		}
		while (true) {
			System.out.println("Enter Last Name: ");
			this.lastname = scan.nextLine();
			if(!this.lastname.matches(".*[0-9].*") && this.lastname.length() > 2) {
				break;
			}
		}
		//validate Disease
		while(this.disease == "0") {
			System.out.println("Enter Disease: ");
			this.disease = scan.nextLine();
		}
		//validate birth date
		while (!this.birthdate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			System.out.println("Enter Birthdate [yyyy-mm-dd]: ");
			this.birthdate = scan.nextLine();
		}
		//validate discharge date
		while(!this.dischargedate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			System.out.println("Enter Discharge Date [yyyy-mm-dd]: ");
			this.dischargedate = scan.nextLine();
		}
		insert_patient(con);
	}
	/*documentation: insert_patient(Connection con)
	 * "Insert new patient into datebase"
	 * require 1 argument of Connection*/
	private void insert_patient(Connection con) {
		try {
			String query = "INSERT INTO patient(firstname, lastname, disease, birthdate, dischargedate, created_on, updated_on) VALUES (?, ?, ?, ?, ?, NOW(), NOW()); ";
			PreparedStatement token = con.prepareStatement(query);
			token.setString(1, this.firstname);
			token.setString(2, this.lastname);
			token.setString(3, this.disease);
			token.setString(4, this.birthdate + " 00:00:00");
			token.setString(5, this.dischargedate + " 00:00:00");
			token.execute();
			System.out.println("Congratulation!!!! you have successfully added into out Datebase");
			System.out.println("---------------------------------------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class hospital {

	public static void main(String[] args) throws SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospi", "root", "Oracle01");
			Scanner scan = new Scanner(System.in);
			Model db = new Model();
			String option;
			
			while (true) {
				System.out.println("Welcome to Hospital Database Management System");
				System.out.println("1. Get All Patients");
				System.out.println("2. Get All Pediatric patients");
				System.out.println("3. Retrieve patient");
				System.out.println("4. Add Patient");
				System.out.println("5. Exit");
				System.out.println("Select Option: ");
				option  = scan.nextLine();
				System.out.println("---------------------------------------------------------------");
				switch (option) {
				case "1":
					System.out.println("Retrieving All Patients\n");
					ResultSet patient = db.patients(con);
					db.display_patient(patient);
					break;
				case "2":
					System.out.println("Retrieving All Pediatric Patients\n");
					ResultSet pediatric_patients = db.pediatric_patients(con);
					db.display_patient(pediatric_patients);
					break;
				case "3":
					System.out.println("Retrieving Patient\n");
					ResultSet patients = db.patients(con);
					db.display_patient(patients);
					break;
				case "4":
					System.out.println("Add a Patient");
					db.add_patient(con);
					break;
				case "5":
					System.out.println("Thank you for using Hospital Database Management System");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Option!!");
					System.out.println("Please Try Again!!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
