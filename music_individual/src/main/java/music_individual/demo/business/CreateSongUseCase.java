package music_individual.demo.business;

import music_individual.demo.domain.CreateSongRequest;

public interface CreateSongUseCase {
    CreateSongRequest createSong(CreateSongRequest request);
}
