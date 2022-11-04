package music_individual.demo.controller;

import music_individual.demo.domain.*;
import music_individual.demo.business.*;
import music_individual.demo.business.GetSongUseCase;
import lombok.AllArgsConstructor;
import music_individual.demo.business.GetSongsUseCase;
import music_individual.demo.domain.GetAllSongsRequest;
import music_individual.demo.domain.GetAllSongsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {

    private final GetSongUseCase getSongUseCase;
    private final GetSongsUseCase getSongsUseCase;

    private final CreateSongUseCase createSongUseCase;

    @GetMapping()
    @CrossOrigin("http://localhost:3000/")
    public ResponseEntity<GetAllSongsResponse> getAllSongs(@RequestParam(value="type", required = false) String type){
        GetAllSongsRequest request = GetAllSongsRequest.builder().type(type).build();
        GetAllSongsResponse response = getSongsUseCase.getSongs(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Song> getSong(@PathVariable(value = "id") final int id){
        final Optional<Song> songOptional = getSongUseCase.getSong(id);
        if (songOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(songOptional.get());
    }
}



