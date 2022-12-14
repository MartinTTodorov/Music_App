package music_individual.demo.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="song")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @NotBlank
    @Length(min=2, max=100)
    @Column
    private String name;

    @NotBlank
    @Length(min=2, max=100)
    @Column
    private String type;

    @NotBlank
    @Length(min=2, max=100)
    @Column
    private String author;

    @ManyToMany(mappedBy = "songs")
    private List<PlaylistEntity> playlists = new ArrayList<>();
}
