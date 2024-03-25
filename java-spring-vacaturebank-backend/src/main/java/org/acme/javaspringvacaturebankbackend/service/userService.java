package org.acme.javaspringvacaturebankbackend.service;

import java.lang.reflect.Field;
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
        return userRepository.save(user);
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
                        throw new IllegalArgumentException(
                                "Field cannot contain numbers or special characters" + key.toString()
                                        + (StringUtils.isAlphaSpace(value.toString())));
                    } else {
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, existingUser.get(), value);
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

}
