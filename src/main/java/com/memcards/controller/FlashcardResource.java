package com.memcards.controller;

import com.memcards.controller.dto.CreatedFlashcardRequest;
import com.memcards.controller.dto.FlashcardResponse;
import com.memcards.controller.dto.UpdatedFlashcardRequest;
import com.memcards.model.Flashcard;
import com.memcards.service.FlashcardService;
import com.memcards.service.exception.DeckNotFoundException;
import com.memcards.service.exception.FlashcardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/decks/{deckId}")
public class FlashcardResource {

    private FlashcardService flashcardService;

    public FlashcardResource(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping("/flashcards")
    public ResponseEntity createFlashcard(@PathVariable Long deckId, @RequestBody CreatedFlashcardRequest createdFlashcardRequest) {
        try {
            flashcardService.createFlashcard(createdFlashcardRequest.toFlashcard(), deckId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DeckNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/flashcards/{flashcardId}")
    public ResponseEntity updateFlashcard(@PathVariable Long deckId,
                                          @PathVariable UUID flashcardId,
                                          @RequestBody UpdatedFlashcardRequest flashcardRequest) {
        try {
            var updatedFlashcard = flashcardService.updateFlashcard(flashcardRequest.toFlashcard(), flashcardId);
            return ResponseEntity.ok(new FlashcardResponse(updatedFlashcard));
        } catch (FlashcardNotFoundException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/flashcards/{flashcardId}")
    public ResponseEntity deleteFlashcard(@PathVariable UUID flashcardId){
        try {
            flashcardService.deleteFlashcard(flashcardId);
            return ResponseEntity.noContent().build();
        } catch (FlashcardNotFoundException e) {
        return ResponseEntity.badRequest().build();
        }
    }
}
