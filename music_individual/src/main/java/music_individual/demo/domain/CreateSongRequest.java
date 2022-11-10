package music_individual.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateSongRequest {
    private String name;
    private String author;
    private String type;
}
