package CBC;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import java.security.*;


public class DecryptFileCBC {
    public static void main(String plaintextFileName) {
        Security.addProvider(new BouncyCastleProvider());
        String dir = "/Users/danielnoren/Desktop";
        String mr = "MedicalRecordNielsJ";
        String testFile = dir + "/" + mr + "." + "test" + "." + "pdf", originalSHAFile = dir + "/" + "MedicalRecordNielsJ.pdf" ;
        //byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f");
        KeyStore ks = medicalKS.load();
        medicalKS.store(ks);

        {
            try {
                String ivString = library.FileUtil.getIV(plaintextFileName);
                System.out.println(ivString);
                // Reading
                IvParameterSpec iv = new IvParameterSpec(Hex.decode(ivString));
                byte[] input = library.FileUtil.readAllBytes(plaintextFileName);
                System.out.println("Decrypter" + plaintextFileName);
                // Decrypting
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
                //SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
                SecretKeySpec key = medicalKS.getKey();
                cipher.init(Cipher.DECRYPT_MODE, key, iv);

                byte[] output = cipher.doFinal(input);
                byte[] storedHashValue = library.FileUtil.readAllBytes(originalSHAFile + ".sha256");
                MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC"); digest.update(output);
                byte[] computedHashValue = digest.digest();

                if (MessageDigest.isEqual(computedHashValue, storedHashValue)) {
                    System.out.println("They are equal");
                } else {
                    System.out.println("File has been tampered with");
                    System.exit(1);
                    }
                // Writing
                library.FileUtil.write("", testFile, output, "");
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
