package crypto;
import java.util.HashMap;
import ds.Hasher;
import ds.MerkleTree;
import ds.MerkleNode;

import java.sql.Timestamp;
import java.time.Instant;

import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 

public class Block {
    static final int MAX_SIZE = 50; //max number of transactions in one block
    public int index;
    public long timestamp;
    public String currentHash;   
    public Transaction transactions[]; 
    public int transactionPointer;
    public MerkleTree mt;
    public MerkleNode root;
    public String previousHash;
   
    public Block(int index, String previous){
        this.index = index;
        Timestamp t = new Timestamp(System.currentTimeMillis());
        this.timestamp = t.getTime();
        Hasher h = new Hasher();
        this.previousHash = previous;
        this.currentHash = h.computeSHA256(String.valueOf(this.timestamp));
        this.transactionPointer = 0;    
        this.transactions = new Transaction[this.MAX_SIZE];
    }   
    public boolean isFull(){
        return this.transactionPointer < this.MAX_SIZE ? false : true;
    }
    public void showTransactions(){
        Transaction t = new Transaction("","",0);
        for(int i = 0; i < this.transactionPointer; i++){
            t = this.transactions[i];
            System.out.println("Sender :"+t.sender+"\t Receiver: "+t.receiver+"\t Amount: "+t.amount+"\t Transaction Hash: "+t.hash);
        }
        System.out.println("number of transactions "+this.transactionPointer);
    }
}