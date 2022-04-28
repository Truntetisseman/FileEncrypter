package CBC;

import java.util.ArrayList;
import java.util.List;

public interface Global {

    // Keystore
    String keystoreDir = "FileEncrypter/CBC/testfolder/keystore";
    String storeFileName = keystoreDir + "/" + "keystore.bks";
    char[] storePW = "burger".toCharArray();

    // Login
    String password = "pizza";
    String username = "psec";

    // Logs
    List<Logs> logs = new ArrayList<>();

    // Test
    String testfolder = "FileEncrypter/CBC/testfolder";
    String testEncryptFile = "testfile.rtf";

    // Decrypt
    String decryptFolder = "FileEncrypter/CBC/testfolder";
}
