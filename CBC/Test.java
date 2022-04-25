package CBC;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        testHashing(Global.originalSHAFile, Global.testFile, Global.plaintextFileName);
    }

    private static void testHashing(String originalSHAFile, String testFile, String plaintextFileName) {
        System.out.println(originalSHAFile);
        System.out.println(testFile);
        System.out.println(plaintextFileName);
    }

    //Encrypt file
        //Test if filename contains aes.
        //Open file and test if file != match regex pattern ([a-zA-Z0-9]*)
    //Decrypt file
        //Test if file now matches regex pattern.
}