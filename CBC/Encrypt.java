package CBC;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class Encrypt {

    public static void encryptFile(String file) throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");
        KeyStore ks = Keystore.load();
        Keystore.generateAndAddKey(ks);
        Keystore.store(ks);
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        {
            try {
                // reading
                byte [] input = library.FileUtil.readAllBytes(file);

                // encrypting
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

                // SecretKeySpec secretKey = new SecretKeySpec(medicalKS.getKey(), "AES");
                SecretKeySpec secretKey = Keystore.getKey();
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
                byte[] output = cipher.doFinal(input);

                MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC");
                byte[] hashValue = digest.digest(input);
                System.out.println("Hashvalue: " + Hex.toHexString(hashValue));

                // writing hash value to file
                System.out.println(file);
                String hashFileName = file + "." + "sha256";//sha256
                library.FileUtil.write(hashFileName, hashValue);

                // writing
                library.FileUtil.write("AES/ECB/PKCS5Padding",file, output, Hex.toHexString(iv));
            }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
