package com.lila.app.verification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
// This is the Verification model where attributes are defined
// The attributes are verification_id which is a database primary key
// and verification type and patientId
public class Verification {
    @Id
    @SequenceGenerator(
            name = "verification_id_sequence",
            sequenceName = "verification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "verification_id_sequence"
    )
    private Integer id;
    private String type;
    private Integer patientId;
}
