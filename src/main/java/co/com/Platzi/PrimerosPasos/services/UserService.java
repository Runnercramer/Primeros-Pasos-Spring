package co.com.Platzi.PrimerosPasos.services;

import co.com.Platzi.PrimerosPasos.entity.User;
import co.com.Platzi.PrimerosPasos.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Log LOGGER = LogFactory.getLog(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveTransactional(List<User> users) {
        users.stream().peek(user -> LOGGER.info("Usuario insertado: " + user)).forEach(userRepository::save);
    }
}
