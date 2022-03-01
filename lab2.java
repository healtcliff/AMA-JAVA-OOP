import java.util.Arrays;
import java.util.Scanner;
public class lab2 {
	public static void Rearrange(int array[], int N) {
		 int temp[] = new int [N];
		for(int i=0;i<N;i++) {
			temp[i] = 0;
		}
		int low=0, high =N-1;
		int X=1;
		//arrangement of every second element in greater than to its left and right
		for(int i=0;i<N;i++) {
			if(X==1) {
				temp[i] = array[high--];
			}
			else {
				temp[i] = array[low++];
			}
			if(X==1) {
				X=0;
			}
			else {
				X=1;
			}
		}
		for (int i=0; i<N;i++) {
			array[i]=temp[i];
		}
	 }
	 public static void main(String[]args) {
		 Scanner input=new Scanner(System.in);
		 System.out.println("enter the elements to store: ");
		 int n=input.nextInt();
		 System.out.println("enter the elements to be stored: ");
		 int a[]=new int[n];
		 for(int i=0;i<n;i++) {
			 a[i]=input.nextInt();
		 }
		 System.out.println("input array");
		 for(int i=0;i<n;i++) {
			 System.out.print(a[i]+" ");
		 }
		 System.out.println();
		 Rearrange(a,n);
		 System.out.println("output array");
		 for(int i=0;i<n;i++) {
			 System.out.print(a[i]+" ");
		 }
		 
	 }
}