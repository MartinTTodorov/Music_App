package music_individual.demo.business.impl;

import lombok.AllArgsConstructor;
import music_individual.demo.business.CreateSongUseCase;
import music_individual.demo.domain.CreateSongRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateSongUseCaseImpl implements CreateSongUseCase {
    @Override
    public CreateSongRequest createSong(CreateSongRequest request) {
        return null;
    }
}
