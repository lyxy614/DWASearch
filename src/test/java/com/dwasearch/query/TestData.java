package com.dwasearch.query;

import com.dwasearch.model.DataSnapshot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//此类仅用于测试：从test/resource下的json文件初始化DataSnapshot对象
public class TestData {
    public static final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    public static DataSnapshot loadDataSnapshot(String fileName) throws IOException {
        InputStream in = TestData.class.getClassLoader().getResourceAsStream(fileName);
        if (in == null) {
            throw new FileNotFoundException("测试资源不存在: " + fileName);
        }
        try (InputStream stream = in) {
            return mapper.readValue(stream, DataSnapshot.class);
        }
    }

    public static String loadResourceAsString(String fileName) throws IOException {
        try (InputStream in = TestData.class.getClassLoader().getResourceAsStream(fileName)) {
            if (in == null) {
                throw new FileNotFoundException("测试资源不存在: " + fileName);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
