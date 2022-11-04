package music_individual.demo.business;

import music_individual.demo.domain.GetAllSongsResponse;
import music_individual.demo.domain.GetAllSongsRequest;

public interface GetSongsUseCase {
    GetAllSongsResponse getSongs(GetAllSongsRequest request);
}
