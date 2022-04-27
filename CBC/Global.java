package CBC;

import java.util.ArrayList;
import java.util.List;

public interface Global {
    //Paths and other global variables
  /*  String path = "/Users/danielnoren/Desktop"; ///Users/noel/Desktop
    String fileName = "MedicalRecordNielsJ"; //
    String fileSuffix = "pdf"; // eg. pdf, jpeg, docx

    //Variables for decrypt
    String originalSHAFile = Global.path + "/" + Global.fileName + "." + Global.fileSuffix; //"MedicalRecordNielsJ.pdf" ;
    String testFile = Global.path + "/" + Global.fileName + "." + "test" + "." + Global.fileSuffix; /* originalSHAFile;
    String plaintextFileName = Global.path + "/" + Global.fileName + "." + Global.fileSuffix + "." + "aes";// + testFile;
    */

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
