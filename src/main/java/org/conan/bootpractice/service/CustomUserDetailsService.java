package org.conan.bootpractice.service;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.conan.bootpractice.domain.entity.Subscriber;
import org.conan.bootpractice.domain.SubscriberDTO;
import org.conan.bootpractice.repository.SubscriberRepository;


@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SubscriberRepository subscriberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Subscriber subscriber = subscriberRepository.getWithRoles(username);
        if (subscriber == null) {
            throw new UsernameNotFoundException("Not Found");
        }
        SubscriberDTO subscriberDTO = new SubscriberDTO(
                subscriber.getEmail(),
                subscriber.getPwd(),
                subscriber.getNickname(),
                subscriber.isSocial(),
                subscriber.getSubscriberRoleList().stream()
                        .map(memberRole -> memberRole.name())
                        .collect(Collectors.toList()));
        log.info(subscriberDTO);
        return subscriberDTO;
    }
}
