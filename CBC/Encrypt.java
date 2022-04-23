package CBC;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class Encrypt {

    public static void EncryptFile(String file) throws NoSuchAlgorithmException, NoSuchProviderException {

        SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        {
            try {
                // reading
                byte [] input = library.FileUtil.readAllBytes(Global.plaintextFileName);

                // encrypting
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

                // SecretKeySpec secretKey = new SecretKeySpec(medicalKS.getKey(), "AES");
                SecretKeySpec secretKey = Keystore.getKey();
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
                byte[] output = cipher.doFinal(input);
                //System.out.println(output.length);

                MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC");
                byte[] hashValue = digest.digest(input);
                //System.out.println("Hashvalue: " + Hex.toHexString(hashValue));

                // writing hash value to file
                String hashFileName = Global.plaintextFileName + "." + "SHA-256";//sha256
                library.FileUtil.write(hashFileName, hashValue);

                // writing
                library.FileUtil.write("AES/ECB/PKCS5Padding",Global.plaintextFileName, output, Hex.toHexString(iv));
            }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
