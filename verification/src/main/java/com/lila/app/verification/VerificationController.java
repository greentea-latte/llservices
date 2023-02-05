package com.lila.app.verification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/verification")
@AllArgsConstructor
@Slf4j
// This controller gets called whenever a patient identity check is required
// It accepts GET requests to the endpoint
// It will return true if the patient identity check has no problems
public class VerificationController {
    private final VerificationService verificationService;

    @GetMapping(path = "{patientId}")
    public VerificationResponse identityVerificationPassed(@PathVariable("patientId") Integer patientId) {
        String verificationDetails = verificationService.verifyIdentity((patientId));
        return new VerificationResponse(verificationDetails);
    }
}
