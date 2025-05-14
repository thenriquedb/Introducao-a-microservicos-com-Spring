package com.thenriquedb.ms_users.services;

import com.thenriquedb.ms_users.domain.User;
import com.thenriquedb.ms_users.dtos.UserRecordDto;
import com.thenriquedb.ms_users.producers.UserProducer;
import com.thenriquedb.ms_users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProducer userProducer;

    @Transactional
    public User createUser(UserRecordDto userRecordDto) {
        User user = new User();
        BeanUtils.copyProperties(userRecordDto, user);

        User createdUser = this.userRepository.save(user);

        userProducer.publishMessageEmail(createdUser);

        return this.userRepository.save(createdUser);
    }
}
