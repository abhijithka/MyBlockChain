import java.util.ArrayList;
import java.util.List;
import com.google.gson.GsonBuilder;

public class BlockChain {

    public static List<Block> blockchain = new ArrayList<> ();
    public static int difficulty = 6;

    public static void main(String[] args) {
        blockchain.add(new Block("First block", "0"));
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("Second block", blockchain.get(blockchain.size()-1).hash));
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block("Third block", blockchain.get(blockchain.size()-1).hash));
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("Validity of blockchain : " + isValidChain());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockChainJson);
    }

    public static Boolean isValidChain() {
        Block currBlock;
        Block prevBlock;

        for (int i=1; i<blockchain.size(); i++) {
            currBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);
            if (!(currBlock.hash.equals(currBlock.computeHash()))) {
                System.out.println("Current hashes are not equal");
                return false;
            }
            if (!(currBlock.prevHash.equals(prevBlock.hash))) {
                System.out.println("Previous Hashes are not equal");
                return false;
            }
        }
        return true;
    }
}
