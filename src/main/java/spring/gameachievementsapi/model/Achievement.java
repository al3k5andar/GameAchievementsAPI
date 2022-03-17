package spring.gameachievementsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Achievement implements Serializable
{
    @Id
    @Type(type = "uuid-char") // This line is required because UUID in java and UUID in MySQL database are different
    private UUID id;
    @NotEmpty
    @Size(min = 10, max = 100, message = "The display name must be between 10 and 100 characters")
    private String displayName;

    @NotEmpty
    @Size(min = 10, max = 100, message = "The name must be between 10 and 500 characters")
    private String description;

    @URL
    private String icon;
    private Long displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
