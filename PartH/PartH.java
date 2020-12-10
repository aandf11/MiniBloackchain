//Part H

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class PublicKeyToJSON1 {
	String PbKey;
	public String getpbkey() {return PbKey;}
	  public void setpbkey (String PB){this.PbKey = PB;}
	
}


class PublicKeyBlockRecord1{
	String PubKey;
	int PID;

	// Getter and setter methods for the public key data
	public String getPubKey(){return this.PubKey;}

		public void setPubKey(String PK){this.PubKey = PK;}

	public int getPID(){return this.PID;}

		public void setPID(int ID){this.PID = ID;}
}

// ========================================referenced from bc.java=========================================
class BlockRecord implements Serializable { 
    String TimeStamp;
    String Data;

    /* Examples of accessors for the BlockRecord fields: */
    public String getTimeStamp() {return TimeStamp;}
    public void setTimeStamp(String TS){this.TimeStamp = TS;}
    public String getData() {return Data;}
    public void setData(String DATA){this.Data = DATA;}
}

class FirstServerWorker extends Thread{
	Socket sock;
	ObjectInputStream in;
    String JSONPubKey = "";
	public FirstServerWorker(Socket s) {
		this.sock=s;
	}
	   public void run(){
			try{
				in = new ObjectInputStream(sock.getInputStream());
				JSONPubKey = (String) in.readObject();
				Gson gson1 = new Gson();
		    	PublicKeyToJSON1 PkIn = gson1.fromJson(JSONPubKey, PublicKeyToJSON1.class);
		    	String ReadInPBKey=PkIn.PbKey.toString();
		    	System.out.println("First Public Key is : " + ReadInPBKey + "\n");
				in.close();
                this.sock.close();
			}catch (IOException ioe) {System.out.println(ioe);} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
}

	class FirstServer implements Runnable {
  		int port;
  		static ServerSocket sock;
		FirstServer( int p) {
		  	      this.port=p;
		  	      System.out.println("Creating " +  port + " (Public Key Server)");
		  	   }
		   public void run(){
				System.out.println("Starting Key Server input thread using " + Integer.toString(port));
				try{

					sock = new ServerSocket(port,6);
					while(true) {
			        Socket socket = sock.accept();	
					new FirstServerWorker(socket).start();
					}
				}catch (IOException ioe) {System.out.println(ioe);}
		   }
	}
	
	
	class SecondServerWorker extends Thread{
		Socket sock;
		ObjectInputStream in;
	    String JSONPubKey = "";
		public SecondServerWorker(Socket s) {
			this.sock=s;
		}
		   public void run(){
				try{
					in = new ObjectInputStream(sock.getInputStream());
					JSONPubKey = (String) in.readObject();
					Gson gson1 = new Gson();
			    	PublicKeyToJSON1 PkIn = gson1.fromJson(JSONPubKey, PublicKeyToJSON1.class);
			    	String ReadInPBKey=PkIn.PbKey.toString();
			    	System.out.println("Second Public Key is : " + ReadInPBKey +"\n");
					in.close();
	                this.sock.close();
				}catch (IOException ioe) {System.out.println(ioe);} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }
	}	
	
	class SecondServer implements Runnable {
		ServerSocket sock;
		int port;
		public SecondServer(int p){this.port = p;
	      System.out.println("Creating " +  port + " (Second Server)");
		}
		   public void run(){
				System.out.println("Starting Second Server input thread using " + Integer.toString(port));
				try{
					sock = new ServerSocket(port,6);
					while(true) {
			        Socket socket = sock.accept();	
					new SecondServerWorker(socket).start();
					}
				}catch (IOException ioe) {System.out.println(ioe);}
		   }
	}

	
	class BlockChainServer implements Runnable {
	  	   int port;
	  	   ServerSocket sock;
	  	 BlockChainServer(int p) {
	  	      this.port=p;
	  	      System.out.println("Creating " +  port + " (BlockChain Server)");
	  	   }
	  	   
	 	public void run(){
				System.out.println("Starting Block Chain Server input thread using " + Integer.toString(port));
			try {
				sock = new ServerSocket(port,6);
				while (true){
					// Once server accepts client connection set the boolean variable runSystem to true so process0 and process1 can start
					Socket socket = sock.accept();
					new BlockChainWorker(socket).start();	// Start the BlockChainWorker thread
				}
			}
			catch (Exception e){e.printStackTrace();}
		}
	 	}
	
