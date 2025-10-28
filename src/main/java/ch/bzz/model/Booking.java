package ch.bzz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "text", nullable = false, length = 128)
    private String text;

    @ManyToOne
    @JoinColumn(name = "debitAccount", nullable = false)
    private Account debitAccount;

    @ManyToOne
    @JoinColumn(name = "creditAccount", nullable = false)
    private Account creditAccount;

    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "projectName", nullable = false)
    private Project project;
}
