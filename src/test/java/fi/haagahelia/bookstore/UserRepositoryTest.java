package fi.haagahelia.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userNameShouldReturnUser() {
        userRepository.save(new User("Mehedi", "hash123", "USER", "mehedi@gmail.com"));
        User user = userRepository.findByUsername("Mehedi");
        assertThat(user.getUsername()).isEqualTo("Mehedi");
        assertThat(user.getPassword()).isEqualTo("hash123");
        assertThat(user.getRole()).isEqualTo("USER");
        assertThat(user.getEmail()).isEqualTo("mehedi@gmail.com");
    }

    @Test
    public void createNewUser() {
        User user = new User("Refat", "refat123", "USER", "refat@gmail.com");
        userRepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteUser() {
        User user = new User("DeleteMe", "delete123", "USER", "deleteme@gmail.com");
        userRepository.save(user);
        userRepository.delete(user);
        assertThat(userRepository.findByUsername("DeleteMe")).isNull();
    }
}