	class BlockChainWorker extends Thread {
		Socket sock;
		ObjectInputStream in;
	    String JSONPubKey = "";

		public BlockChainWorker(Socket s){this.sock = s;}
	

		public void run(){
			try {
				in = new ObjectInputStream(sock.getInputStream());
				JSONPubKey = (String) in.readObject();
				Gson gson1 = new Gson();
		    	PublicKeyToJSON1 PkIn = gson1.fromJson(JSONPubKey, PublicKeyToJSON1.class);
		    	String ReadInPBKey=PkIn.PbKey.toString();
		    	System.out.println("Third Public Key is : " + ReadInPBKey + "\n");
				in.close();
                this.sock.close();
			}
			catch (Exception e){e.printStackTrace();}
		}

	}
	
	
	
	
//=========================================Referenced from bc.java===============================================================
public class PartH {
	 static String serverName = "localhost";
	public static int KeyServerPortBase = 4710;		// Port number for server receiving Public keys
  	public static int UnverifiedBlockServerPortBase = 4820;	//Port no. for server of the unverified blockchain
  	public static int BlockchainServerPortBase = 4930; //Port no. for the blockchain server
  	static int numProcesses = 3; //set the number of processes to 3
  	public static int PID = 0; //initial process id to 0
  	public static Boolean runSystem = false; // set runsystem to false
  	
  	private static PrivateKey privateKey;

	  public static String CSC435Block =
			    "You will design and build this dynamically. For now, this is just a string.";
//    public void UnverifiedSend (){ // Multicast some unverified blocks to the other processes
////
//	Socket UVBsock; // Will be client connection to the Unverified Block Server for each other process.
//	BlockRecord tempRec;
////
//	String fakeBlockData;
//	String T1;
//	String TimeStampString;
//	Date date;
//	Random r = new Random();
////      
////
//	try{
////
//	    ObjectOutputStream toServerOOS = null; // Stream for sending Java objects
//	    for(int i = 0; i < numProcesses; i++){// Send some sample Unverified Blocks (UVBs) to each process
//	    	System.out.println("Hello multicast message from Process"  + i + "...");// Sleep up to a second to randominze when sent.
//	    	
//		    UVBsock = new Socket(serverName, 4820 + i);
//		    toServerOOS = new ObjectOutputStream(UVBsock.getOutputStream());
//		    Thread.sleep((r.nextInt(9) * 100));
//	    toServerOOS.writeObject("test"); // Send the unverified block record object
//		    toServerOOS.flush();
//		    UVBsock.close();
////
//	    }
//	    Thread.sleep((r.nextInt(9) * 100)); // Sleep up to a second to randominze when sent.
//	}catch (Exception x) {x.printStackTrace ();}
//    }
  	
  	
    public static void SendKey (){ // Multicast our public key to the other processes
	Socket sock;
	ObjectOutputStream toServer;
	try{

	    for(int i=0; i< numProcesses; i++){// Send our public key to all servers.
		sock = new Socket(serverName, 4710 + i);
		//toServer = new PrintStream(sock.getOutputStream());
		PublicKeyBlockRecord1 pkbr = new PublicKeyBlockRecord1();
		KeyPair kp = generateKeyPair(1000);
		PublicKey pubKey = kp.getPublic();
		privateKey = kp.getPrivate();
		String encodedPubKey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
		pkbr.setPID(PID);	// Set process ID of PublicKeyBlockRecord
		pkbr.setPubKey(encodedPubKey);	// Set Public Key of PublicKeyRecord
		PublicKeyToJSON1 savepbkey=new PublicKeyToJSON1();
		savepbkey.setpbkey(encodedPubKey);
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    // Convert the Java object to a JSON String:
	    String json = gson.toJson(savepbkey);
	    
		toServer = new ObjectOutputStream(sock.getOutputStream());
		toServer.writeObject(json);
	//	toServer.println("FakeKeyProcess" + ServerClient.PID); 
		toServer.flush();
		sock.close();
	    }
	    
	   // TestOutput();
	}catch (Exception x) {x.printStackTrace ();}
    }

