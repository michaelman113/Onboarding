package com.onboarding; // Changed package

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sequence_data")
public class SequenceData {

    @Id
    private long sequenceNumber;
    
    private String randomString;

    public SequenceData() {
    }

    public SequenceData(long sequenceNumber, String randomString) {
        this.sequenceNumber = sequenceNumber;
        this.randomString = randomString;
    }

    // --- Getters and Setters ---
    public long getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(long sequenceNumber) { this.sequenceNumber = sequenceNumber; }
    public String getRandomString() { return randomString; }
    public void setRandomString(String randomString) { this.randomString = randomString; }

    @Override
    public String toString() {
        return "SequenceData{" +
                "sequenceNumber=" + sequenceNumber +
                ", randomString='" + randomString + '\'' +
                '}';
    }
}