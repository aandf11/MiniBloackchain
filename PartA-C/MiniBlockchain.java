/*--------------------------------------------------------
Part A-C
1. Name / Date: Kai Ming Fung, 11/4/2020

2. Java version used, if not the official version for the class:

java version "9.0.4"
Java(TM) SE Runtime Environment (build 9.0.4+11)
Java HotSpot(TM) 64-Bit Server VM (build 9.0.4+11, mixed mode)

3. Precise command-line compilation examples / instructions:

> To compile and run:

>javac -cp "gson-2.8.2.jar" MiniBlockchain.java
>java -cp ".;gson-2.8.2.jar" MiniBlockchain

4. Precise examples / instructions to run this program:

> To compile and run in the shell windows:

>javac -cp "gson-2.8.2.jar" MiniBlockchain.java
>java -cp ".;gson-2.8.2.jar" MiniBlockchain

I am not sure if its the same because I run my program through Eclipse

Used "localhost" for the default IP address, used 4545 as the defulat port for the joke server 

5. List of files needed for running the program.

 a. Block.java
 b. StringUtil.java
 c. gson-2.8.2.jar

5. Notes:



----------------------------------------------------------*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
//===========================================Referenced from BlockJ.java========================================

class BlockRecord{

	  String BlockID;
	  String VerificationProcessID;
	  String PreviousHash;
	  UUID uuid; 
	  String Fname;
	  String Lname;
	  String SSNum;
	  String DOB;
	  String Diag;
	  String Treat;
	  String Rx;
	  String RandomSeed;
	  String WinningHash;
	String TimeCreated;

	  public String getBlockID() {return BlockID;}
	  public void setBlockID(String BID){this.BlockID = BID;}

	  public String getVerificationProcessID() {return VerificationProcessID;}
	  public void setVerificationProcessID(String VID){this.VerificationProcessID = VID;}
	  
	  public String getPreviousHash() {return this.PreviousHash;}
	  public void setPreviousHash (String PH){this.PreviousHash = PH;}
	  
	  public UUID getUUID() {return uuid;} 
	  public void setUUID (UUID ud){this.uuid = ud;}

	  public String getLname() {return Lname;}
	  public void setLname (String LN){this.Lname = LN;}
	  
	  public String getFname() {return Fname;}
	  public void setFname (String FN){this.Fname = FN;}
	  
	  public String getSSNum() {return SSNum;}
	  public void setSSNum (String SS){this.SSNum = SS;}
	  
	  public String getDOB() {return DOB;}
	  public void setDOB (String RS){this.DOB = RS;}

	  public String getDiag() {return Diag;}
	  public void setDiag (String D){this.Diag = D;}

	  public String getTreat() {return Treat;}
	  public void setTreat (String Tr){this.Treat = Tr;}

	  public String getRx() {return Rx;}
	  public void setRx (String Rx){this.Rx = Rx;}

	  public String getRandomSeed() {return RandomSeed;}
	  public void setRandomSeed (String RS){this.RandomSeed = RS;}
	  
	  public String getWinningHash() {return WinningHash;}
	  public void setWinningHash (String WH){this.WinningHash = WH;}
	
	  public String getTimeCreated(){return this.TimeCreated;}

	
	  public void setTimeCreated(String TC){this.TimeCreated = TC;}
	}



public class MiniBlockchain {



	static LinkedList<Block> blockchain = new LinkedList<Block>();
	public static int difficulty = 0;
	
	public static void main(String[] args) {	
		String uuid = "";
		uuid = new String(UUID.randomUUID().toString());
		
		//simply adding blocks 
		blockchain.add(new Block("First block with random data", "0",1,uuid)); //put some simple data
		System.out.println("Mining block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		blockchain.add(new Block("This is the second block",blockchain.get(blockchain.size()-1).hash,2,uuid));
		System.out.println("Mining block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("The third block with random data",blockchain.get(blockchain.size()-1).hash,3,uuid));
		System.out.println("Mining block 3... ");
		blockchain.get(2).mineBlock(difficulty);	
		
		
		blockchain.add(new Block("The fourth block with random data",blockchain.get(blockchain.size()-1).hash,4,uuid));
		System.out.println("Mining block 4... ");
		blockchain.get(3).mineBlock(difficulty);	
		
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);//trying convert blockchain to String then to JSON
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		
		
		WriteJSON(); //calling write method to write JSON to disk
		ReadJSON(); //calling read method to read JSON file 
		

	}
	

public static Boolean isChainValid() {
	Block currentBlock; 
	Block previousBlock;
	String hashTarget = new String(new char[difficulty]).replace('\0', '0');
	//loop through blockchain to check hashes:
	for(int i=1; i < blockchain.size(); i++) {
		currentBlock = blockchain.get(i);
		previousBlock = blockchain.get(i-1);
		//compare registered hash and calculated hash:
		if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
			System.out.println("Current Hashes not equal");			
			return false;
		}
		//compare previous hash and registered previous hash
		if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
			System.out.println("Previous Hashes not equal");
			return false;
		}
		if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
			System.out.println("This block hasn't been mined");
			return false;
		}
	
	}
	return true;
}
//==================Referenced from BlockJ.java=========================================
public static void WriteJSON() {
//	BlockRecord blockRecord = new BlockRecord();

	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String blockchainJson = gson.toJson(blockchain);
	//To write JSON file to the disk:	
    try (FileWriter writer = new FileWriter("BlockRecord.json")) {
		System.out.println("Writing File: ");
        gson.toJson(blockchainJson, writer);
        System.out.println("Done!");
      } catch (IOException e) {
        e.printStackTrace();
      }
}
//==================Referenced from BlockJ.java=========================================
public static void ReadJSON(){
    System.out.println("\n=========> In ReadJSON <=========\n");
    
    Gson gson = new Gson();
    try {
        File myObj = new File("BlockRecord.json");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          System.out.println(data);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
//    try (Reader reader = new FileReader("BlockRecord.json")) {
//   
//      // Read and convert JSON File to a Java Object:
//      BlockRecord blockRecordIn = gson.fromJson(reader, BlockRecord.class);
//  //    Block blockRecordIn = gson.fromJson(reader, Block.class);
//  //  	System.out.println(reader);
//      System.out.println(reader);
//    	// LinkedList<Block> blocks = gson.fromJson(reader, new TypeToken<LinkedList<Block>>() {}.getType());
//	   // ArrayList<Block> blocks = gson.fromJson(reader, new TypeToken<ArrayList<Block>>() {}.getType());
//	   
//      // Print the blockRecord:
//  //    System.out.println(blockRecordIn);
// //     System.out.println("Name is: " + blockRecordIn.Fname + " " + blockRecordIn.Lname);
//
//  //    String INuid = blockRecordIn.uuid.toString();
//  //    System.out.println("String UUID: " + blockRecordIn.BlockID + " Stored-binaryUUID: " + INuid);
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

//========================================================================
//BlockJ.java
public static KeyPair generateKeyPair(long seed) throws Exception {
    KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
    SecureRandom rng = SecureRandom.getInstance("SHA1PRNG", "SUN");
    rng.setSeed(seed);
    keyGenerator.initialize(1024, rng);
    
    return (keyGenerator.generateKeyPair());
  }
public static byte[] signData(byte[] data, PrivateKey key) throws Exception {
    Signature signer = Signature.getInstance("SHA1withRSA");
    signer.initSign(key);
    signer.update(data);
    return (signer.sign());
  }
public static boolean verifySig(byte[] data, PublicKey key, byte[] sig) throws Exception {
    Signature signer = Signature.getInstance("SHA1withRSA");
    signer.initVerify(key);
    signer.update(data);
    
    return (signer.verify(sig));
  }



}


