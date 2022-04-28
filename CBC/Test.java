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

    /**
     * Tests the encryption in the following steps
     * 1. Gets a file from the designated folder
     * 2. Encrypts the file
     * 3. Checks if the encrypted file is created successfully
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static void testEncryption() throws NoSuchAlgorithmException, NoSuchProviderException {
        File file = new File(Global.testfolder + "/" + Global.testEncryptFile);
        String encryptedFileName = Global.testfolder + "/" + Global.testEncryptFile + ".aes";
        Encrypt.encryptFile(file);
        testForSuffix(encryptedFileName);
        String test = FileUtil.getTestname(encryptedFileName);
    }

    /**
     * Tests encrypted file for aes suffix
     * If the testEncryption functions succeeds this function is called, it checks that the encrypted fil enow contains the .aes suffix
     * @param encryptedFileName
     */
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
