package io.mhmtonrn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mhmtonrn.data.CrawlDto;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
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
        log.info("kayit sayisi:{}", records.size());
        for (SinkRecord record : records) {
            log.error("Topic: {}, Partition: {}, Offset: {}, Value: {}\n", record.topic(), record.kafkaPartition(), record.kafkaOffset(), record.value());
            log.error("type of object {}", record.value().getClass().getName());
            try {
                CrawlDto crawlDto = objectMapper.readValue(record.value().toString(), CrawlDto.class);
                log.error("{}", crawlDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }


        }
    }

    @Override
    public void stop() {
        System.out.println("Task durduruluyor...");
    }

    @Override
    public String version() {
        return "1.0";
    }
}