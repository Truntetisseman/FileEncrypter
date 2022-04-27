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

public class Decrypt {


    public static void decryptFile(String plaintextFileName, String shaFile) {
        Security.addProvider(new BouncyCastleProvider());
        KeyStore ks = Keystore.load();
        Keystore.store(ks);
        System.out.println("plaintext" + plaintextFileName);
        String testFile = FileUtil.getTestname(plaintextFileName);

        try {
            String ivString = FileUtil.getIV(plaintextFileName);
            // Reading
            IvParameterSpec iv = new IvParameterSpec(Hex.decode(ivString));
            byte[] input = FileUtil.readAllBytes(plaintextFileName);

            // Decrypting
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

            //SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            SecretKeySpec key = Keystore.getKey();

            System.out.println("test" + key);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] output = cipher.doFinal(input);
            byte[] storedHashValue = FileUtil.readAllBytes(shaFile);
            MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC"); digest.update(output);
            byte[] computedHashValue = digest.digest();

            if (MessageDigest.isEqual(computedHashValue, storedHashValue)) {
                System.out.println("They are equal");
            } else {
                System.out.println("File has been tampered with");
                System.exit(1);
            }
            // Writing
            FileUtil.write("",testFile, output, "");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static Boolean checkForKeystore(){
        return false;
    }
}
