package CBC;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Scanner;

public class medicalKS {
        public static void main(String[] args) {
            Security.addProvider(new BouncyCastleProvider());
            KeyStore ks = createKeyStore();
            generateAndAddKey(ks);
            store(ks);
        }
    static String dir = "/Users/danielnoren/Desktop";
    static String storeFileName = dir + "/" + "keystore.bks";
    static char[] storePW = "burger".toCharArray(), secretKeyPW = "pizza".toCharArray();

    public static KeyStore createKeyStore() {
        KeyStore store = null;
        try {
            store = KeyStore.getInstance("BKS", "BC");
            store.load(null, null);
        } catch (Exception e) { }
        return store;
    }

    public static KeyStore load() {
        KeyStore ks = null;
        try {
            // step (1)
            ks = KeyStore.getInstance("BKS", "BC");
            // step (2)
            FileInputStream fis = new FileInputStream(storeFileName);
            ks.load(fis, storePW);
            fis.close();
        } catch (Exception e) { e.printStackTrace(); }
        return ks;
    }

    public static SecretKeySpec getKey() {
        SecretKeySpec key = null;
        try {
            KeyStore ks = load();
            System.out.print("MedicalKS: please type password: ");
            Scanner scanner = new Scanner(System.in);
            char[] pw = scanner.nextLine().toCharArray();
            key = (SecretKeySpec) ks.getKey("key", pw);
        } catch (Exception e) { e.printStackTrace(); }
        return key;
    }

    public static void store(KeyStore store) {
        try {
            FileOutputStream fOut = new FileOutputStream(storeFileName);
            store.store(fOut, storePW);
            fOut.close();
        } catch (Exception e) { e.printStackTrace(); }
    }


    public static void generateAndAddKey(KeyStore store) {
        try {
            // generation of symmetric key
            SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");
            byte[] keyBytes = new byte[16];
            secureRandom.nextBytes(keyBytes);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            System.out.println("Test genereate" + key);
            // adding key to keystore
            KeyStore.SecretKeyEntry entry = new KeyStore.SecretKeyEntry(key);
            KeyStore.ProtectionParameter protection = new KeyStore.PasswordProtection(secretKeyPW);
            store.setEntry("key", entry, protection);
        } catch (Exception e) {
            System.out.println("generate and add key");
        }
    }
}
