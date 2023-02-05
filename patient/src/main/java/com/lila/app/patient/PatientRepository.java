package com.lila.app.patient;

import org.springframework.data.jpa.repository.JpaRepository;
// Here JPA is configured to enable management of relational database data using Java objects
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
