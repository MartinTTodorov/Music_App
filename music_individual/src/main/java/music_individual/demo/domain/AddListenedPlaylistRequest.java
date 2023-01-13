package music_individual.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import music_individual.demo.persistence.entities.PlaylistEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddListenedPlaylistRequest {
    private Integer id;
    private Integer playlistId;
    private String played_on;
}
