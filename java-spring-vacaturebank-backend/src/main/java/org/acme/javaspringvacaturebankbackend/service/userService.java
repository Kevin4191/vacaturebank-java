package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.userModel;
import org.acme.javaspringvacaturebankbackend.repository.userRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                user.setUserPassword(passwordEncoder(user.getUserPassword()));
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
                            value = passwordEncoder(value.toString());
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

    // Encrypting the password on entry
    private String passwordEncoder(String password) {
            PasswordEncoder passwordEncoder;
            passwordEncoder = new BCryptPasswordEncoder();

            String encodedPassword = passwordEncoder.encode(password);
            return encodedPassword;
    }
    
    // Validating password to succeed login
    public boolean validateUser(Map<String, Object> fields) {
        try{
        final Object[] loginUsername = {null};
        final Object[] loginPassword = {null};
        fields.forEach((key, value) -> { // Map through fields
            @SuppressWarnings("unused")
            Field field = ReflectionUtils.findField(userModel.class, key);
            if (key.toString() == "userEmail"){
                loginUsername[0] = value;
            }
            if (key.toString() == "userPassword"){
                loginPassword[0] = value;
            }
        });
        List<userModel> existingAccounts = userRepository.validationByEmail(loginUsername[0].toString());
    
        // check if an account exists with used email
        if (existingAccounts.isEmpty()) {
            return false;
        }
    
        userModel existingAccount = existingAccounts.get(0);
        String hashedPassword = existingAccount.getUserPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Boolean check if the entered password matches the one linked to the email
        return encoder.matches(loginPassword[0].toString(), hashedPassword);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
