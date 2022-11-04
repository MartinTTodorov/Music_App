package music_individual.demo.business.impl;


import lombok.AllArgsConstructor;
import music_individual.demo.business.GetSongsUseCase;
import music_individual.demo.domain.GetAllSongsRequest;
import music_individual.demo.domain.GetAllSongsResponse;
import music_individual.demo.domain.Song;
import music_individual.demo.persistence.SongsRepo;
import music_individual.demo.persistence.entities.SongEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSongsUseCaseImpl implements GetSongsUseCase {

    private SongsRepo songsRepo;
    @Override
    public GetAllSongsResponse getSongs(GetAllSongsRequest request) {
        List<SongEntity> results;

        final GetAllSongsResponse response = new GetAllSongsResponse();
        results = songsRepo.GetAllSongs();
        List<Song> songs = results.stream().map(SongConverter::convert).toList();
        response.setSongs(songs);

        return response;
    }
}
