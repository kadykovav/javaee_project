package org.catalog.model;


import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "Composition_sequence_id", sequenceName = "Composition_seq_id",allocationSize = 1)
@Table(name = "composition")
public class Composition {
    @Id
    @GeneratedValue(generator = "Composition_sequence_id")
    @Column(name = "id", nullable = false, length = 100)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "length", nullable = false)
    private String length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)
    private Album album;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
