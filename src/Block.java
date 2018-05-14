import  java.security.MessageDigest;

public class Block {
    private String data;
    private long timeStamp;
    public String hash;
    public String prevHash;

    public Block(String data, String prevHash) {
        this.prevHash = prevHash;
        this.data = data;
        this.timeStamp = System.currentTimeMillis();
        this.hash = computeHash(data, timeStamp, prevHash);
    }

    private String computeHash(String data, long timeStamp, String prevHash) {
        return SecurityHelper.getHash(data + Long.toString(timeStamp) + prevHash);
    }
}
