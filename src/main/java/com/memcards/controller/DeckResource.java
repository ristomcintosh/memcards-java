package com.memcards.controller;

import com.memcards.controller.dto.*;
import com.memcards.service.DeckService;
import com.memcards.service.exception.DeckNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DeckResource {

    public DeckResource(DeckService deckService) {
        this.deckService = deckService;
    }

    private DeckService deckService;

    @GetMapping("/decks")
    public ResponseEntity<List<DeckResponse>> getDecks() {
        var decks = deckService.getAllDecks();
        return ResponseEntity.ok(new DecksResponse(decks).getDecks());
    }

    @GetMapping("/decks/{deckId}")
    public ResponseEntity<DeckWithFlashcardsResponse> getDeck(@PathVariable Long deckId) {
        try {
            var deck = deckService.getDeck(deckId);
            return ResponseEntity.ok(new DeckWithFlashcardsResponse(deck));
        } catch (DeckNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/decks")
    public ResponseEntity<DeckResponse> createDeck(@RequestBody CreatedDeckRequest createdDeckRequest){
        var deck = createdDeckRequest.toDeck();
        var createdDeck = deckService.createDeck(deck);

        return ResponseEntity.ok(new DeckResponse(createdDeck));
    }

    @PutMapping("/decks/{deckId}")
    public ResponseEntity updateDeck(@PathVariable Long deckId, @RequestBody UpdatedDeckRequest updatedDeckRequest){

        var deck = updatedDeckRequest.toDeck();

        try {
            var updatedDeck = deckService.updateDeck(deckId, deck);
            return ResponseEntity.ok(new DeckResponse(updatedDeck));
        } catch (DeckNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/decks/{deckId}")
    public ResponseEntity deleteDeck(@PathVariable Long deckId) {
        try {
            deckService.deleteDeck(deckId);
            return ResponseEntity.noContent().build();
        } catch (DeckNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
