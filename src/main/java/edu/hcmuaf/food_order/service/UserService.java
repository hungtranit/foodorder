package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean login(String username, String password) {
        if (userRepository.getOne(username).getUsername().equalsIgnoreCase(username) &&
                userRepository.getOne(username).getPassword().equals(UserUtil.encryptPassword(password))) {
            return true;
        }
        return false;
    }

}
