package CBC;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.security.*;

public class Encrypt {

    /**
     * Encrypts the selected file
     * @param file
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static void encryptFile(File file) throws NoSuchAlgorithmException, NoSuchProviderException {
        String fileAsString = file.toString();
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
                byte [] input = library.FileUtil.readAllBytes(fileAsString);

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
                library.FileUtil.write("AES/ECB/PKCS5Padding",fileAsString, output, Hex.toHexString(iv));
                Utility.successDialog(file.getName(), file.getAbsolutePath(), "Encryption Success", "The file was succesfully encrypted", file.getName() + " is now encrypted!");
            }
            catch (Exception e) { e.printStackTrace(); Utility.errorDialog(file.getName(), file.getAbsolutePath(), "Encryption Error", "An error occurred", "The encryption failed please try again!"); }
        }
    }
}


