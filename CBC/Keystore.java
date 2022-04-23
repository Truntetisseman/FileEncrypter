package CBC;

import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Scanner;

public class Keystore {

    /*
    Security.addProvider(new BouncyCastleProvider());
    KeyStore ks = createKeyStore();
    generateAndAddKey(ks);
    store(ks);
    */

    /*
    static String dir = "/Users/danielnoren/Desktop";
    static String storeFileName = dir + "/" + "keystore.bks";
    static char[] storePW = "burger".toCharArray();
    */

    public static KeyStore createKeyStore() {
        KeyStore store = null;

        try {
            store = KeyStore.getInstance("BKS", "BC");
            store.load(null, null);
        } catch (Exception e) {e.printStackTrace(); }
        return store;
    }

    public static void generateAndAddKey(KeyStore store) {
        try {
            // generation of symmetric key
            SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");
            byte[] keyBytes = new byte[16];
            secureRandom.nextBytes(keyBytes);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

            // adding key to keystore
            KeyStore.SecretKeyEntry entry = new KeyStore.SecretKeyEntry(key);
            System.out.print("OurKeystore: please choose a password: ");
            Scanner scanner = new Scanner(System.in);
            char[] secretKeyPW = scanner.nextLine().toCharArray();
            KeyStore.ProtectionParameter protection = new KeyStore.PasswordProtection(secretKeyPW);
            store.setEntry("key", entry, protection);
        } catch (Exception e) {
            System.out.println("generate and add key");
        }
    }

    public static void store(KeyStore store) {
        try {
            FileOutputStream fOut = new FileOutputStream(Global.storeFileName);
            store.store(fOut, Global.storePW);
            fOut.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static KeyStore load() {
        KeyStore ks = null;

        try {
            // step (1)
            ks = KeyStore.getInstance("BKS", "BC");
            // step (2)
            FileInputStream fis = new FileInputStream(Global.storeFileName);
            ks.load(fis, Global.storePW);
            fis.close();
        } catch (Exception e) { e.printStackTrace(); }

        return ks;
    }

    public static SecretKeySpec getKey() {
        SecretKeySpec key = null;
        try {
            KeyStore ks = load();
            System.out.print("OurKeystore: please type password: ");
            Scanner scanner = new Scanner(System.in);
            char[] pw = scanner.nextLine().toCharArray();
            key = (SecretKeySpec) ks.getKey("key", pw);
        } catch (Exception e) { e.printStackTrace(); }
        return key;
    }
}
