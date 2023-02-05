package com.lila.app.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
// This service takes in a patient's full name and email as parameters
// It sends the patient's ID to the verification service for identity check
// If the check passes it creates a patient record with the details and persists into the database
public class PatientAccountsService {
    private final PatientRepository patientRepository;
    private final RestTemplate restTemplate;
    public void createAccount(PatientAccountCreationRequest request) {
        Patient patient = Patient.builder().fullName(request.fullName()).email(request.email()).build();
        patientRepository.saveAndFlush(patient);
        VerificationResponse verificationResponse = restTemplate.getForObject("http://localhost:8082/api/v1/verification/{patientId}", VerificationResponse.class, patient.getId());
        if(verificationResponse.equals("true")) {
            throw new IllegalStateException("identify check failed");
        }
        patientRepository.save(patient);
    }
}
