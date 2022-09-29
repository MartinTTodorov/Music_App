package persistence.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Song {
    private int id;
    private String name;
}
