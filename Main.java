import java.util.*;

import crypto.Transaction;
import crypto.Block;
import crypto.Blockchain;
public class Main {
    Blockchain blockchain;
    public void run(Transaction transactions[]){
        //Transaction transactions[] = {new Transaction("ro","aj",100),new Transaction("ro","aj",200),new Transaction("ro","aj",300),new Transaction("ro","aj",400),new Transaction("ro","aj",500)};
        this.blockchain = new Blockchain();
        this.blockchain.addBlock();
        this.blockchain.makeTransactions(transactions);
        this.blockchain.showAllBlocks();
    }   
    public void makeRandomTransactions(int n){
        Transaction transactions[] = new Transaction[n];
        for(int i = 0; i < n; i++){
            transactions[i] = new Transaction("sender","receiver",1);
        }
        this.run(transactions);
    }
}
