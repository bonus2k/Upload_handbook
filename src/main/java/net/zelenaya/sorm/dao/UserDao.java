package net.zelenaya.sorm.dao;

import net.zelenaya.sorm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configurable
@Service
public class UserDao implements UserDetailsService {

    final
    UserRepo userRepo;

    public UserDao(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s).get();
    }
}
