package ch.bzz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private long id;
    private String accountNumber;
    private String name;
    private Project project;
}
