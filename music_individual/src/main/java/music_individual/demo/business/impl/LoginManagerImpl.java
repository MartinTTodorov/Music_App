package music_individual.demo.business.impl;


import lombok.AllArgsConstructor;
import music_individual.demo.business.IAccessTokenManager;
import music_individual.demo.business.ILoginManager;
import music_individual.demo.business.exception.InvalidCredentialsException;
import music_individual.demo.domain.AccessToken;
import music_individual.demo.domain.LoginRequest;
import music_individual.demo.domain.LoginResponse;
import music_individual.demo.persistence.UsersRepusitory;
import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginManagerImpl implements ILoginManager {

    private final UsersRepusitory repo;
    private final PasswordEncoder passwordEncoder;
    private final IAccessTokenManager accessTokenManager;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = repo.findByUsername(loginRequest.getUsername());

        String accessToken;
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }
        accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    //test
    public String generateAccessToken(UserEntity user){
        return accessTokenManager.encode(AccessToken.builder()
                .subject(user.getUsername())
                .role(user.getRole())
                .userID(user.getId())
                .build());
    }

}
