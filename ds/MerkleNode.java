package ds;

import crypto.Transaction;
public class MerkleNode{
    public String nodeHash;
    public int level;
    public Transaction t;
    public MerkleNode leftChild;
    public MerkleNode rightChild;
    public MerkleNode(String hash,int level,Transaction t){
        this.t = t;
        this.level = level;
        this.nodeHash = hash;
        this.leftChild = this.rightChild = null;
    }
}
