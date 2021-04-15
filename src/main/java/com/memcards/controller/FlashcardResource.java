package com.memcards.controller;

import com.memcards.controller.dto.CreatedFlashcardRequest;
import com.memcards.model.Flashcard;
import com.memcards.service.FlashcardService;
import com.memcards.service.exception.DeckNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlashcardResource {

    private FlashcardService flashcardService;

    public FlashcardResource(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping("/decks/{deckId}/flashcards")
    public ResponseEntity createFlashcard(@PathVariable Long deckId, @RequestBody CreatedFlashcardRequest createdFlashcardRequest) {
        try {
        flashcardService.createFlashcard(createdFlashcardRequest.toFlashcard(), deckId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DeckNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
