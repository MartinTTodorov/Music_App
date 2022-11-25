package music_individual.demo.business;

import music_individual.demo.domain.LoginRequest;
import music_individual.demo.domain.LoginResponse;

public interface ILoginManager {
    public LoginResponse login(LoginRequest loginRequest);
}
