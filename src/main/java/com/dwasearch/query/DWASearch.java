package com.dwasearch.query;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.query.exception.InvalidDataException;
import com.dwasearch.query.util.DWAData;
import com.dwasearch.query.util.SnapshotValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
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
            System.err.println("命令行参数错误：" + e.getMessage());
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

        //按行读取input.txt中的命令，并交给CommandProcessor类处理
        //从中获取到应该输出的内容并调用CoreModule中的方法完成输出

    }
    void checkArgs (String[] args){
        if (args.length != 2) {
            throw new IllegalArgumentException("命令行参数数量错误，应为2");
        }
        for (String arg : args) {
            if (!arg.endsWith(".txt")){
                throw new IllegalArgumentException("命令行参数中的文件类型错误，应为.txt文件");
            }
        }
    }
}
