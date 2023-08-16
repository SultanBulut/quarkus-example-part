package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Director extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;

    public Director(String name,String lastname){
        this.name=name;
        this.lastname=lastname;
    }


    @OneToMany(mappedBy = "director",cascade = CascadeType.ALL)
    private List<MovieDirection> movieDirection;


}
