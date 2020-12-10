//Part G


import java.io.StringWriter;
import java.io.StringReader;

/* CDE: The encryption needed for signing the hash: */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.spec.*;
// Ah, heck:
import java.security.*;

// Produces a 64-bye string representing 256 bits of the hash output. 4 bits per character
import java.security.MessageDigest; // To produce the SHA-256 hash.
//
//import org.json.simple.*;
//import org.json.simple.parser.*;
/* CDE Some other uitilities: */

import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;
import java.text.*;
import java.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;


//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;

//=============================================referenced from BlockJ.java============================
class PublicKeyToJSON {
	String PbKey;
	public String getpbkey() {return PbKey;}
	  public void setpbkey (String PB){this.PbKey = PB;}
	
}


class PublicKeyBlockRecord{
	String PubKey;
	int PID;

	// Getter and setter methods for the public key data
	public String getPubKey(){return this.PubKey;}

		public void setPubKey(String PK){this.PubKey = PK;}

	public int getPID(){return this.PID;}

		public void setPID(int ID){this.PID = ID;}
}


//=============================================referenced from BlockJ.java============================
public class KeyPairTest {
	
	  public static String CSC435Block =
			    "You will design and build this dynamically. For now, this is just a string.";
	
	  public static String SignedSHA256;
  	public static int PID = 0;
  	public static int totalNumProcesses = 3;
  	ArrayList<PublicKeyToJSON> sampleList = new ArrayList<PublicKeyToJSON>();
  	
  	
  	//was in an attemp to run key pair for 3 different processes 
	public static void main(String args[]) throws Exception {
			
	//	String path = "C:\\Users\\Aiden\\Desktop\\eclipse-workspace\\KeyPairTest\\src";
		if (args.length < 1) PID = 0;
    	else if (args[0].equals("0")) PID = 0;
    	else if (args[0].equals("1")) PID = 1;
    	else if (args[0].equals("2")) PID = 2;
    	else PID = 0; 
		
		
		
		CreateKeyPair(); //calling the method to generates a public key / private key pair
		
	}
	
  	private static PrivateKey privateKey;

	public static void CreateKeyPair(){
		//String path = "C:\\Users\\Aiden\\Desktop\\eclipse-workspace\\KeyPairTest\\src";

		try {
			
			for (int i = 0; i < totalNumProcesses; i++){ //runnning this method for 3 different processes

			    MessageDigest md = MessageDigest.getInstance("SHA-256");
			    md.update (CSC435Block.getBytes());
			    byte byteData[] = md.digest();
				
			    StringBuffer sb = new StringBuffer();
			    for (int j = 0; j < byteData.length; j++) {
			      sb.append(Integer.toString((byteData[j] & 0xff) + 0x100, 16).substring(1));
			    }
		
			    System.out.println("Starting to generate key pair" + "\n");	    
			    
			    String SHA256String = sb.toString();
				
		PublicKeyBlockRecord pkbr = new PublicKeyBlockRecord();	

		KeyPair kp = generateKeyPair(1000); //running with a random seed
		
	    byte[] digitalSignature = signData(SHA256String.getBytes(), kp.getPrivate()); //we sign the data with the private key
		
		PublicKey pubKey = kp.getPublic(); //get the public key from the key pair
		privateKey = kp.getPrivate(); //get the private key from the key pair

		String encodedPubKey = Base64.getEncoder().encodeToString(pubKey.getEncoded()); //encode the public key
		pkbr.setPID(PID);	// Set process ID of PublicKeyBlockRecord
		pkbr.setPubKey(encodedPubKey); //set the public key for the publickeyblockrecord
		
		System.out.println("Endcoded PublicKey for: " + "Process" + i + " : " + encodedPubKey + "\n");
		
	    boolean verified = verifySig(SHA256String.getBytes(), pubKey, digitalSignature); //try to verify the signature with the public key
	    System.out.println("Has the signature been verified: " + verified + "\n");		

	    
		//byte[] byte_pubkey  = Base64.getDecoder().decode(encodedPubKey);
	    byte[] bytePubkey = kp.getPublic().getEncoded();
	    String stringKey = Base64.getEncoder().encodeToString(bytePubkey);  //get the public key in String form
	    System.out.println("Key in String form: " + stringKey);
	//	System.out.println("Endcoded PublicKey for: " + "Process" + i + " : " + byte_pubkey);
	    
	    
	    PublicKeyToJSON savepbkey=new PublicKeyToJSON(); //convert the String public key to JSON
	    savepbkey.setpbkey(stringKey);
	    
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    
	    // Convert the Java object to a JSON String:
	    String json = gson.toJson(savepbkey);
	    
	    System.out.println("\nJSON (suffled) String list is: " + json); 
	    
	    try (FileWriter writer = new FileWriter("PublicKey.json")) {  //save the JSON public key to disk
	        gson.toJson(savepbkey, writer);
	      } catch (IOException e) {e.printStackTrace();}
	    

	    
	    
	    Gson gson1 = new Gson();
	    try (Reader reader = new FileReader("PublicKey.json")) { //now we try to read the JSON public key from disk 
	        // Read and convert JSON File to a Java Object:
	    	PublicKeyToJSON PkIn = gson1.fromJson(reader, PublicKeyToJSON.class);      
	        // Print the blockRecord:
	        System.out.println("The Read In Encoded Public Key : "  + PkIn.PbKey+ "\n");
	        
	        String ReadInPBKey=PkIn.PbKey.toString(); //convert the JSON public key to String
	     
	        byte[] byteReadInPubkey = ReadInPBKey.getBytes();
	        // to decode the public key, and then put it into Public Key
	        byte[] publicBytes = Base64.getDecoder().decode(ReadInPBKey); //public key decoded in bytes form 
	        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        PublicKey pubReadInKey = keyFactory.generatePublic(keySpec);
	        
	        SignedSHA256 = Base64.getEncoder().encodeToString(digitalSignature); //encode it
	        System.out.println("The signed SHA-256 string: " + SignedSHA256 + "\n");
	        byte[] testSignature = Base64.getDecoder().decode(SignedSHA256); //check the signature 
	        System.out.println("Testing restore of signature: " + Arrays.equals(testSignature, digitalSignature));
	        
	        verified = verifySig(SHA256String.getBytes(), pubReadInKey, testSignature); //try to verify the signature
	        System.out.println("Has the restored signature been verified: " + verified + "\n");
	        System.out.println("=============================================================================" + "\n");


	      } catch (IOException e) {
	        e.printStackTrace();
	      }

		
	    
			}
			
		}catch (Exception e){e.printStackTrace();}
		
	}

	
	//=============================================referenced from BlockJ.java============================
	
	public static KeyPair generateKeyPair(long seed) throws Exception {
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		SecureRandom rng = SecureRandom.getInstance("SHA1PRNG", "SUN");
		rng.setSeed(seed);
		keyGenerator.initialize(1024, rng);

		return (keyGenerator.generateKeyPair());
	}
	
	  public static boolean verifySig(byte[] data, PublicKey key, byte[] sig) throws Exception {
		    Signature signer = Signature.getInstance("SHA1withRSA");
		    signer.initVerify(key);
		    signer.update(data);
		    
		    return (signer.verify(sig));
		  }
	
	  public static byte[] signData(byte[] data, PrivateKey key) throws Exception {
		    Signature signer = Signature.getInstance("SHA1withRSA");
		    signer.initSign(key);
		    signer.update(data);
		    return (signer.sign());
		  }
	  
	  
	
	

	
}


