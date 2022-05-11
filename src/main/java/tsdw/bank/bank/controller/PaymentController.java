package tsdw.bank.bank.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
import tsdw.bank.bank.service.*;
import tsdw.bank.bank.domain.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> entityList = service.getAllPayments();
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("/payments/received/{receiver}")
    public ResponseEntity<List<Payment>> getReceiverPayments(@PathVariable String receiver) {
        List<Payment> entityList = service.getPaymentsReceiver(receiver);
        return ResponseEntity.ok(entityList);
    }
    
    @GetMapping("/payments/sent/{sender}")
    public ResponseEntity<List<Payment>> getSenderPayments (@PathVariable String sender) {
        List<Payment> entityList = service.getPaymentsSender(sender);
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable long id) {
        Optional<Payment> entity = service.getPayment(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> addPayment(@Valid @RequestBody Payment e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Payment entity = service.addPayment(e);
        return ResponseEntity.created(new URI("/api/payments" + entity.getId())).body(entity);
    }

    @PutMapping("/payments")
    public ResponseEntity<Payment> updatePayment(@Valid @RequestBody Payment e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Payment entity = service.updatePayment(e);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable long id) {
        if (service.getPayment(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}