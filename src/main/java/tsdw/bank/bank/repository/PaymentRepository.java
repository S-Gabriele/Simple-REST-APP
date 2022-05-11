package tsdw.bank.bank.repository;

import tsdw.bank.bank.domain.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
    List<Payment> findBySender(String sender);
    List<Payment> findByReceiver(String receiver);
}