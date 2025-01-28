package org.catalog.model;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "executor")
public class Executor {

    @Id
    @SequenceGenerator(name = "Executor_seq_id", sequenceName = "Executor_seq_id",allocationSize = 1)
    @GeneratedValue(generator = "Executor_seq_id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "executor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
