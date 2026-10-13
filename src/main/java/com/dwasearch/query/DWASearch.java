package com.dwasearch.query;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.query.service.DWAData;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DWASearch {
    public static void main(String[] args) {
        final Path dataPath = Paths.get("data.json");
        //检查命令行参数
        if (args.length != 2) {
            System.err.println("命令行参数数量错误，应为2");
            System.exit(1);
        }

        DataSnapshot datasnapshot;
        try {
            datasnapshot = new DWAData(dataPath).readData();
        }catch (IOException e){
            System.err.println("无法读取数据文件：" + e.getMessage());
            System.exit(2);
        }
    }
}
