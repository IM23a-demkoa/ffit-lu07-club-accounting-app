package ch.bzz.controller;

import ch.bzz.generated.api.AccountApi;
import ch.bzz.generated.model.Account;
import ch.bzz.repository.AccountRepository;
import ch.bzz.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountApiController implements AccountApi {

    private final AccountRepository accountRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @Override
    public ResponseEntity<Account> createAccount(Account account) {
        // Optional: Prüfen, ob das Project existiert
        var project = projectRepository.findById(account.getProject()).orElseThrow();
        account.setProject(project);
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.status(201).body(savedAccount);
    }
}
