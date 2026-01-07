package com.firsttry.firsttryout.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

public class OwnerTokenUtil {

    private static final String HMAC_ALGO = "HmacSHA256";
    private static final String SECRET = SECRET_KEY_STRING;

    public static String generateToken(UUID ownerId) {
        String payload = ownerId + ":" + Instant.now().getEpochSecond();
        String signature = sign(payload);
        return base64(payload) + "." + signature;
    }

    public static UUID verifyAndExtract(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 2) {
            throw new RuntimeException("Invalid token format");
        }

        String payload = new String(Base64.getUrlDecoder().decode(parts[0]));
        String expectedSignature = sign(payload);

        if (!expectedSignature.equals(parts[1])) {
            throw new RuntimeException("Invalid token signature");
        }

        String ownerId = payload.split(":")[0];
        return UUID.fromString(ownerId);
    }

    private static String sign(String payload) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGO);
            mac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), HMAC_ALGO));
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Token signing failed", e);
        }
    }

    private static String base64(String payload) {
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(payload.getBytes(StandardCharsets.UTF_8));
    }
}
