package music_individual.demo.business;

import music_individual.demo.domain.AccessToken;

public interface IAccessTokenManager {
    public String encode(AccessToken accessToken);
    public AccessToken decode(String accessToken);
}
