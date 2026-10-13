package com.dwasearch.query.service;

import com.dwasearch.model.DataSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DWADataTest {
    DWAData dwaData;

    @Test
    @DisplayName("读取data.json应正确加载快照")
    void readData() throws IOException {
        this.dwaData = new DWAData(Paths.get("data.json"));
        DataSnapshot dataSnapshot = dwaData.readData();

        //检查dataSnapshot下的字段是否为空
        assertNotNull(dataSnapshot);
        assertNotNull(dataSnapshot.getSource());
        assertNotNull(dataSnapshot.getCountries());
        assertNotNull(dataSnapshot.getCompetitions());

        assertFalse(dataSnapshot.getCompetitions().isEmpty());
        assertFalse(dataSnapshot.getCountries().isEmpty());
    }

    @Test
    @DisplayName("读取错误文件路径时应抛出IOException")
    void readDataByWrongPath() throws IOException {
        this.dwaData = new DWAData(Paths.get("dwa.json"));
        assertThrows(IOException.class, () -> this.dwaData.readData());
    }

    @Test
    @DisplayName("读取的json结构错误时，应抛出IOException")
    void readDataByWrongJson() throws IOException {
        Path tmp = Files.createTempFile("dwa-mismatch", ".json");
        Files.writeString(tmp, """
        {
          "source": { "event": "x", "url": "x", "fetchedAt": "2026-10-01T06:30:00Z" },
          "players": "not-an-array",
          "results": {}
        }
        """);

        this.dwaData = new DWAData(tmp);
        assertThrows(IOException.class, () -> this.dwaData.readData());

        Files.deleteIfExists(tmp);
    }
}