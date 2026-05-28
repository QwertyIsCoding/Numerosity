package org.vaadin.numerosity.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vaadin.numerosity.Subsystems.QuestionSeeder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * REST controller for question operations.
 */
@RestController
@RequestMapping({"/api/questions", "/api/v1/questions"})
public class QuestionRestController {

    private final QuestionSeeder questionSeeder;

    public QuestionRestController(QuestionSeeder questionSeeder) {
        this.questionSeeder = questionSeeder;
    }

    /**
     * Get all questions.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllQuestions() {
        return ResponseEntity.ok(Map.of(
                "message", "Use /category/{category} or /difficulty/{difficulty} to filter",
                "endpoints", List.of(
                        "/api/v1/questions/category/{category}",
                        "/api/v1/questions/difficulty/{difficulty}",
                        "/api/v1/questions/random"
                )
        ));
    }

    /**
     * Get questions by category.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Map<String, Object>>> getQuestionsByCategory(
            @PathVariable String category) {
        List<Map<String, Object>> questions = questionSeeder.getQuestionsByCategory(category);
        return ResponseEntity.ok(questions);
    }

    /**
     * Get questions by difficulty.
     */
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Map<String, Object>>> getQuestionsByDifficulty(
            @PathVariable String difficulty) {
        List<Map<String, Object>> questions = questionSeeder.getQuestionsByDifficulty(difficulty);
        return ResponseEntity.ok(questions);
    }

    /**
     * Get a random question, optionally filtered.
     */
    @GetMapping("/random")
    public ResponseEntity<Map<String, Object>> getRandomQuestion(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String difficulty) {
        List<Map<String, Object>> questions;
        if (category != null) {
            questions = questionSeeder.getQuestionsByCategory(category);
        } else if (difficulty != null) {
            questions = questionSeeder.getQuestionsByDifficulty(difficulty);
        } else {
            questions = List.of(Map.of(
                "id", "demo_1",
                "text", "What is 2 + 2?",
                "options", List.of(
                    Map.of("id", "a", "text", "3"),
                    Map.of("id", "b", "text", "4"),
                    Map.of("id", "c", "text", "5"),
                    Map.of("id", "d", "text", "6")
                ),
                "correct_option_id", "b",
                "difficulty", "easy",
                "category", "Arithmetic"
            ));
        }
        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(questions.size());
        return ResponseEntity.ok(questions.get(randomIndex));
    }
}
