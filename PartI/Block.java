import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.text.*;
import java.util.Base64;
import java.util.Arrays;




public class Block {
	
	  String BlockID;
	  String VerificationProcessID;
	  String PreviousHash; // We'll copy from previous block
	  UUID uuid; // Just to show how JSON marshals this binary data.
	  String Fname;
	  String Lname;
	  String SSNum;
	  String DOB;
	  String Diag;
	  String Treat;
	  String Rx;
	  String RandomSeed; // Our guess. Ultimately our winning guess.
	  String WinningHash;
	  

	
	private int BlockNumber;
	public String hash; //To hold our digital signature
	public String previousHash; //hold previous block's has
	private String data; //hold our block data
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	private String uuuid;
	//Block Constructor.
	public Block(String data,String previousHash ,int blocknumber, String uuuid) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
		this.BlockNumber = blocknumber;
		this.uuuid = uuuid;

	}
	


public String calculateHash() {
	String calculatedhash = StringUtil.applySha256( 
			previousHash +
			Long.toString(timeStamp) +
			data 
			);
	return calculatedhash;
}

public void mineBlock(int difficulty) {
	String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
	while(!hash.substring( 0, difficulty).equals(target)) {
		nonce ++;
		hash = calculateHash();
	}
	System.out.println("Block Mined!!! : " + hash);
}






}



