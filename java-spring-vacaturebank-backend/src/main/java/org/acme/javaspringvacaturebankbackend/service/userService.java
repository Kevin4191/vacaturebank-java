package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.acme.javaspringvacaturebankbackend.model.userDTO;
import org.acme.javaspringvacaturebankbackend.model.userModel;
import org.acme.javaspringvacaturebankbackend.repository.userRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
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

    // Map entity to DTO
    private userDTO mapToDTO(userModel user) {
        userDTO dto = new userDTO();
        dto.setUserEmail(user.getUserEmail());
        dto.setUserName(user.getUserName());
        dto.setUserRole(userRepository.findUserRoleByEmail(user.getUserEmail()));
        dto.setEmployersEmployerId(user.getEmployersEmployerId());
        // Map other attributes
        return dto;
    }

    // Modify the validateUser method to return a DTO
    public userDTO validateUser(Map<String, Object> fields) {
        try {
            final Object[] loginUsername = { null };
            final Object[] loginPassword = { null };
            fields.forEach((key, value) -> {
                if ("userEmail".equals(key)) {
                    loginUsername[0] = value;
                }
                if ("userPassword".equals(key)) {
                    loginPassword[0] = value;
                }
            });

            List<userModel> existingAccounts = userRepository.validationByEmail(loginUsername[0].toString());

            if (existingAccounts.isEmpty()) {
                return null;
            }

            userModel existingAccount = existingAccounts.get(0);
            String hashedPassword = existingAccount.getUserPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(loginPassword[0].toString(), hashedPassword)) {
                return mapToDTO(existingAccount);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
