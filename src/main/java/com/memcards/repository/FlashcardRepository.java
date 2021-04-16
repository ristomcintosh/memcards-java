package com.memcards.repository;

import com.memcards.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, UUID> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Flashcard f WHERE f.deck.id = ?1")
    void deleteAllByDeckId(Long deckId);
}
