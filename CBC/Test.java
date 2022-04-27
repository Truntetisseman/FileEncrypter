package CBC;

import library.FileUtil;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        File file = new File(Global.testfolder + "/" + Global.testEncryptFile);
        String encryptedFileName = Global.testfolder + "/" + Global.testEncryptFile + ".aes";
        Encrypt.encryptFile(file);
        testForSuffix(encryptedFileName);
        String test = FileUtil.getTestname(encryptedFileName);

    }

    public static void testEncryption() throws NoSuchAlgorithmException, NoSuchProviderException {
        File file = new File(Global.testfolder + "/" + Global.testEncryptFile);
        String encryptedFileName = Global.testfolder + "/" + Global.testEncryptFile + ".aes";
        Encrypt.encryptFile(file);
        testForSuffix(encryptedFileName);
        String test = FileUtil.getTestname(encryptedFileName);
    }

    public static void testForSuffix(String encryptedFileName) {

        String[] fileParts = encryptedFileName.split("/");
        System.out.println(encryptedFileName);
            if (encryptedFileName.contains(".aes")) {
                Utility.successDialog(encryptedFileName, encryptedFileName, "Test Encryption Success", "The test has succeeded", "The test was conducted without any errors, your system is up to date");
            } else {
                System.out.println("File not found");
                Utility.errorDialog(encryptedFileName, encryptedFileName, "Test Encryption Error", "The test failed", "Your system is not working properly, check your passwords and paths!");
            }
    }



}