    public static void SendKey1 (){ // Multicast our public key to the other processes
	Socket sock;
	ObjectOutputStream toServer;
	try{

	    for(int i=0; i< numProcesses; i++){// Send our public key to all servers.
		sock = new Socket(serverName, 4820  + i);
		//toServer = new PrintStream(sock.getOutputStream());
		PublicKeyBlockRecord1 pkbr = new PublicKeyBlockRecord1();
		KeyPair kp = generateKeyPair(999);
		PublicKey pubKey = kp.getPublic();
		privateKey = kp.getPrivate();
		String encodedPubKey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
		pkbr.setPID(PID);	// Set process ID of PublicKeyBlockRecord
		pkbr.setPubKey(encodedPubKey);	// Set Public Key of PublicKeyRecord
		PublicKeyToJSON1 savepbkey=new PublicKeyToJSON1();
		savepbkey.setpbkey(encodedPubKey);
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    // Convert the Java object to a JSON String:
	    String json = gson.toJson(savepbkey);
	    
		toServer = new ObjectOutputStream(sock.getOutputStream());
		toServer.writeObject(json);
	//	toServer.println("FakeKeyProcess" + ServerClient.PID); 
		toServer.flush();
		sock.close();
	    }
	    
	   // TestOutput();
	}catch (Exception x) {x.printStackTrace ();}
    }
    
    public static void SendKey2 (){ // Multicast our public key to the other processes
	Socket sock;
	ObjectOutputStream toServer;
	try{

	    for(int i=0; i< numProcesses; i++){// Send our public key to all servers.
		sock = new Socket(serverName, 4930  + i);
		//toServer = new PrintStream(sock.getOutputStream());
		PublicKeyBlockRecord1 pkbr = new PublicKeyBlockRecord1();
		KeyPair kp = generateKeyPair(998);
		PublicKey pubKey = kp.getPublic();
		privateKey = kp.getPrivate();
		String encodedPubKey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
		pkbr.setPID(PID);	// Set process ID of PublicKeyBlockRecord
		pkbr.setPubKey(encodedPubKey);	// Set Public Key of PublicKeyRecord
		PublicKeyToJSON1 savepbkey=new PublicKeyToJSON1();
		savepbkey.setpbkey(encodedPubKey);
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    // Convert the Java object to a JSON String:
	    String json = gson.toJson(savepbkey);
	    
		toServer = new ObjectOutputStream(sock.getOutputStream());
		toServer.writeObject(json);
	//	toServer.println("FakeKeyProcess" + ServerClient.PID); 
		toServer.flush();
		sock.close();
	    }
	    
	   // TestOutput();
	}catch (Exception x) {x.printStackTrace ();}
    }
    

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
	
	
	public static void main(String args[]) throws Exception { //accept an argument
		if (args.length < 1) PID = 0; //if the length of the argument is less than 1 , set PID to 0
    	else if (args[0].equals("0")) PID = 0; //if arg is"0" , PID set to 0, etc.
    	else if (args[0].equals("1")) PID = 1;
    	else if (args[0].equals("2")) PID = 2;
    	else PID = 0; 		
		
    	System.out.println("Process: " + Integer.toString(PID) + " is running");
    	
    	FirstServer pretendKeyServer = new FirstServer(KeyServerPortBase + PID); //Now we set up the first thread
    	new Thread(pretendKeyServer).start(); //start the thread 
    	
    	SecondServer unverifiedBlockServer = new SecondServer(UnverifiedBlockServerPortBase+ PID); //set up second thread
    	new Thread(unverifiedBlockServer).start(); //start second thread
    	
    	BlockChainServer blockChainServer = new BlockChainServer(BlockchainServerPortBase + PID); //set up the third thread
    	new Thread(blockChainServer).start(); //start the third thread
    	
     	if (PID == 2) runSystem = true; //and if the PID is 2, set runsystem to true
     	while (!runSystem) Thread.sleep(200); //if runsystem is false, we sleep for 200
     	  	
    	SendKey(); //run the SendKey() method
    	try{Thread.sleep(1000);}catch(Exception e){}  //catch any exception
    	SendKey1();
    //	new ServerClient().UnverifiedSend();
    	try{Thread.sleep(1000);}catch(Exception e){}
    	SendKey2();
    	try{Thread.sleep(1000);}catch(Exception e){}
	}
}