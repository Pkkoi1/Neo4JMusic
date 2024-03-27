package entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Genre {
    private String id;
    private String name;
    private String description;
}
