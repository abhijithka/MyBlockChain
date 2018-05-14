import  java.security.MessageDigest;

public class Block {
    private String data;
    private long timeStamp;
    public String hash;
    public String prevHash;
    private int nonce;

    public Block(String data, String prevHash) {
        this.prevHash = prevHash;
        this.data = data;
        this.timeStamp = System.currentTimeMillis();

        this.hash = computeHash();
    }

    public String computeHash() {
        return SecurityHelper.getHash(prevHash + Long.toString(timeStamp) + nonce + data);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = computeHash();
        }
        System.out.println("New block mined : " + hash);
    }
}
