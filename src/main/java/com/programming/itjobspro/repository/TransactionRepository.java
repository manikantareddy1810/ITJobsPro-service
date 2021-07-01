package com.programming.itjobspro.repository;

import com.programming.itjobspro.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transactions,Integer> {

    List<Transactions> findAllByType(String id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM transactions t\n" +
            "    WHERE t.account_number =:accountNumber and t.date between :date1 and :date2", nativeQuery = true)
    List<Transactions> getAllTransactionsWithInRange(@Param("accountNumber") String accountNumber, @Param("date1") Date date1, @Param("date2") Date date2);
}
