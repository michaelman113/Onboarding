package com.onboarding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceDataRepository extends JpaRepository<SequenceData, Long> {
}