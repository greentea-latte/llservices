package com.lila.app.patient;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping
    @Slf4j
    @AllArgsConstructor
// This controller accepts a POST request to the endpoint
// It routes the patient detail parameters to the accounts service
    public class PatientController {
        private final PatientAccountsService patientAccountsService;
        @PostMapping("api/v1/patients")
        public void createPatientAccount(@RequestBody PatientAccountCreationRequest patientAccountCreationRequest) {
            log.info("new patient registration {}", patientAccountCreationRequest);
            patientAccountsService.createAccount(patientAccountCreationRequest);
        }
    }
