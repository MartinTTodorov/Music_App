package music_individual.demo.controller;

import music_individual.demo.business.*;
import lombok.AllArgsConstructor;
import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.domain.CreateSongRequest;
import music_individual.demo.persistence.entities.SongEntity;
import music_individual.demo.security.Authorization.isAuthorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("/songs")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class SongController {


    private final ISongsManager songsManager;


    @isAuthorized
    @RolesAllowed({"ROLE_ADMIN", "ROLE_Listener"})
    @GetMapping()
    public ResponseEntity GetAllSongs(){

        return ResponseEntity.ok(songsManager.GetAllSongs());
    }

    @isAuthorized
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity CreateSong(@RequestBody CreateSongRequest request){
        songsManager.AddSong(songConverter(request));
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
    }

    @DeleteMapping("{id}")
    public ResponseEntity DeleteSong(@PathVariable Integer songId){

        try{
        songsManager.DeleteSong(songId);
        }
        catch(ObjectMissingException e){
            return ResponseEntity.status(405).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }


    public SongEntity songConverter(CreateSongRequest request){
        return SongEntity.builder().name(request.getName()).author(request.getAuthor()).type(request.getType()).build();
    }






}



