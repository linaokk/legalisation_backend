package com.pfa.services;


import com.pfa.clients.FaceRecognitionClient;
import com.pfa.clients.dtos.FaceRecognitionCheckRequest;
import com.pfa.clients.dtos.FaceRecognitionResult;
import com.pfa.config.JwtTokenProvider;
import com.pfa.dtos.LoginDTO;
import com.pfa.entities.users.UserEntity;
import com.pfa.exceptions.FeatureErrorEnum;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final FaceRecognitionClient recognitionClient;
    private final UserRepository userRepository;

    public Map<Object, Object> loginAndGenerateToken(LoginDTO data) {
        String login = data.getLogin();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                login,
                data.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authToken);
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String userPicture = userEntity.getUserPicture();
        String loggedInUserPicture = data.getUserPicture();

        if(userEntity.isNeedFaceCheck()){
            FaceRecognitionCheckRequest faceCheckRequest = FaceRecognitionCheckRequest.from(userPicture, loggedInUserPicture);
            FaceRecognitionResult recognitionResult = recognitionClient.checkSameFace(faceCheckRequest);
            if (!recognitionResult.isResult()) {
                FeatureErrorEnum.FT0003.throwException();
            }
        }

        String token = jwtTokenProvider.createToken(authentication);
        Map<Object, Object> model = new HashMap<>();
        model.put("username", login);
        model.put("token", token);
        return model;
    }

    public UserEntity findClientByIdentityCode(String identityCode) {
        return this.userRepository.findByIdentityCode(identityCode)
                .orElseThrow(() -> FeatureErrorEnum.FT0001.newException());
    }

    public void updateClientAccount(String identityCode, String phoneNumber, String password, String email) {
        UserEntity clientEntity = this.findClientByIdentityCode(identityCode);

        String encodedPassword = passwordEncoder.encode(password);
        clientEntity.setPhoneNumber(phoneNumber);
        clientEntity.setEmail(email);
        clientEntity.setPassword(encodedPassword);
        this.userRepository.save(clientEntity);
    }
}
