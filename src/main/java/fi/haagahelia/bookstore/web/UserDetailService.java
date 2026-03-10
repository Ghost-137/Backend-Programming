package fi.haagahelia.bookstore.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; 

import fi.haagahelia.bookstore.domain.User; 
import fi.haagahelia.bookstore.domain.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;
    
    public UserDetailService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        User currentUser = repository.findByUsername(username); 
        
        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        UserDetails user = new org.springframework.security.core.userdetails.User(
                username,
                currentUser.getPassword(),
                AuthorityUtils.createAuthorityList(currentUser.getRole())
        );
        return user;
    }
}