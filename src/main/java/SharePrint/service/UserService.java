package SharePrint.service;

import SharePrint.repository.UserRepository;
import SharePrint.models.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        userRepository.persist(user);
        return user;
    }

    public User updateUser(User user) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(user.getId()));
        if (optionalUser.isPresent()) {
            User entity = optionalUser.get();
            entity.setName(user.getName());
            userRepository.persist(entity);
            return entity;
        } else {
            return null;
        }
    }



//public User updateUser(User user) {
//    User entity = userRepository.findById(user.getId()).orElse(null);
//    if (entity != null) {
//        entity.setName(user.getName());
//        userRepository.persist(entity);
//    }
//    return entity;
//}


    public void deleteUser(Long id) {
        User entity = userRepository.findById(id);
        if (entity != null) {
            userRepository.delete(entity);
        }
    }
}
