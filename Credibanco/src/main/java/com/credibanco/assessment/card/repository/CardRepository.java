package com.credibanco.assessment.card.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.model.Card;

@Repository
public interface CardRepository  extends JpaRepository<Card, Long>{
	Optional<Card> findByPan(String pan);
	Optional<Card> deleteByPan(String pan);
}
