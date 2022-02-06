package spring.gameachievementsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Game
{
    @Id
    @Type(type = "uuid-char") // This line is required because UUID in java and UUID in MySQL database are different
    private UUID id;

    @NotEmpty
    @Size(min = 10, max = 100, message = "The name must be between 10 and 100 characters")
    private String displayName;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<Achievement> achievementList;
}
