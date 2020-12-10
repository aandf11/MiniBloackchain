/*--------------------------------------------------------
Part I
1. Name / Date: Kai Ming Fung, 11/4/2020

2. Java version used, if not the official version for the class:

java version "9.0.4"
Java(TM) SE Runtime Environment (build 9.0.4+11)
Java HotSpot(TM) 64-Bit Server VM (build 9.0.4+11, mixed mode)

3. Precise command-line compilation examples / instructions:

> To compile and run:

>javac -cp "gson-2.8.2.jar" MiniWork.java
>java -cp ".;gson-2.8.2.jar" MiniWork

4. Precise examples / instructions to run this program:

> To compile and run in the shell windows:

>javac -cp "gson-2.8.2.jar" MiniWork.java
>java -cp ".;gson-2.8.2.jar" MiniWork

I am not sure if its the same because I run my program through Eclipse

Used "localhost" for the default IP address, used 4545 as the defulat port for the joke server 

5. List of files needed for running the program.

 a. Block.java
 b. MiniWork.java
 c. gson-2.8.2.jar
 d. StringUtil.java

5. Notes:



----------------------------------------------------------*/

import java.security.MessageDigest;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import javax.xml.bind.DatatypeConverter;  Java 1.9 does not like this. War on XML!
import java.util.Arrays;
import java.util.LinkedList;
//========================================referenced from Work.java================================================
public class MiniWork {

    public static String ByteArrayToString(byte[] ba){
	StringBuilder hex = new StringBuilder(ba.length * 2);
	for(int i=0; i < ba.length; i++){
	    hex.append(String.format("%02X", ba[i]));
	}
	return hex.toString();
    }

    public static String randomAlphaNumeric(int count) {
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) {
	    int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	    builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
    }
	
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static String someText = "one two three";
    static String randString;
	static LinkedList<Block> blockchain = new LinkedList<Block>();
    public static void main(String[] args) throws Exception {
    	
		String uuid = "";
		uuid = new String(UUID.randomUUID().toString());
		
		//simply adding blocks 
		blockchain.add(new Block("First block with random data", "0",1,uuid)); //put some simple data

		blockchain.add(new Block("This is the second block",blockchain.get(blockchain.size()-1).hash,2,uuid));
		
		blockchain.add(new Block("The third block with random data",blockchain.get(blockchain.size()-1).hash,3,uuid));
		
		blockchain.add(new Block("The fourth block with random data",blockchain.get(blockchain.size()-1).hash,4,uuid));
			
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);//trying convert blockchain to String then to JSON
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		
    	
    	int j=0;
    	for (j=0;j<4;j++) {
    	String concatString = "";  // Random seed string concatenated with the existing data
    	String stringOut = ""; // Will contain the new SHA256 string converted to HEX and printable.

    	String stringIn = blockchain.get(j).toString();
    	
    	//String stringIn = ourInput.nextLine();

    	randString = randomAlphaNumeric(8);
    	int counter =j+1;
    	System.out.println("=====================================================================");
    	System.out.println("For block: "+ counter);
    	System.out.println("Our example random seed string is: " + randString + "\n");
    	System.out.println("Concatenated with the \"data\": " + stringIn + randString + "\n");

    	System.out.println("Number will be between 0000 (0) and FFFF (65535)\n");
    	int workNumber = 0;     // Number will be between 0000 (0) and FFFF (65535), here's proof:
    	workNumber = Integer.parseInt("0000",16); // Lowest hex value
    	System.out.println("0x0000 = " + workNumber);

    	workNumber = Integer.parseInt("FFFF",16); // Highest hex value
    	System.out.println("0xFFFF = " + workNumber + "\n");

    	try {

    	    for(int i=1; i<20; i++){ // Limit how long we try for this example.
    		randString = randomAlphaNumeric(8); // Get a new random AlphaNumeric seed string
    		concatString = stringIn + randString; // Concatenate with our input string (which represents Blockdata)
    		MessageDigest MD = MessageDigest.getInstance("SHA-256");
    		byte[] bytesHash = MD.digest(concatString.getBytes("UTF-8")); // Get the hash value
    	
    		//stringOut = DatatypeConverter.printHexBinary(bytesHash); // Turn into a string of hex values Java 1.8
    		stringOut = ByteArrayToString(bytesHash); // Turn into a string of hex values, java 1.9 
    		System.out.println("Hash is: " + stringOut); 

    		workNumber = Integer.parseInt(stringOut.substring(0,4),16); // Between 0000 (0) and FFFF (65535)
    		System.out.println("First 16 bits in Hex and Decimal: " + stringOut.substring(0,4) +" and " + workNumber);
    		if (!(workNumber < 20000)){  // lower number = more work.
    		    System.out.format("%d is not less than 20,000 so we did not solve the puzzle\n\n", workNumber);
    		    TimeUnit.SECONDS.sleep(1);
    		}
    		if (workNumber < 20000){
    		    System.out.format("%d IS less than 20,000 so puzzle solved!\n", workNumber);
    		    System.out.println("The seed (puzzle answer) was: " + randString);
    		    System.out.println("=====================================================================");
    		    TimeUnit.SECONDS.sleep(1);
    		    break;
    		}
    	    }
    	    
    	}catch(Exception ex) {ex.printStackTrace();}
    	}

    }
    
    
}
