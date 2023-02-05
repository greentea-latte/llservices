package com.lila.app.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
// This is the Patient model where attributes are defined
// The attributes are patient_id which is a database primary key
// and fullName and email of the patient
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_sequence"
    )
    private Integer id;
    private String fullName;
    private String email;
}
