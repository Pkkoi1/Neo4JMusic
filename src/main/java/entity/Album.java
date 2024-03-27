package entity;
import lombok.*;
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String id;
    private String title;
    private Double price;
    private Integer yearOfRelease;
    private String downloadLink;
    private String songID;
    private String artistID;
    private String genreID;

}
