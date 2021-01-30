package crypto;
import ds.MerkleTree;
import ds.MerkleNode;
import java.util.*;
import crypto.Block;
public class Blockchain {

    LinkedList <Block> chain = new LinkedList<Block>();  
    public int count = 0; 

    public Blockchain(){
        this.chain.add(new Block(0,"0")); //genesis block
    }
     
    public void makeTransactions(Transaction ts[]){
        Block latest = this.getLatestBlock();
        for(int i = 0; i < ts.length;i++){
            if(i%latest.MAX_SIZE == 0 && i != 0){
                latest.mt = new MerkleTree(ts);
                latest.root = latest.mt.prepareMerkleTree();
                this.addBlock();
                latest = this.getLatestBlock();
            }
            latest.transactions[latest.transactionPointer++] = ts[i];
        }
        latest.mt = new MerkleTree(ts);
        latest.root = latest.mt.prepareMerkleTree();
    }
    
    public boolean addBlock(){
        this.count++;
        Block b = new Block(this.count, this.getLatestBlock().currentHash);
        this.chain.addLast(b);
        return true;
    }

    public Block getLatestBlock(){
        return this.chain.getLast();
    }

    public void showAllBlocks(){//check thgis 
        for(int i = 0; i < this.chain.size(); i++){
            Block current = this.chain.get(i);
            System.out.println("BLOCK "+i);
            System.out.println("Block hash: "+current.currentHash+"\t Previous Hash: "+current.previousHash+"\t Timestamp: "+current.timestamp);
            if(i > 0){
                System.out.println("Merkle Root: "+current.root+"\t NodeHash: "+current.root.nodeHash+"\t Level: "+current.root.level);
                System.out.println("Transacations: "+current.transactions);
                current.showTransactions();
            }
        }
    }
}