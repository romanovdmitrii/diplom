/*
package ru.dmitriiromanov.diplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dmitriiromanov.diplom.repository.CoachRepository;

@Service
public class CoachService implements UserDetailsService {
    @Autowired
    private CoachRepository coachRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return (UserDetails) coachRepository.findByName(name);
    }
}
*/
