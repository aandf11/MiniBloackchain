//Part D
public class NewProcess {
 	public static int PID = 0; //set initial process ID to 0 

	public static void main(String args[]) throws Exception { //accept an argument
		System.out.println("Please choose a process from 1 - 3");
		if (args.length < 1) PID = 0; //if the length of the argument is less than 1 , set PID to 0
    	else if (args[0].equals("0")) PID = 0; //if the arg is "1" , set PID to 1
    	else if (args[0].equals("1")) PID = 1;
    	else if (args[0].equals("2")) PID = 2;
    	else PID = 0;
		if (PID==0) {
			System.out.println("Process: 0 is running"); //if PID is the value of 1, print out the statement.
		}
		else if (PID==1) {
			System.out.println("Process: 1 is running");
		}
		else if (PID==2) {
			System.out.println("Process: 2 is running");
		}

	}
}
