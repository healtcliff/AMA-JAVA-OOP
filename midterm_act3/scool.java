package midterm_act3;

abstract class Professor {
	protected String name;
	protected int pay;
}
class Dean extends Professor{
	protected String department;
	protected String position;
	
	public Dean(String name, int pay, String department) {
		this.name = name;
		this.pay = pay;
		this.department = department;
		this.position = "assiatance chief";
	}
	//documentation: display of status()
	//display position,name,department and pay
	
	public void display_status() {
		System.out.println("Status");
		System.out.println("position: " + this.position);
		System.out.println("Name: " + this.name);
		System.out.println("Department:" + this.department);
		System.out.println("pay: " + this.pay);
		System.out.println("--------------------------------------------------");
	}
}
class Executive extends Dean {
	public Executive(String name, int pay, String department) {
		super(name, pay, department);
		this.position = "executive";
	}
}

public class scool {

	public static void main(String[] args) {
		// create object assistance_chief and executive
		Dean assis_chief = new Dean("kenny mar", 15000,  "finance");
		assis_chief.display_status();
		
		Executive exec = new Executive("lonar boo", 32000, "human resource");
		exec.display_status();
		

	}

}
