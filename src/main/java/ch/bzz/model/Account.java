package ch.bzz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "accountNumber", nullable = false, unique = true, length = 32)
    private String accountNumber;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = "projectName", nullable = false)
    private Project project;
}
