package project.personal.personalstoremanagementproject.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.personal.personalstoremanagementproject.repositories.BaseRepository;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 *  StringUtil class
 */
@Component
public class StringUtil {

    @Value("${aes.secretKey}")
    private String secretKeyString;

    private byte[] secretKey;

    @PostConstruct
    public void init() {
        secretKey = Base64.getDecoder().decode(secretKeyString);
    }

    /**
     * Generates unique id for entities
     * @param entity
     * @param repository
     * @return
     * @param <T>
     */
    public static <T> String createId(T entity, BaseRepository<T, ?> repository) {
        // Get current date
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        // Get count of entities
        long count = repository.count();
        // Format count to 3 digits
        String formattedCount = String.format("%03d", count + 1);
        // Get first letter of entity class name
        String prefix = entity.getClass().getSimpleName().substring(0, 1).toUpperCase();
        // Return generated id
        return prefix + date + formattedCount;
    }

    /**
     * Encrypts data using AES algorithm
     * @param userId
     * @param userName
     * @return
     * @throws Exception
     */
    public String encrypt(String userId, String userName) throws Exception {
        // Combine userId and userName into one string
        String data = userId + userName;
        // Create SecretKeySpec object from secret key
        var secretKeySpec = new SecretKeySpec(secretKey, "AES");
        // Create cipher object
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // Encrypt data
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        // Return encrypted data
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * Decrypts data using AES algorithm
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public String decrypt(String encryptedData) throws Exception {
        // Decode encrypted data
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        // Create SecretKeySpec object from secret key
        var secretKeySpec = new SecretKeySpec(secretKey, "AES");
        // Create cipher object
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // Decrypt data
        byte[] originalData = cipher.doFinal(decodedData);
        // Return decrypted data
        return new String(originalData);
    }
}
