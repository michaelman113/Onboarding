package com.onboarding; // Changed package

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// We import SequenceData from the same package, so no import needed

@Repository
public interface SequenceDataRepository extends JpaRepository<SequenceData, Long> {
}