package com.onboarding; // Changed package

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// SequenceData and SequenceDataRepository are in the same package

@RestController
@RequestMapping("/api/data")
public class SequenceQueryController {

    @Autowired
    private SequenceDataRepository repository;

    @GetMapping
    public List<SequenceData> getAllData() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "sequenceNumber"));
    }

    @GetMapping("/{sequenceNumber}")
    public ResponseEntity<SequenceData> getDataBySequenceNumber(@PathVariable Long sequenceNumber) {
        Optional<SequenceData> data = repository.findById(sequenceNumber);
        
        return data.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
}