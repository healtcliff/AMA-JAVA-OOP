package midterm_act1;

import java.util.Scanner;

class User{
	private int balance = 0;
	
	public User(int balance) {
		this.balance=balance;
	}
	/* instruction : withdraw
	 * " subtracting amount to current balance and then return it to the current users balance
	 * 1 arguments been using
	 * ctto for Arjhay frias */
	public int withdraw(int amount) {
		if (amount>this.balance) {
			System.out.println("Unable to proces withdrawal");
			System.out.println("Insufficient Balance!");
		} else if(amount <=0) {
			System.out.println("Unable to withdraw negative number");
		}else {
			this.balance -= amount;
		}
		return this.balance;
	}
	/* instruction : deposit
	 * " adding amount to current balance and then return it to the current users balance
	 * 1 arguments been using
	  */
	public int deposit (int amount) {
		if (amount<=0) {
			System.out.println("Unable to deposit negative number!");
		} else {
			this.balance += amount;
		}
		return this.balance;
	}
}


public class ATM {

	public static void main(String[] args) {
		//set user's balance
		Scanner sc = new Scanner(System.in);
		System.out.println("yohoho Welcome!!");
		int user_balance = 0;
		
		//validate's user input [User's balance]
		while (user_balance <= 0 || user_balance != (int)user_balance) {
			try {
				System.out.print("Enter your balance: ");
                String str_users_balance = sc.nextLine();
                user_balance = Integer.parseInt(str_users_balance);
                if (user_balance <0) {
                	System.out.println("Unable to set your balance with negative currency.");
                	
                }
			} catch (Exception e) {
				System.out.println("Invalid Input please try again.");
				System.out.println("----------------------------------------------------");
			}
		
		}
		// Creating user
		User ken = new User(user_balance);
		System.out.println("-----------------------------------------------");
		
		
		
		//validating the User Input [option or opt]
		while (true) {
			try {
				// Selection option
				System.out.println("1. Withdraw amount");
				System.out.println("2. Deposit Amount");
				System.out.println("3. Exit");
				int opt = 0;
				
				//get user's input
				System.out.println("Select an option: ");
				String str_option = sc.nextLine();
				opt = Integer.parseInt(str_option);
				System.out.println("-----------------------------------------------");
				
				// Initialize Function
				switch(opt) {
				case 1:
					withdraw(ken, sc);
					break;
				case 2:
					deposit(ken, sc);
					break;
				case 3:
					exit();
					break;
				default:
					System.out.println("invalid option. try agian.");
					break;									
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Try again");
				System.out.println("------------------------------------------");
			}
		}
	}
	/*docu: withdraw(user user_name, scanner scan)
	 * "get user input of amount and call withdraw method from user class
	 * also display's user current balance
	 * take 2 argyuments of user class and scanner class
	 */
	public static void withdraw(User user_name, Scanner sc) {
		System.out.println("How much amount you want to withdraw from your account?");
		int amount = 0;
		
		//validate user input [amount]
		while (amount == 0 || amount != (int)amount) {
			try {
				System.out.print("Amount: ");
				String str_amount = sc.nextLine();
				amount = Integer.parseInt(str_amount);
				System.out.println("current balance: " + user_name.withdraw(amount));
				System.out.println("---------------------------------------------------");
			} catch (Exception e) {
				System.out.println("Invalid Input. Try again.");
				System.out.println("-----------------------------------------------------");
			}
		}
	}
	/*documentation: deposit( User user_name, Scanner sc)
	 * get users input of amount and call deposit method from user class
	 * also to display users current balance
	 * it takes  2 argument of User class and Scanner class
	 */
	public static void deposit(User user_name, Scanner sc) {
		System.out.println("How much you want to deposit in your account?");
		int amount = 0;
		
		//validate user input [ amount]
		while (amount == 0 || amount != (int)amount) {
			try {
				System.out.println("Amount: ");
				String str_amount = sc.nextLine();
				amount = Integer.parseInt(str_amount);
				System.out.println("current balance: " + user_name.deposit(amount));
				System.out.println("------------------------------------------------------");
			} catch (Exception e) {
				System.out.println("Invalid Input. Try again.");
				System.out.println("--------------------------------------------------");
			}
		}
	}
	/* documentation: exit()
	 * termination of the program
	 * takes 0 argument*/
	public static void exit() {
		System.out.println("Thank you for using the banking system!");
		System.exit(0);
	}
}
