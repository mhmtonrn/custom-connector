package io.mhmtonrn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MySinkTask extends SinkTask {
    private static final Logger log = LoggerFactory.getLogger(MySinkTask.class);
    private ObjectMapper objectMapper;

    @Override
    public void start(Map<String, String> props) {

        System.out.println("Task başlatılıyor...");
        System.out.println("\n\n\n\n\n\n\n\n\nProperties : " + props);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        for (SinkRecord record : records) {
            System.out.printf("Topic: %s, Partition: %d, Offset: %d, Value: %s%n", record.topic(), record.kafkaPartition(), record.kafkaOffset(), record.value());
            try {
                HashMap<String, Object> hashMap = objectMapper.readValue((String) record.value(), HashMap.class);

                for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                    log.error("Key: {} Value: {}", entry.getKey(), entry.getValue());
                }

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void stop() {
        // Task durdurma işlemleri
        System.out.println("Task durduruluyor...");
    }

    @Override
    public String version() {
        return "1.0";
    }
}