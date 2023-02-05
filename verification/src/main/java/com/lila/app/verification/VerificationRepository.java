package com.lila.app.verification;

import org.springframework.data.jpa.repository.JpaRepository;
// Here JPA is configured to enable management of relational database data using Java objects
public interface VerificationRepository  extends JpaRepository<Verification, Integer> {
}
