package si.test.backend.coding.challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "actors")
/*@NamedQueries({
        @NamedQuery(
                name = "Actor.findActors",
                query = "SELECT a " +
                        "FROM Actors a"
        )
})*/
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@imdbId")
public class Actors implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 20, message = "Actor_id length > 20")
    @Column(name = "ACTOR_ID", unique = true, nullable = false, length = 20)
    private Integer actorId;

    private String firstname;

    private String lastname;

    private String email;

    private String country;

    @Temporal(TemporalType.TIMESTAMP)
    private Date born;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name="MOVIES_ACTORS",
            joinColumns=@JoinColumn(name="ACTOR_ID"),
            inverseJoinColumns=@JoinColumn(name="IMDB_ID"))
//    @JoinTable(name = "movies_actors")
    @JsonIgnoreProperties("actors")
    @EqualsAndHashCode.Exclude
    private Set<Movies> movies;
}
