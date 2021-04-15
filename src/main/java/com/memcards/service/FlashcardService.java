package com.memcards.service;

import com.memcards.model.Flashcard;
import com.memcards.repository.DeckRepository;
import com.memcards.repository.FlashcardRepository;
import com.memcards.service.exception.DeckNotFoundException;
import org.springframework.stereotype.Service;

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
}
