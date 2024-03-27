package entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String url;
}
