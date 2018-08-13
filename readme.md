# 数据库问题

```bash
# 连接数据库
mysql -uroot -proot -P3306 -hlocalhost

source F:\睿云实验室\王剑锋\2018.7.12_VDB实验第二阶段\第3阶段\VDB4J\src\main\sql\db.sql
```

sql文件里面的内容为：

```sql
DROP DATABASE IF EXISTS vdb;

CREATE DATABASE IF NOT EXISTS vdb DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use vdb;

CREATE TABLE IF NOT EXISTS SetupOutput_H(
  i int not null PRIMARY KEY ,
	Hi blob
); 
```


当q=500时候，出现如下的问题

```bash
com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'Hi' at row 1
```


修改sql文件里面的内容为：

```sql
DROP DATABASE IF EXISTS vdb;

CREATE DATABASE IF NOT EXISTS vdb DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use vdb;

CREATE TABLE IF NOT EXISTS SetupOutput_H(
  	i int not null,
  	j int not null,
	H_ij blob,
	primary key(i,j)
); 
```

# 正确性验证

## Setup

在命令行中运行如下的指令

```bash
# 连接数据库
mysql -uroot -proot -P3306 -hlocalhost

source F:\睿云实验室\王剑锋\2018.7.12_VDB实验第二阶段\第3阶段\VDB4J\src\main\sql\db.sql
```

运行VDB_Setup的main函数

```bash
#linux
linux: mvn exec:java -Dexec.mainClass="com.zhong.VDB_Setup" -Dexec.args="5 2"

#windows
windows: mvn exec:java -D"exec.mainClass"="com.zhong.VDB_Setup" -D"exec.args"="5 2"
```


## Update

运行VDB_Update的main函数

```bash
#linux
linux: mvn exec:java -Dexec.mainClass="com.zhong.VDB_Update" -Dexec.args="5 2"

#windows
windows: mvn exec:java -D"exec.mainClass"="com.zhong.VDB_Update" -D"exec.args"=""
```

输出如下的内容

```bash
0
705174584455020495879460947914775963667628000543
```

其中0代表当前的T为0
705174584455020495879460947914775963667628000543为新的元素


## query

运行VDB_Query的main函数


```bash
#linux
linux: mvn exec:java -Dexec.mainClass="com.zhong.VDB_Query" -Dexec.args="0 5"

#windows
windows: mvn exec:java -D"exec.mainClass"="com.zhong.VDB_Query" -D"exec.args"="0 5"
```

输出

```bash
1653489015177421786040698304679830223482273581487786852162195848847627719619831434105629104869126167921348748107071668755058349127339040132871896113341072,3363270268778715147048417617339662786697392457799003768310570405933826800388148524732623704452494542055123103766629955275085298659327024510424429003768407,0
1
705174584455020495879460947914775963667628000543
```

最后一行输出705174584455020495879460947914775963667628000543说明查询正确

## verify


运行VDB_Verify的main函数

```bash
#linux
linux: mvn exec:java -Dexec.mainClass="com.zhong.VDB_Verify" -Dexec.args="0 5"

#windows
windows: mvn exec:java -D"exec.mainClass"="com.zhong.VDB_Verify" -D"exec.args"="0 5"
```

# 测试时间

## Setup

在命令行中运行如下的指令

```bash
# 连接数据库
mysql -uroot -proot -P3306 -hlocalhost

source F:\睿云实验室\王剑锋\2018.7.12_VDB实验第二阶段\第3阶段\VDB4J\src\main\sql\db.sql

source /root/VDB4J/src/main/sql/db.sql
```

运行VDB_Setup的main函数

```bash
#linux
linux: mvn exec:java -Dexec.mainClass="com.zhong.VDB_Setup" -Dexec.args="6549 2"

#windows
windows: mvn exec:java -D"exec.mainClass"="com.zhong.VDB_Setup" -D"exec.args"="6549 2"
```


## Update


```bash
mvn test -Dtest=VDB_UpdateTest#computeUpdateTime
```


## Query

```bash
mvn test -Dtest=VDB_QueryTest#computeQueryTime
```


## verify


```bash
mvn test -Dtest=VDB_VerifyTest#computerVerifyTime
```































