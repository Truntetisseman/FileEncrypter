package CBC;
import java.security.KeyStore;

public class medicalKS {
    public static KeyStore createKeyStore() {
        KeyStore store = null;
        try {
            store = KeyStore.getInstance("BKS", "BC");
            store.load(null, null);
        } catch (Exception e) { }
        return store;
    }
}
