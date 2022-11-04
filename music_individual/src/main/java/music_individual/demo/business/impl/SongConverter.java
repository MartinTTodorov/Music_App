package music_individual.demo.business.impl;

import music_individual.demo.domain.Song;
import music_individual.demo.persistence.entities.SongEntity;

final class SongConverter {
    private SongConverter(){
    }

    public static Song convert(SongEntity songEntity){
        return Song.builder()
                .id(songEntity.getId())
                .name(songEntity.getName())
                .type(songEntity.getType())
                .author(songEntity.getAuthor())
                .build();
    }
}
