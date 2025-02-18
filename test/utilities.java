package com.crud.utils;

import com.crud.model.Customer;
import com.crud.constants.MessageConstants;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ValidationUtils {
    public List<String> validate(Customer customer) {
        List<String> errors = new ArrayList<>();
        
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            errors.add(MessageConstants.ERROR_NAME_REQUIRED);
        }
        
        if (customer.getSex() != 'M' && customer.getSex() != 'F') {
            errors.add(MessageConstants.ERROR_INVALID_SEX);
        }
        
        if (customer.getBirthday() != null) {
            if (customer.getBirthday().isAfter(LocalDate.now())) {
                errors.add(MessageConstants.ERROR_INVALID_BIRTHDAY);
            }
        }
        
        if (customer.getEmail() != null && !customer.getEmail().trim().isEmpty()) {
            if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                errors.add(MessageConstants.ERROR_INVALID_EMAIL);
            }
        }
        
        return errors;
    }
}

package com.crud.utils;

import java.util.Properties;
import java.util.ResourceBundle;
import java.text.MessageFormat;

public class MessageUtils {
    private final ResourceBundle messageBundle;
    
    public MessageUtils() {
        messageBundle = ResourceBundle.getBundle("messages");
    }
    
    public String getMessage(String key) {
        try {
            return messageBundle.getString(key);
        } catch (Exception e) {
            return key;
        }
    }
    
    public String getMessage(String key, Object... params) {
        try {
            String pattern = messageBundle.getString(key);
            return MessageFormat.format(pattern, params);
        } catch (Exception e) {
            return key;
        }
    }
}

package com.crud.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    
    public String hashPassword(String password) throws Exception {
        byte[] salt = generateSalt();
        byte[] hash = generateHash(password, salt);
        
        return Base64.getEncoder().encodeToString(salt) + "$" +
               Base64.getEncoder().encodeToString(hash);
    }
    
    public boolean verifyPassword(String password, String storedHash) throws Exception {
        String[] parts = storedHash.split("\\$");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);
        
        byte[] testHash = generateHash(password, salt);
        return slowEquals(hash, testHash);
    }
    
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
    private byte[] generateHash(String password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmaxSHA256");
        return factory.generateSecret(spec).getEncoded();
    }
    
    private boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
