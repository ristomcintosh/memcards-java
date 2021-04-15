package com.memcards.service;

import com.memcards.model.Deck;
import com.memcards.repository.DeckRepository;
import com.memcards.service.exception.DeckNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class DeckService {

    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
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
        deckRepository.delete(deckInDb);
    }

    public Deck getDeck(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId).orElseThrow(DeckNotFoundException::new);
    }
}
