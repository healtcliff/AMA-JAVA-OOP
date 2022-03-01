import java.util.Scanner;

public class lab3 {
	public static void main(String[] args) {
        int answer;int n; int i=0;
        Scanner scan = new Scanner(System.in);
        System.out.println("set your password: ");
        answer=scan.nextInt();
        while (i < 5) {
            System.out.println("enter your password: ");
            n=scan.nextInt();
            i++;
            if (answer == n) {
                System.out.println("System Unlocked!");
                break;
            } else if (i == 5) {
                System.out.println("CALLING 911!!!");
                System.out.println("CRACKING YOU IP ADDRESS....");
                System.out.println("INITIATING...");
            }
        }
    }
		
}
		

