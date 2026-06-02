package com.Hospital.service;
import com.Hospital.entity.Patient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PatientKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PatientKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRegistrationSuccess(Patient patient) {
        String message = patient.getEmail() + "," + patient.getFullName();

        kafkaTemplate.send("registration-success", message);
    }
}