package tsdw.bank.bank.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tsdw.bank.bank.domain.*;
import tsdw.bank.bank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public Transaction addTransaction(Transaction e) {
        return repository.save(e);
    }

    public Optional<Transaction> getTransaction(Long id) {
        return repository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> output = new ArrayList<Transaction>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Transaction updateTransaction(Transaction e) {
        return repository.save(e);
    }

    public void deleteTransaction(Transaction e) {
        repository.delete(e);
    }

    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }

    public List<Transaction> getTransactionByPayment(Long id) {
        List<Transaction> output = new ArrayList<Transaction>();
        repository.findByPaymentId(id).forEach(output::add);
        return output;
    }
    public List<Transaction> getTransactionsAccepted(Boolean accepted) {
        List<Transaction> output = new ArrayList<Transaction>();
        repository.findByAccepted(accepted).forEach(output::add);
        return output;
    }

}