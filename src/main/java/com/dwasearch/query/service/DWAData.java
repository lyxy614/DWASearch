package com.dwasearch.query.service;

import com.dwasearch.model.DataSnapshot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;

public class DWAData {

    private final Path dataPath;

    public DWAData(Path dataPath) {
        this.dataPath = dataPath;
    }

    public DataSnapshot readData() throws IOException {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();

        return mapper.readValue(dataPath.toFile(), DataSnapshot.class);
    }
}
