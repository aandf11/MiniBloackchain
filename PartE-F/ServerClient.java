//Part E - F

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
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
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
	public FirstServerWorker(Socket s) {
		this.sock=s;
	}
	   public void run(){
			try{
				
			    BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			    String data = in.readLine ();  
			//	System.out.println(" got a new key: " + data);
				System.out.println(data);
				in.close();
                this.sock.close();
			}catch (IOException ioe) {System.out.println(ioe);}
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
		BufferedReader in;
		public SecondServerWorker(Socket s) {
			this.sock=s;
		}
		   public void run(){
				try{
					in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
				//	in = new ObjectInputStream(sock.getInputStream());
				//	System.out.println("Received a new Unverified Block");
					String data=in.readLine();	   
					System.out.println(data);
				//	System.out.println("Hello multicast message from Process 1");
					in.close();
	                this.sock.close();
				}catch (IOException ioe) {System.out.println(ioe);}
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
		BufferedReader in;
		String inputString;

		public BlockChainWorker(Socket s){this.sock = s;}
	

		public void run(){
			try {
	            in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
				inputString = (String)in.readLine();
			//	StringReader reader = new StringReader(inputString);
				System.out.println("Hello multicast message from Process 2");

			//	System.out.println("Received new Block Chain, now updating local blockchain with new one received.");
				in.close();
                this.sock.close();
			}
			catch (Exception e){e.printStackTrace();}
		}

	}
	
	
	
	
//=========================================Referenced from bc.java===============================================================
public class ServerClient {
	 static String serverName = "localhost";
	public static int KeyServerPortBase = 4710;		// Port number for server receiving Public keys
  	public static int UnverifiedBlockServerPortBase = 4820;	//Port no. for server of the unverified blockchain
  	public static int BlockchainServerPortBase = 4930; //Port no. for the blockchain server
  	static int numProcesses = 3; //set the number of processes to 3
  	public static int PID = 0; //initial process id to 0
  	public static Boolean runSystem = false; // set runsystem to false

  	
//    public void UnverifiedSend (){ // Multicast some unverified blocks to the other processes
//
//	Socket UVBsock; // Will be client connection to the Unverified Block Server for each other process.
//	BlockRecord tempRec;
//
//	String fakeBlockData;
//	String T1;
//	String TimeStampString;
//	Date date;
//	Random r = new Random();
//      
//
//	try{
//
//	    ObjectOutputStream toServerOOS = null; // Stream for sending Java objects
//	    for(int i = 0; i < numProcesses; i++){// Send some sample Unverified Blocks (UVBs) to each process
//		System.out.println("Sending UVBs to process " + i + "...");
//
//		    UVBsock = new Socket(serverName, 4820 + i);
//		    toServerOOS = new ObjectOutputStream(UVBsock.getOutputStream());
//		    Thread.sleep((r.nextInt(9) * 100)); // Sleep up to a second to randominze when sent.
//		    toServerOOS.writeObject("test"); // Send the unverified block record object
//		    toServerOOS.flush();
//		    UVBsock.close();
//
//	    }
//	    Thread.sleep((r.nextInt(9) * 100)); // Sleep up to a second to randominze when sent.
//	}catch (Exception x) {x.printStackTrace ();}
//    }
  	
  	
    public static void SendKey (){ // Multicast our public key to the other processes
	Socket sock;
	PrintStream toServer;
	try{
	    for(int i=0; i< numProcesses; i++){// Send our public key to all servers.
		sock = new Socket(serverName, 4710 + i);
		toServer = new PrintStream(sock.getOutputStream());
		toServer.println("Hello multicast message from Process 0");
	//	toServer.println("FakeKeyProcess" + ServerClient.PID); 
		toServer.flush();
		sock.close();
	    }
	    
	   // TestOutput();
	}catch (Exception x) {x.printStackTrace ();}
    }

    public static void SendKey1 (){ // Multicast our public key to the other processes
	Socket sock;
	PrintStream toServer;
	try{
	    for(int i=0; i< numProcesses; i++){// Send our public key to all servers.
		sock = new Socket(serverName, 4820 + i);
		toServer = new PrintStream(sock.getOutputStream());
		toServer.println("Hello multicast message from Process 1");
	//	toServer.println("FakeKeyProcess" + ServerClient.PID); 
		toServer.flush();
		sock.close();
	    }
	    
	  //  TestOutput();
	}catch (Exception x) {x.printStackTrace ();}
    }
    
    public static void SendKey2 (){ // Multicast our public key to the other processes
	Socket sock;
	PrintStream toServer;
	try{
	    for(int i=0; i< numProcesses; i++){// Send our public key to all servers.
		sock = new Socket(serverName, 4930 + i);
		toServer = new PrintStream(sock.getOutputStream());
		toServer.println("Hello multicast message from Process 2");
	//	toServer.println("FakeKeyProcess" + ServerClient.PID); 
		toServer.flush();
		sock.close();
	    }
	    
	 //  TestOutput();
	}catch (Exception x) {x.printStackTrace ();}
    }
    public static void TestOutput(){
    	Socket sock;
		ObjectOutputStream out;
		try {
			for (int i = 0; i < numProcesses; i++){
				sock = new Socket("localhost",4930+i);
				out = new ObjectOutputStream(sock.getOutputStream());
				out.flush();
				out.close();
			}
		}catch (Exception x) {x.printStackTrace ();}
		
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
