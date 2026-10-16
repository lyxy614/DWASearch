package com.dwasearch.query.util;

import com.dwasearch.model.DataSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.dwasearch.query.TestData.loadDataSnapshot;

class CoreModuleTest {

    @Test
    @DisplayName("正常输入下应按照规定的格式输出players到output.txt")
    void outputPlayers_normal_format() throws IOException {
        DataSnapshot dataSnapshot = loadDataSnapshot("data.json");
        Path testPath = Files.createTempFile("normalOutput", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(testPath, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            CoreModule.outputPlayers(dataSnapshot, writer);
        }

        List<String> lines = Files.readAllLines(testPath, StandardCharsets.UTF_8);
        assertFalse(lines.isEmpty());

        //逐行检查格式
        for (int i = 0; i < lines.size(); i += 4) {
            assertTrue(lines.get(i).startsWith("Full Name:"),
                    "第 " + (i + 1) + " 行应以 Full Name: 开头，实际: " + lines.get(i));
            assertTrue(lines.get(i + 1).startsWith("Gender:"),
                    "第 " + (i + 2) + " 行应以 Gender: 开头，实际: " + lines.get(i + 1));
            assertTrue(lines.get(i + 2).startsWith("Country:"),
                    "第 " + (i + 3) + " 行应以 Country: 开头，实际: " + lines.get(i + 2));
            assertEquals("-----", lines.get(i + 3),
                    "第 " + (i + 4) + " 行应为分隔线，实际: " + lines.get(i + 3));
        }

        // 4. 最后一行是分隔线
        assertEquals("-----", lines.get(lines.size() - 1),
                "最后一行应为分隔线");

        // 5. 字段名后面有内容，没有多余空格（样例要求冒号后紧跟值）
        String firstFullName = lines.get(0);
        assertTrue(firstFullName.length() > "Full Name:".length(),
                "Full Name 字段不应为空");
        assertFalse(firstFullName.contains(": "),
                "冒号后不应有空格");

        Files.deleteIfExists(testPath);
    }


}