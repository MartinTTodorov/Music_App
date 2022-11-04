package music_individual.demo.persistence.entities;

import
        lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class SongEntity {
    private int id;
    private String name;
    private String type;
    private String author;
}
