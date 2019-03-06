package si.test.backend.coding.challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Movies Movies have properties as title, year, description, list of actors, some pictures, etc. (as identifier use imdbID)
 *
 * @author Anže Germovšek
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "movies")
public class Movies implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "ImdbId is required")
    @Column(name = "IMDB_ID")
    private Long imdbId;

    @NotNull(message = "Title is required")
    private String title;

    private String year;

    private String description;

    @ManyToMany (fetch = FetchType.EAGER, mappedBy = "movies")
    @JsonIgnoreProperties("movies")
    @EqualsAndHashCode.Exclude
    private Set<Actors> actors;
}
