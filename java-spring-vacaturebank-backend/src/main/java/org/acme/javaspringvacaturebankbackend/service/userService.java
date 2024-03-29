package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.userModel;
import org.acme.javaspringvacaturebankbackend.repository.userRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class userService {

    @Autowired
    userRepository userRepository;

    // Get method
    public Iterable<userModel> getUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepository.findAll();
    }

    // Post method
    public userModel postUser(userModel user) {
        try {
            List<userModel> existingUser = userRepository.findUserByEmail(user.getUserEmail(), user.getUserId());
            if (!existingUser.isEmpty()) {
                throw new IllegalArgumentException("Account is already made with entered email");
            } else {
                user.setUserPassword(passwordHasher(user.getUserPassword()));
                return userRepository.save(user);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    // Patch method
    public userModel patchUser(int id, Map<String, Object> fields) {
        try {
            Optional<userModel> existingUser = userRepository.findById(id);
            if (existingUser.isPresent()) {
                fields.forEach((key, value) -> { // Map through fields
                    Field field = ReflectionUtils.findField(userModel.class, key);
                    if ((key.toString() == "userName") && (key.toString() == "userRole")
                            && (!StringUtils.isAlphaSpace(value.toString()))) {
                        throw new IllegalArgumentException("Field cannot contain numbers or special characters");
                    } else {
                        if (key.toString() == "userPassword") {
                            value = passwordHasher(value.toString());
                            field.setAccessible(true);
                            ReflectionUtils.setField(field, existingUser.get(), value);
                        } else {
                            field.setAccessible(true);
                            ReflectionUtils.setField(field, existingUser.get(), value);
                        }

                    }
                });
                return userRepository.save(existingUser.get());
            } else {
                throw new IllegalArgumentException("user not found");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    // Delete Method
    public String deleteUser(int id) {
        try {
            Optional<userModel> existingUser = userRepository.findById(id);
            if (existingUser.isPresent()) {
                userRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("user not found");
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return "user deleted succesfully";

    }

    private String passwordHasher(String password) {
        try {
            // MessageDigest instance for MD5
            MessageDigest m = MessageDigest.getInstance("MD5");

            // Add plain-text password bytes to digest using MD5 update() method.
            m.update(password.getBytes());

            // Convert the hash value into bytes
            byte[] bytes = m.digest();

            // The bytes array has bytes in decimal form. Converting it into hexadecimal
            // format
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Complete hashed password in hexadecimal format
            String encryptedpassword = s.toString();
            
            return encryptedpassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}
