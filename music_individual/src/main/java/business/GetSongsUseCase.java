package business;

import domain.GetAllSongsResponse;
import domain.GetAllSongsRequest;

public interface GetSongsUseCase {
    GetAllSongsResponse getSongs(GetAllSongsRequest request);
}
