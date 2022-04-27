package library;

import CBC.Global;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static byte[] readAllBytes(String plaintextFileName) {
        byte[] bytesRead = {};
        try {
            bytesRead = Files.readAllBytes(Paths.get(plaintextFileName));
            System.out.println(bytesRead.getClass().getName());
        } catch (Exception e) {}
        return bytesRead; // returns {} if file does not exist
    }

    public static void write(String transformation,
                             String plaintextFileName, byte[] output, String iv) {
        String outFile = "";
        String[] parts = transformation.split("/");
        System.out.println("parts "+ parts);
        if (parts.length == 3 && parts[0].equals("AES")) {
            outFile = plaintextFileName  + ".aes" + "." + iv;
        } else { outFile = plaintextFileName; }
        try {

            Files.write(Paths.get(outFile), output);
        } catch (Exception e) { e.printStackTrace(); }
    }
    public static void write(String hashFileName, byte[] hashValue){
        try {
            Files.write(Paths.get(hashFileName), hashValue);
        } catch (Exception e) { e.printStackTrace(); }
    }


    public static String getIV(String encryptedFileName) {
        String[] fileNames ;
        String[] fileParts = encryptedFileName.split("/");
        File folder = new File(Global.decryptFolder);
        fileNames = folder.list();
        String IV = null;
        for (String fileName : fileNames) {
            if (fileName.startsWith(fileParts[5]) && encryptedFileName.contains(".aes")) {
                String[] parts = fileName.split("[.]");
                if(parts[3]!= null){
                IV = parts[3];
            }
        }
     }
        return IV;
    }
    public static String getTestname(String encryptedFileName){
        String[] fileParts = encryptedFileName.split("\\.");
        String testFileName = fileParts[0] + "." + "test" + "." + fileParts[fileParts.length-3];
        System.out.println(testFileName);
        return testFileName;
    }
}