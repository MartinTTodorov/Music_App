package music_individual.demo.business;

import music_individual.demo.domain.LoginRequest;
import music_individual.demo.domain.LoginResponse;
import music_individual.demo.persistence.entities.UserEntity;

public interface ILoginManager {
    public LoginResponse login(LoginRequest loginRequest);
}
