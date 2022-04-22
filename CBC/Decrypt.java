package CBC;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyStore;
import java.security.Security;

public class Decrypt {


    /**
     *
     * @return
     */
    public static KeyStore createKeystore(){
        Security.addProvider(new BouncyCastleProvider());
        String dir = "/Users/danielnoren/Desktop";
        String mr = "MedicalRecordNielsJ";

        String plaintextFileName = dir + "/" + mr + "." + "pdf" + "." + "aes",
                testFile = dir + "/" + mr + "." + "test" + "." + "pdf", originalSHAFile = dir + "/" + "MedicalRecordNielsJ.pdf" ;
        //byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f");
        KeyStore ks = OurKeystore.load();
        OurKeystore.store(ks);

        return ks;
    }

    public  static Boolean checkForKeystore(){
        return false;
    }

}
