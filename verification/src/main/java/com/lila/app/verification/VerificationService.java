package com.lila.app.verification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
// This service takes in the patient ID parameter
// It would typically perform a check on the ID, but for now it will return "True" as default
// It saves the verification details into the database
public class VerificationService {
    private final VerificationRepository verificationRepository;
    public String verifyIdentity(Integer patientId) {
        Verification verification = Verification.builder().patientId(patientId).type("identity check").build();
        verificationRepository.save(verification);
        return "True";
    }
}
