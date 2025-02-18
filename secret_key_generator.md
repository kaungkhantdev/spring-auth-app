# SecretKeyGenerator

```java
import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {
        // Generate a random 256-bit (32-byte) key
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretKey = new byte[32]; // 256 bits = 32 bytes
        secureRandom.nextBytes(secretKey);

        // Convert to base64
        String base64EncodedKey = Base64.getEncoder().encodeToString(secretKey);
        System.out.println("Generated Secret Key: " + base64EncodedKey);
    }
}

```