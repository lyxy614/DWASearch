# 一、模块分工
计划将DWASearch的查询部分分为若干个模块：
DWASearch, DWAData, CoreModule, ...

当前进度：
1. 接收并处理命令行参数(finished)
2. 读取、解析data.json并检查是否成功反序列化(finished)
3. 校验查询所需数据字段是否有缺失(finished)
4. 读取查询命令并输出对应信息

## 1.DWASearch（主入口）
负责程序的总流程。负责接收并处理命令行参数、检查文件路径是否正确、读取input.txt中的内容、
将读取到的命令传递给其他模块处理。

目前已实现：
1. 接收并处理命令行参数

## 2.DWAData（解析类）
数据文件处理类。负责解析data.json

目前已实现：
1. 解析data.json

## 3.SnapshotValidator（校验类）
检查DWAData读到的DataSnapshot内容是否有问题，
字段是否有缺失。

目前已实现。

## 4.CommandProcessor（命令处理类）
将输入的命令集转化为调用输出方法的语句；识别不合法命令并指定Error / N/A输出。

## 5.CoreModule
核心输出逻辑。负责输出players，results和命令不合法时应输出的信息。
并处理需排序数据的输出顺序。

目前已实现（包括测试）：
1. 输出players
2. 输出results