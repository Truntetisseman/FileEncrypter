package CBC;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.w3c.dom.ls.LSOutput;

import java.security.*;



public class EncryptFileCBC {
    public static void main(String plaintextFileName) throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");
        KeyStore ks = medicalKS.load();
        medicalKS.generateAndAddKey(ks);
        medicalKS.store(ks);
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        {
            try {
                // reading
                byte [] input = library.FileUtil.readAllBytes(plaintextFileName);
                // encrypting
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
               // SecretKeySpec secretKey = new SecretKeySpec(medicalKS.getKey(), "AES");
                SecretKeySpec secretKey = medicalKS.getKey();
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
                byte[] output = cipher.doFinal(input);
                System.out.println(output.length);

                MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC");
                byte[] hashValue = digest.digest(input);
                System.out.println("Hashvalue: " + Hex.toHexString(hashValue));
                // writing hash value to file
                String hashFileName = plaintextFileName + "." + "sha256";
                library.FileUtil.write(hashFileName, hashValue);
                // writing
                library.FileUtil.write("AES/ECB/PKCS5Padding", plaintextFileName, output, Hex.toHexString(iv));
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}