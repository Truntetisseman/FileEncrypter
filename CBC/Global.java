package CBC;

import java.util.ArrayList;
import java.util.List;

public interface Global {

    //Keystore
    String keystoreDir = "/Users/patrickorum/Desktop/testfolder/keystore";
    String storeFileName = keystoreDir + "/" + "keystore.bks";
    char[] storePW = "burger".toCharArray();

    //Login
    String password = "pizza";
    String username = "psec";

    //Logs
    List<Logs> logs = new ArrayList<>();

    //Test
    String testfolder = "/Users/patrickorum/Desktop/testfolder";
    String testEncryptFile = "testfile.rtf";

    //Decrypt
    String decryptFolder = "/Users/patrickorum/Desktop/testfolder";




}
