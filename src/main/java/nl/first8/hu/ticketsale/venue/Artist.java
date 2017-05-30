package nl.first8.hu.ticketsale.venue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Rogier on 23-5-2017.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;
}
