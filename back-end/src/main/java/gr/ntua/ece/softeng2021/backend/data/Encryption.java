/*package gr.ntua.ece.softeng2021.backend.data;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Encryption {
    private String myString;
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public Encryption(String s){
        this.myString = s;
        System.out.println(s);
    }

    public String CreateEncryption() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(this.myString.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        char[] hexChars = new char[salt.length * 2];
        char[] hexChars2 = new char[hash.length * 2];
        for (int j = 0; j < salt.length; j++) {
            int v = salt[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        for (int j = 0; j < hash.length; j++) {
            int v = hash[j] & 0xFF;
            hexChars2[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars2[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        String encoded = new String(hexChars) + new String(hexChars2);
        //System.out.println(encoded);
        return encoded;
    }
}

*/