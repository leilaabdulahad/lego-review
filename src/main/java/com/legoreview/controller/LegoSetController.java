package com.legoreview.controller;

import com.legoreview.models.LegoSet;
import com.legoreview.repository.LegoSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/lego")
@CrossOrigin(origins = "http://localhost:3000")
public class LegoSetController {

    @Autowired
    private LegoSetRepository legoSetRepository;

    // Logger (optional, for debugging purposes)
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LegoSetController.class);

    // Fetch all Lego sets
    @GetMapping
    public List<LegoSet> getAllLegoSets() {
        logger.info("Fetching all Lego sets");
        return legoSetRepository.findAll();
    }

    // Fetch Lego set by ID
    @GetMapping("/{id}")
    public ResponseEntity<LegoSet> getLegoSetById(@PathVariable Long id) {
        return legoSetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new Lego set
    @PostMapping
    public ResponseEntity<LegoSet> createLegoSet(@Valid @RequestBody LegoSet legoSet) {
        logger.info("Creating a new Lego set: {}", legoSet.getName());
        LegoSet createdLegoSet = legoSetRepository.save(legoSet);
        return ResponseEntity.ok(createdLegoSet);
    }

    // Update an existing Lego set
    @PutMapping("/{id}")
    public ResponseEntity<LegoSet> updateLegoSet(@PathVariable Long id, @Valid @RequestBody LegoSet legoSetDetails) {
        return legoSetRepository.findById(id).map(existingLegoSet -> {
            logger.info("Updating Lego set with ID: {}", id);
            existingLegoSet.setName(legoSetDetails.getName());
            existingLegoSet.setDescription(legoSetDetails.getDescription());
            existingLegoSet.setPrice(legoSetDetails.getPrice());
            existingLegoSet.setTheme(legoSetDetails.getTheme());
            existingLegoSet.setAge(legoSetDetails.getAge());
            existingLegoSet.setImageUrl(legoSetDetails.getImageUrl());
            existingLegoSet.setPieces(legoSetDetails.getPieces());
            existingLegoSet.setRating(legoSetDetails.getRating());
            existingLegoSet.setReview(legoSetDetails.getReview());
            existingLegoSet.setReleaseDate(legoSetDetails.getReleaseDate());
            LegoSet updatedLegoSet = legoSetRepository.save(existingLegoSet);
            return ResponseEntity.ok(updatedLegoSet);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a Lego set by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLegoSet(@PathVariable Long id) {
        if (legoSetRepository.existsById(id)) {
            logger.info("Deleting Lego set with ID: {}", id);
            legoSetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Fetch Lego sets by theme
    @GetMapping("/theme/{theme}")
    public List<LegoSet> getLegoSetsByTheme(@PathVariable String theme) {
        logger.info("Fetching Lego sets by theme: {}", theme);
        return legoSetRepository.findByTheme(theme);
    }

    // Fetch Lego sets released after a specific date
    @GetMapping("/released-after/{date}")
    public List<LegoSet> getLegoSetsReleasedAfter(@PathVariable LocalDate date) {
        logger.info("Fetching Lego sets released after: {}", date);
        return legoSetRepository.findByReleaseDateAfter(date);
    }
}
