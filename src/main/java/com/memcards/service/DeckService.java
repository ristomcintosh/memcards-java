package com.memcards.service;

import com.memcards.model.Deck;
import com.memcards.repository.DeckRepository;
import com.memcards.repository.FlashcardRepository;
import com.memcards.service.exception.DeckNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    private final DeckRepository deckRepository;
    private final FlashcardRepository flashcardRepository;

    public DeckService(DeckRepository deckRepository, FlashcardRepository flashcardRepository) {
        this.deckRepository = deckRepository;
        this.flashcardRepository = flashcardRepository;
    }

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public Deck createDeck(Deck newDeck) {
        return deckRepository.save(newDeck);
    }

    public Deck updateDeck(Long deckId, Deck updatedDeck) throws DeckNotFoundException {
        Deck deckInDb = deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
        deckInDb.setName(updatedDeck.getName());
        return deckRepository.save(deckInDb);
    }

    public void deleteDeck(Long deckId) throws DeckNotFoundException {
        Deck deckInDb = deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
        flashcardRepository.deleteAllByDeckId(deckInDb.getId());
        deckRepository.delete(deckInDb);
    }

    public Deck getDeck(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
    }
}
