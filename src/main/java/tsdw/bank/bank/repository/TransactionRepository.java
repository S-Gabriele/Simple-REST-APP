package tsdw.bank.bank.repository;
import tsdw.bank.bank.domain.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findByAccepted(boolean accepted);
    List<Transaction> findByPaymentId(Long id);
}