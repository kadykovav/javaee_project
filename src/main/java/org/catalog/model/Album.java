package org.catalog.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@SequenceGenerator(name = "album_sequence_id", sequenceName = "album_seq_id", allocationSize = 1)
@Table(name = "album")
public class Album {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "album_sequence_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id", nullable = false, referencedColumnName = "id")
    private Executor executor;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Composition> compositions;


    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}
