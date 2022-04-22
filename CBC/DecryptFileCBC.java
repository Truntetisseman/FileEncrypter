package CBC;

import library.FileUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.Security;


public class DecryptFileCBC {
    public static void main(String args) {
        Security.addProvider(new BouncyCastleProvider());
        String dir = "/Users/danielnoren/Desktop";
        String mr = "MedicalRecordNielsJ";

        String plaintextFileName = dir + "/" + mr + "." + "pdf" + "." + "aes",
                testFile = dir + "/" + mr + "." + "test" + "." + "pdf", originalSHAFile = dir + "/" + "MedicalRecordNielsJ.pdf" ;
        //byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f");
        KeyStore ks = OurKeystore.load();
        OurKeystore.store(ks);

        {
            try {
                String ivString = FileUtil.getIV(plaintextFileName);
                // Reading
                IvParameterSpec iv = new IvParameterSpec(Hex.decode(ivString));
                byte[] input = FileUtil.readAllBytes(plaintextFileName + "." + ivString);
                System.out.println(plaintextFileName + "." + ivString);
                // Decrypting
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
                //SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
                SecretKeySpec key = OurKeystore.getKey();
                System.out.println("test" + key);
                cipher.init(Cipher.DECRYPT_MODE, key, iv);

                byte[] output = cipher.doFinal(input);
                byte[] storedHashValue = FileUtil.readAllBytes(originalSHAFile + ".sha256");
                MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC"); digest.update(output);
                byte[] computedHashValue = digest.digest();

                if (MessageDigest.isEqual(computedHashValue, storedHashValue)) {
                    System.out.println("They are equal");
                } else {
                    System.out.println("File has been tampered with");
                    System.exit(1);
                    }
                // Writing
                FileUtil.write("", testFile, output, "");
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
