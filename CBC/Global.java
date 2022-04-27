package CBC;

public interface Global {
    //Paths and other global variables
    String path = "/Users/danielnoren/Desktop"; //
    String fileName = "MedicalRecordNielsJ"; //
    String fileSuffix = "pdf"; // eg. pdf, jpeg, docx

    //Variables for decrypt
    String originalSHAFile = Global.path + "/" + Global.fileName + Global.fileSuffix; //"MedicalRecordNielsJ.pdf" ;
    String testFile = Global.path + "/" + Global.fileName + "." + "test" + "." + Global.fileSuffix + originalSHAFile;
    String plaintextFileName = Global.path + "/" + Global.fileName + "." + Global.fileSuffix + "." + "aes" + testFile;

    //Keystore
    String keystoreDir = "/Users/danielnoren/Desktop";
    String storeFileName = keystoreDir + "/" + "keystore.bks";
    char[] storePW = "burger".toCharArray();

    //Login
    String password = "pizza";
    String username = "psec";

}
