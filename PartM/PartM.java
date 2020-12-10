//Part M
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*; 
import java.security.MessageDigest; 

class BlockA{ 
	  

    public String hash; 
    public String previousHash; 
    private String data; 
   public  String TimeStamp;

    public BlockA(String data, 
                 String previousHash) 
    { 
        this.data = data; 
        this.previousHash 
            = previousHash; 
        this.TimeStamp 
            =Long.toString(new Date().getTime()); 
        this.hash 
            = calculateHash(); 
    } 
  
    public String getTimeStamp() {return TimeStamp;}
    public void setTimeStamp(String TS){this.TimeStamp = TS;}
    
    // Function to calculate the hash 
    public String calculateHash() 
    { 
        String calculatedhash 
            = crypt.sha256( 
                previousHash 
                + TimeStamp
                + data); 
  
        return calculatedhash; 
    } 
} 

class crypt { 
	  

    public static String sha256(String input) 
    { 
        try { 
            MessageDigest sha 
                = MessageDigest 
                      .getInstance( 
                          "SHA-256"); 
            int i = 0; 
  
            byte[] hash 
                = sha.digest( 
                    input.getBytes("UTF-8")); 
  
            // hexHash will contain 
            // the Hexadecimal hash 
            StringBuffer hexHash 
                = new StringBuffer(); 
  
            while (i < hash.length) { 
                String hex 
                    = Integer.toHexString( 
                        0xff & hash[i]); 
                if (hex.length() == 1) 
                    hexHash.append('0'); 
                hexHash.append(hex); 
                i++; 
            } 
  
            return hexHash.toString(); 
        } 
        catch (Exception e) { 
            throw new RuntimeException(e); 
        } 
    } 
} 

class CompareBlockRecord implements Comparator<BlockA> {
	public int compare(BlockA first, BlockA second){
		return first.getTimeStamp().compareTo(second.getTimeStamp());
	}
}


public class PartM{
	public static BlockingQueue<BlockA> Queue;
	 public static ArrayList<BlockA> blockchain 
     = new ArrayList<BlockA>(); 
	 
	 public static void main(String[] args) 
	    { 	 
	    
	    
	    
	    		Queue = new PriorityBlockingQueue<BlockA>(5,new CompareBlockRecord()); //create a queue for priorityblockingqueue, calling the compare method to add blocks in order according to the timestamp
	        // Adding the data to the ArrayList 

	    		//creating the simple blockchain
	        blockchain.add(new BlockA( 
	            "First block", "0")); 
	        
	        blockchain.add(new BlockA( 
	            "Second block", 
	            blockchain 
	                .get(blockchain.size() - 1) 
	                .hash)); 
	  
	        blockchain.add(new BlockA( 
	            "Third block", 
	            blockchain 
	                .get(blockchain.size() - 1) 
	                .hash)); 
	  
	        blockchain.add(new BlockA( 
	            "Fourth block", 
	            blockchain 
	                .get(blockchain.size() - 1) 
	                .hash)); 
	  
	        blockchain.add(new BlockA( 
	            "Fifth block", 
	            blockchain 
	                .get(blockchain.size() - 1) 
	                .hash)); 
//adding blocks to the queue
        Queue.add(new BlockA( 
            "First block", "0")); 
        
        Queue.add(new BlockA( 
            "Second block", 
            blockchain 
                .get(blockchain.size() - 1) 
                .hash)); 
  
        Queue.add(new BlockA( 
            "Third block", 
            blockchain 
                .get(blockchain.size() - 1) 
                .hash)); 
  
        Queue.add(new BlockA( 
            "Fourth block", 
            blockchain 
                .get(blockchain.size() - 1) 
                .hash)); 
        Queue.add(new BlockA( 
	            "Fifth block", 
	            blockchain 
	                .get(blockchain.size() - 1) 
	                .hash)); 
        Queue.add(new BlockA( 
	            "Sixth block", 
	            blockchain 
	                .get(blockchain.size() - 1) 
	                .hash));  
	        	        
	        //showing the result of the priority blockchain
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(Queue);
			System.out.println("\nThe Priority block chain (sorted (queued) by timestamp priority): ");
			System.out.println(blockchainJson);     
	    } 
	
}