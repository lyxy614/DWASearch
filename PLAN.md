# 一、模块分工
计划将DWASearch的查询部分分为若干个模块：
DWASearch, DWAData, CoreModule, ...

当前进度：
1. 接收并处理命令行参数(finished)
2. 读取并解析data.json并处理


## 1.DWASearch（主入口）
核心业务逻辑。负责接收并处理命令行参数、检查文件路径是否正确、读取input.txt中的内容、
将读取到的命令传递给其他模块处理、输出相应的查询内容到output.txt。

### 目前已实现：
1. 接收并处理命令行参数

该类中的方法暂时不写JUnit测试
## 2.DWAData（解析类）
数据文件处理类。负责解析data.json

### 目前已实现：
1. 解析data.json

## 3.SnapshotValidator（校验类）
检查DWAData读到的DataSnapshot内容是否有问题，
字段是否有缺失。

## 4.CommandProcessor（命令处理类）
初始化查询所需的所有合法命令：将命令字符串与查询路径绑定。

## 5.CoreModule
核心输出逻辑。负责输出players，results和命令不合法时应输出的信息。
并处理需排序数据的输出顺序。