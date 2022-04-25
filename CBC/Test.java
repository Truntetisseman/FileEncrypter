package CBC;

import library.FileUtil;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        String file = "/Users/danielnoren/Desktop/test/MedicalRecordNielsJ.pdf";
        String encryptedFileName = "/Users/danielnoren/Desktop/test/MedicalRecordNielsJ.pdf.aes.db6099940ea07833432a9247218f9392";
        //Encrypt.encryptFile(file);
        //testForSuffix(encryptedFileName);
        String test = FileUtil.getTestname(encryptedFileName);

    }
    public static void testForSuffix(String encryptedFileName) {
        String[] fileNames ;
        String[] fileParts = encryptedFileName.split("/");
        File folder = new File("/Users/danielnoren/Desktop/test");
        fileNames = folder.list();
        for (String fileName : fileNames) {
            if (fileName.startsWith(fileParts[5]) && encryptedFileName.contains(".aes")) {
                System.out.println("test");
            }
        }
        System.out.println("File not found");
    }
    //Encrypt file
        //Test if filename contains aes.
        //Open file and test if file != match regex pattern ([a-zA-Z0-9]*)
    //Decrypt file
        //Test if file now matches regex pattern.
}
