package br.com.coderef.controller;

        import br.com.coderef.model.BankAccount;
        import br.com.coderef.repository.BankAccountRepository;
        import lombok.AllArgsConstructor;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private BankAccountRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<BankAccount>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}