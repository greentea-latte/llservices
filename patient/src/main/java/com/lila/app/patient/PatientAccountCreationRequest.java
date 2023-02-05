package com.lila.app.patient;

// The interface for making a patient account request is defined here
// The request parameters are the patient's full name and email address
public record PatientAccountCreationRequest(
String fullName,
String email) {
}
