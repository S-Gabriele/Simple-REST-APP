package tsdw.bank.bank.controller;
//import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import tsdw.bank.bank.service.*;
import tsdw.bank.bank.domain.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService service;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> entityList = service.getAllTransactions();
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable long id) {
        Optional<Transaction> entity = service.getTransaction(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/transactions/accepted")
    public ResponseEntity<List<Transaction>> getAllAcceptedTransactions() {
        List<Transaction> entityList = service.getTransactionsAccepted(true);
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("/transactions/rejected")
    public ResponseEntity<List<Transaction>> getAllRejectedTransactions() {
        List<Transaction> entityList = service.getTransactionsAccepted(false);
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("/payment/{id}/transaction")
    public ResponseEntity<List<Transaction>> getPaymentTransaction(@PathVariable long id) {
        List<Transaction> entityList = service.getTransactionByPayment(id);
        return ResponseEntity.ok(entityList);
    }
    
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody Transaction e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Payment> payment = paymentService.getPayment(e.getPayment().getId());
        if(!payment.isPresent())
            return ResponseEntity.notFound().build();
        e.setPayment(payment.get());
        Transaction entity = service.addTransaction(e);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/transactions")
    public ResponseEntity<Transaction> updateTransaction(@Valid @RequestBody Transaction e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        if(!paymentService.getPayment(e.getPayment().getId()).isPresent())
            return ResponseEntity.notFound().build();
        Transaction entity = service.updateTransaction(e);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable long id) {
        if (service.getTransaction(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}