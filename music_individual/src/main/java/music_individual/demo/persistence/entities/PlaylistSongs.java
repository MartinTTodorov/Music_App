package music_individual.demo.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Playlist_songs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSongs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @NotBlank
    @Column
    private Integer playlistID;

    @NotBlank
    @Column
    private Integer songID;
}
