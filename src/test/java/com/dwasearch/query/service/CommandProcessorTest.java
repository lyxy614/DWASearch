package com.dwasearch.query.service;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.query.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {

    private String executeAndGetOutput(CoreModule coreModule, List<String> commands) throws IOException {
        StringWriter sw = new StringWriter();
        try (BufferedWriter bw = new BufferedWriter(sw)) {
            CommandProcessor processor = new CommandProcessor(coreModule, bw);
            processor.process(commands);
        }
        return sw.toString();
    }
    @Test
    @DisplayName("单行、多行正常的不同命令输出")
    void process_normal_multiLines() throws IOException {
        List<String> commandList = List.of(
                "players",
                "result women 3m springboard",
                "result men 10m platform",
                "result men 10m synchronised"
        );
        List<String> outputList = new ArrayList<>();
        DataSnapshot dataSnapshot = TestData.loadDataSnapshot("data.json");
        CoreModule coreModule = new CoreModule(dataSnapshot);

        //下面逐个检查每一条命令的输出是否正确
        //与标准数据样例比较
        String standardOutput1 = TestData.loadResourceAsString("standard_players.txt");
        String actualOutput1 = executeAndGetOutput(coreModule, List.of(commandList.getFirst()));
        assertEquals(standardOutput1, actualOutput1);
        outputList.add(actualOutput1);

        String standardOutput2 = TestData.loadResourceAsString("standard_Women's 3m Springboard.txt");
        String actualOutput2 = executeAndGetOutput(coreModule, List.of(commandList.get(1)));
        assertEquals(standardOutput2, actualOutput2);
        outputList.add(actualOutput2);

        String standardOutput3 = TestData.loadResourceAsString("standard_Men's 10m Platform.txt");
        String actualOutput3 = executeAndGetOutput(coreModule, List.of(commandList.get(2)));
        assertEquals(standardOutput3, actualOutput3);
        outputList.add(actualOutput3);

        String standardOutput4 = TestData.loadResourceAsString("standard_Men's 10m Synchronised.txt");
        String actualOutput4 = executeAndGetOutput(coreModule, List.of(commandList.get(3)));
        assertEquals(standardOutput4, actualOutput4);
        outputList.add(actualOutput4);

        assertEquals(commandList.size(), outputList.size());

        //合并上述逐条命令输出的结果，并将合并后的内容与多条命令一起执行后输出的结果比较
        String total = outputList.getFirst() + outputList.get(1)
                + outputList.get(2) + outputList.get(3);

        StringWriter totalSw = new StringWriter();
        try (BufferedWriter bw = new BufferedWriter(totalSw)) {
            CommandProcessor commandProcessor = new CommandProcessor(coreModule, bw);
            commandProcessor.process(commandList);
        }
        assertEquals(total, totalSw.toString(), "多条命令一起输出的结果与单独输出后再拼接的不一致");
    }

}