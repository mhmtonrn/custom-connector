package io.mhmtonrn;

import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.apache.kafka.common.config.ConfigDef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySinkConnector extends SinkConnector {
    private Map<String, String> config;

    @Override
    public void start(Map<String, String> props) {
        this.config = props;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return MySinkTask.class; // Task sınıfına işaret ediyor
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> configs = new ArrayList<>();
        for (int i = 0; i < maxTasks; i++) {
            Map<String, String> taskConfig = new HashMap<>(config);
            taskConfig.put("task.id", String.valueOf(i)); // Her task'a bir ID veriyoruz
            configs.add(taskConfig);
        }
        return configs;
    }

    @Override
    public void stop() {
        // Connector durdurulurken yapılacak işlemler
    }

    @Override
    public ConfigDef config() {
        return new ConfigDef()
                .define("custom.setting", ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, "Custom setting description");
    }

    @Override
    public String version() {
        return "1.0";
    }
}