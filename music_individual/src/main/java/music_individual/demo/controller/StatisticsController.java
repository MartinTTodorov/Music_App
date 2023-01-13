package music_individual.demo.controller;

import lombok.AllArgsConstructor;
import music_individual.demo.business.ILoginStatisticsManager;
import music_individual.demo.business.IPlaylistStatisticsManager;
import music_individual.demo.business.IPlaylistsManager;
import music_individual.demo.business.IUsersManager;
import music_individual.demo.domain.AddListenedPlaylistRequest;
import music_individual.demo.domain.AddLoginRequest;
import music_individual.demo.domain.AddSongToPlaylistRequest;
import music_individual.demo.persistence.entities.ListenedPlaylistsEntity;
import music_individual.demo.persistence.entities.LoginsEntity;
import music_individual.demo.persistence.entities.PlaylistEntity;
import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class StatisticsController {

    private ILoginStatisticsManager loginStatisticsManager;
    private IPlaylistStatisticsManager playlistStatisticsManager;
    private IPlaylistsManager playlistsManager;

    private IUsersManager usersManager;

    @GetMapping("/playlists/top")
    public ResponseEntity getTop3Playlists(){
        return ResponseEntity.ok(playlistStatisticsManager.getTop5PlaylistsLast7Days());
    }

    @PostMapping("/playlists")
    public ResponseEntity registerListenedPlaylist(AddListenedPlaylistRequest request){
        return ResponseEntity.ok(converter(request));
    }

    @GetMapping("/logins/{days}")
    public ResponseEntity getNumberOfLoginsLastDays(@PathVariable(value = "days") Integer numberOfDays){
        return ResponseEntity.ok(loginStatisticsManager.getNumberOfLoginsForTheLastDays(numberOfDays));
    }

    @PostMapping("/logins")
    public ResponseEntity registerLogin(AddLoginRequest request){
        return ResponseEntity.ok(converter(request));
    }

    public ListenedPlaylistsEntity converter(AddListenedPlaylistRequest request){
        PlaylistEntity playlist = playlistsManager.getPlaylistByID(request.getPlaylistId());
        return ListenedPlaylistsEntity.builder().id(request.getId()).playlist(playlist).playedOn(request.getPlayed_on()).build();
    }

    public LoginsEntity converter(AddLoginRequest request){
        UserEntity user = usersManager.GetUserByID(request.getUserId());
        return LoginsEntity.builder().id(request.getId()).time(request.getTime()).userId(user).build();
    }
}
