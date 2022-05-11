package tsdw.bank.bank.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tsdw.bank.bank.domain.*;
import tsdw.bank.bank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment addPayment(Payment e) {
        return repository.save(e);
    }

    public Optional<Payment> getPayment(Long id) {
        return repository.findById(id);
    }

    public List<Payment> getAllPayments() {
        List<Payment> output = new ArrayList<Payment>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Payment updatePayment(Payment e) {
        return repository.save(e);
    }

    public void deletePayment(Payment e) {
        repository.delete(e);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }

    public List<Payment> getPaymentsSender(String sender){
        List<Payment> output = new ArrayList<Payment>();
        repository.findBySender(sender).forEach(output::add);
        return output; 
    }
    public List<Payment> getPaymentsReceiver(String receiver){
        List<Payment> output = new ArrayList<Payment>();
        repository.findByReceiver(receiver).forEach(output::add);
        return output; 
    }
}