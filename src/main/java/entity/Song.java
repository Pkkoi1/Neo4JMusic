package entity;
import lombok.*;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private String id;
    private String name;
    private String runtime;
    private String lyric;
    private String fileLink;
}
