package com.pfa.services;

import com.pfa.entities.ClientEntity;
import com.pfa.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void before(){
        this.clientRepositoryMock = Mockito.mock(ClientRepository.class);
        System.out.println(">");
    }

    @Test
    public void aVoid(){
        // given
        String login = "AE180937";
        Optional<ClientEntity> optClientEntity = Optional.of(ClientEntity.builder().build());



        // when
        Mockito.when(this.clientRepositoryMock.findByLogin(login))
                .thenReturn(optClientEntity);


        // then

        boolean availability = this.userService.checkLoginAvailability(login);
        System.out.println(">");
    }

  
}