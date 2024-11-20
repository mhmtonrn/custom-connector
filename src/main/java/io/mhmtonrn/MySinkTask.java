package io.mhmtonrn;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.util.Collection;
import java.util.Map;

public class MySinkTask extends SinkTask {
    @Override
    public void start(Map<String, String> props) {
        // Task başlatma mantığı
        System.out.println("Task başlatılıyor...");
        System.out.println("\n\n\n\n\n\n\n\n\nProperties : " + props);

    }

    @Override
    public void put(Collection<SinkRecord> records) {
        // Gelen veriyi işleme
        for (SinkRecord record : records) {
            System.out.printf("Topic: %s, Partition: %d, Offset: %d, Value: %s%n",
                    record.topic(),
                    record.kafkaPartition(),
                    record.kafkaOffset(),
                    record.value());
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