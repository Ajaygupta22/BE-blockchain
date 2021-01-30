package ds;

import ds.Hasher;
import crypto.Transaction;
import ds.MerkleNode;

import java.util.LinkedList; 
import java.util.Queue;
public class MerkleTree{
    public Transaction transactions [];
    private int l;
    private Queue<MerkleNode> q = new LinkedList<>();
    private int currentLevel;
    private Hasher h = new Hasher();
    public MerkleNode root;
    public MerkleTree(Transaction transactions[]){
        this.transactions = transactions;
        this.currentLevel = (int)Math.ceil(this.transactions.length/2)+1;
        this.root = new MerkleNode("",0,null);
        //this.transactions = transactions;
        this.l = this.transactions.length;
        for(int i = 0; i < this.transactions.length; i++)//filling queue with all transactions as merkle nodes that are LEAF
        this.q.offer(new MerkleNode(this.transactions[i].hash, this.currentLevel,this.transactions[i])); 
    }
    public MerkleNode prepareMerkleTree(){
        MerkleNode left, right = new MerkleNode("",1,null);
        while(!this.q.isEmpty()){
            //make sure both are of the same level before removing
            left = this.q.poll();
            if(this.q.peek() != null && this.q.peek().level == left.level)
            right = this.q.poll();
            else
            right = left;
            
  
            String parentHash = h.computeSHA256(left.nodeHash+right.nodeHash);
            MerkleNode parent = new MerkleNode(parentHash, left.level - 1, null);
            this.root = parent;
            parent.leftChild = left;
            parent.rightChild = right;
            if(parent.level == 0)
            break;
            this.q.offer(parent);
        }
        return this.root;
    }
}

