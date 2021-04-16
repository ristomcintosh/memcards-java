package com.memcards.service;

import com.memcards.model.Flashcard;
import com.memcards.repository.DeckRepository;
import com.memcards.repository.FlashcardRepository;
import com.memcards.service.exception.DeckNotFoundException;
import com.memcards.service.exception.FlashcardNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final DeckRepository deckRepository;

    public FlashcardService(FlashcardRepository flashcardRepository, DeckRepository deckRepository) {
        this.flashcardRepository = flashcardRepository;
        this.deckRepository = deckRepository;
    }

    public Flashcard createFlashcard(Flashcard flashcard, Long deckId) throws DeckNotFoundException {
        var deck = deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
        flashcard.setDeck(deck);
        return flashcardRepository.save(flashcard);
    }

    public Flashcard updateFlashcard(Flashcard flashcard, UUID flashcardId) throws FlashcardNotFoundException {
        var flashcardInDb = flashcardRepository.findById(flashcardId).orElseThrow(FlashcardNotFoundException::new);
        flashcard.setDeck(flashcardInDb.getDeck());

        return flashcardRepository.save(flashcard);
    }

    public void deleteFlashcard(UUID flashcardId) throws FlashcardNotFoundException {
        Flashcard flashcardInDb = flashcardRepository.findById(flashcardId).orElseThrow(FlashcardNotFoundException::new);
        flashcardRepository.delete(flashcardInDb);
    }
}
