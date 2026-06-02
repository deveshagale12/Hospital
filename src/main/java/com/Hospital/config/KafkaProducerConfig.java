package com.Hospital.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class KafkaProducerConfig {

    @Value("${KAFKA_BOOTSTRAP_SERVERS}")
    private String bootstrapServers;

    @Value("${KAFKA_USERNAME}")
    private String username;

    @Value("${KAFKA_PASSWORD}")
    private String password;

    @Value("${KAFKA_CA_CERT}")
    private String caCert;

    @Bean
    public ProducerFactory<String, String> producerFactory() throws Exception {

        File caFile = File.createTempFile("aiven-ca", ".pem");

        try (FileWriter writer = new FileWriter(caFile)) {
            writer.write(caCert.replace("\\n", "\n"));
        }

        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        config.put("security.protocol", "SASL_SSL");
        config.put("sasl.mechanism", "SCRAM-SHA-256");
        config.put(
                "sasl.jaas.config",
                "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""
                        + username + "\" password=\"" + password + "\";"
        );

        config.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "PEM");
        config.put(SslConfigs.SSL_TRUSTSTORE_CERTIFICATES_CONFIG, caCert.replace("\\n", "\n"));

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() throws Exception {
        return new KafkaTemplate<>(producerFactory());
    }
}