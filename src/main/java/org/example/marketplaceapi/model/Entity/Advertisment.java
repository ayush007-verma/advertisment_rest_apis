package org.example.marketplaceapi.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Advertisment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "advertisment", cascade = CascadeType.ALL)
    // using a Casecade here first add the detail data then fetch with Advertisment..
    private List<Product> products;

    @PrePersist
    public void prePersist() {
        if (publicationDate == null) {
          publicationDate = LocalDate.now();
        }
    }

}
