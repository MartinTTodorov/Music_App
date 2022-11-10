package music_individual.demo.controller;

import music_individual.demo.business.*;
import lombok.AllArgsConstructor;
import music_individual.demo.domain.CreateSongRequest;
import music_individual.demo.persistence.entities.SongEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/songs")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class SongController {


    private final ISongsManager songsManager;

    @GetMapping()
    public ResponseEntity GetAllSongs(){

        return ResponseEntity.ok(songsManager.GetAllSongs());
    }

    @PostMapping()
    public ResponseEntity CreateSong(@RequestBody CreateSongRequest request){
        songsManager.AddSong(SongEntity.builder().name(request.getName()).author(request.getAuthor()).type(request.getType()).build());
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
    }



}



