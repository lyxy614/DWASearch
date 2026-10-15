package com.dwasearch.query;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.query.exception.InvalidDataException;
import com.dwasearch.query.util.DWAData;
import com.dwasearch.query.util.SnapshotValidator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DWASearch {
    public static void main(String[] args) {
        final Path dataPath = Paths.get("data.json");
        DWASearch dwaSearch = new DWASearch();
        //检查命令行参数
        try {
            dwaSearch.checkArgs(args);
        }catch (IllegalArgumentException e) {
            System.exit(1);
        }

        //读取并解析数据文件
        DataSnapshot dataSnapshot = null;
        try {
            dataSnapshot = new DWAData().readData(dataPath);
        }catch (IOException e){
            System.err.println("无法读取数据文件：" + e.getMessage());
            System.exit(2);
        }

        //检查数据文件内容是否有缺失
        SnapshotValidator snapshotValidator = new SnapshotValidator();
        try {
            snapshotValidator.validate(dataSnapshot);
        }catch (InvalidDataException e) {
            System.err.println("data.json数据不完整：" + e.getMessage());
        }

    }
    public void checkArgs (String[] args){
        if (args.length != 2) {
            System.err.println("命令行参数数量错误，应为2");
            throw new IllegalArgumentException();
        }
    }
}
