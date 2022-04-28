package CBC;

import javafx.scene.control.TextInputDialog;

import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Optional;

public class Keystore {

    /**
     * Creates a keystore if none is present
     * @return
     */
    public static KeyStore createKeyStore() {
        KeyStore store = null;

        try {
            store = KeyStore.getInstance("BKS", "BC");
            store.load(null, null);
        } catch (Exception e) {e.printStackTrace(); }
        return store;
    }

    /**
     * Generates a random key for the encryption, based on the inputted values by the user
     * @param store a keystore must be passed as parameter
     */
    public static void generateAndAddKey(KeyStore store) {
        try {
            // generation of symmetric key
            SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");
            byte[] keyBytes = new byte[16];
            secureRandom.nextBytes(keyBytes);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

            // adding key to keystore
            KeyStore.SecretKeyEntry entry = new KeyStore.SecretKeyEntry(key);
            //Getting Keystore password
            String password = openInputDialog("Encryption Password", "Please input the password you want to use to encrypt the file", "Enter password:");
            char[] secretKeyPW = password.toCharArray();
            KeyStore.ProtectionParameter protection = new KeyStore.PasswordProtection(secretKeyPW);
            store.setEntry("key", entry, protection);
        } catch (Exception e) {
            System.out.println("generate and add key");
        }
    }

    /**
     *
     * @param store
     */
    public static void store(KeyStore store) {
        try {
            FileOutputStream fOut = new FileOutputStream(Global.storeFileName);
            store.store(fOut, Global.storePW);
            fOut.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * Loads the keystore, so the password can be used
     * @return
     */
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

    /**
     * Retrieves the encryption key from the keystore
     * @return
     */
    public static SecretKeySpec getKey() {
        SecretKeySpec key = null;
        try {
            KeyStore ks = load();
            //System.out.print("OurKeystore: please type password: ");
            //Scanner scanner = new Scanner(System.in);

            String password = openInputDialog("Keystore Password", "Please input the password for your keystore", "Enter password:");
            char[] pw = password.toCharArray();
            key = (SecretKeySpec) ks.getKey("key", pw);
        } catch (Exception e) { e.printStackTrace(); }
        return key;
    }

    /**
     * Opens a dialog window with an input field, where the user can input a String and the string is then returned
     * @param title the title of the dialog window
     * @param header the header shown in the top of the window
     * @param context the text displayed next to the input field
     * @return returns the input value as a string
     */
    public static String openInputDialog(String title, String header, String context){

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(context);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println(result.get());
            return result.get();
        }
        return "";
    }
}
