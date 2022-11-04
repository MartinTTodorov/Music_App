package music_individual.demo.persistence.impl;

import music_individual.demo.persistence.SongsRepo;
import music_individual.demo.persistence.entities.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
public class SongsRepoMOCK implements SongsRepo {

    private List<SongEntity> songs;



    public SongsRepoMOCK(){
        this.songs = new ArrayList<>();

        //Mock data
        songs.add(new SongEntity(1, "Old town road", "pop", "Lil Nas X"));
        songs.add(new SongEntity(2, "Crazy", "pop", "Jordan"));
        songs.add(new SongEntity(3, "Mask", "rap", "Future"));
        songs.add(new SongEntity(4, "Days", "rap", "Peter"));
        songs.add(new SongEntity(5, "Bad guy", "pop", "Billie Eillish"));
    }

    @Override
    public List<SongEntity> GetAllSongs() {
        return Collections.unmodifiableList(this.songs);
    }

    @Override
    public Optional<SongEntity> GetSongByID(int id) {
        return this.songs.stream().filter(SongEntity -> SongEntity.getId() == id).findFirst();
    }
}
