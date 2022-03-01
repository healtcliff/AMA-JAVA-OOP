import java.util.Scanner;
public class Lab1 {
	public static void main(String[]args) {
		String name;int answer;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("please insert your name");
			name=scan.nextLine();	
			System.out.println("Hello Mr./Ms."+name);
			System.out.println("would you like to change name? insert 1 for yes and 2 for no");
			answer=scan.nextInt();
			scan.nextLine();
			
			
		}
		while(answer==1);
		
			
	}

}
