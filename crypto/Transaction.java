package crypto;
import ds.Hasher;
import java.sql.Timestamp;
import java.time.Instant;
public class Transaction{
    public String sender;
    public String receiver; 
    public int amount;
    public String hash;
    public long timestamp; //issue timestamp, still needs to be validated 
    public Transaction(String sender, String receiver, int amount){
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        Timestamp t = new Timestamp(System.currentTimeMillis());
        this.timestamp = t.getTime();
        
        this.hash = new Hasher().computeSHA256(this.sender+this.receiver+String.valueOf(this.amount)+String.valueOf(this.timestamp));
    }
}