package com.legoreview.controller;

import com.legoreview.models.LegoSet;
import com.legoreview.repository.LegoSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/lego")
public class LegoSetController {
    @Autowired
    private LegoSetRepository legoSetRepository;

    @GetMapping
    public List<LegoSet> getAllLegoSets() {
        return legoSetRepository.findAll();
    }

    @GetMapping("/{id}")
    public LegoSet getLegoSetById(@PathVariable Long id) {
        return legoSetRepository.findById(id).orElse(null);
    }

    @PostMapping
    public LegoSet createLegoSet(@RequestBody LegoSet legoSet) {
        return legoSetRepository.save(legoSet);
    }

    @PutMapping("/{id}")
    public LegoSet updateLegoSet(@PathVariable Long id, @RequestBody LegoSet legoSetDetails) {
        LegoSet legoSet = legoSetRepository.findById(id).orElse(null);
        if (legoSet != null) {
            legoSet.setName(legoSetDetails.getName());
            legoSet.setDescription(legoSetDetails.getDescription());
            legoSet.setPrice(legoSetDetails.getPrice());
            return legoSetRepository.save(legoSet);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLegoSet(@PathVariable Long id) {
        legoSetRepository.deleteById(id);
    }
}
