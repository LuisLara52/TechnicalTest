package com.credibanco.assessment.card.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	Optional<Transaction> findByReferenceNumber(String reference_number);
}
