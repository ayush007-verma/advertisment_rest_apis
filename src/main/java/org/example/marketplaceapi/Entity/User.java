package org.example.marketplaceapi.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String UserName;


    @Column(nullable = false)
    private String Password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL) // using a casecade here first add the detail data then fetch with user.
    private List<Advertisment> advertisments;


}
