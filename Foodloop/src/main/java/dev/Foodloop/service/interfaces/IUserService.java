package dev.Foodloop.service.interfaces;

import dev.Foodloop.persistance.entities.User;
import java.util.List;

public interface IUserService {

    public User authenticateUser(String username, String rawPassword);
}
