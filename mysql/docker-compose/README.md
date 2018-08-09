# 1 mysql 5.6 主从复制配置

# 环境
```
主机A：192.168.1.1 （Master）
主机B：192.168.1.2 （Slave）
```

# master节点配置
主节点my.cnf配置如下：
```
[mysqld]
#服务端编码
collation-server = utf8_unicode_ci
#设置连接字符集
init-connect='SET NAMES utf8'
#服务端字符集
character-set-server = utf8

log-bin=mysql-bin
#服务器ID
server-id=1
#表示同步的时候ignore的数据库
binlog-ignore-db=information_schema
binlog-ignore-db=cluster
binlog-ignore-db=mysql
#指定需要同步的数据库
binlog-do-db=test
```

启动mysql主节点服务后，在主节点赋予从库权限帐号，允许用户在主库上读取日志，
赋予192.168.1.2也就是Slave机器有File和REPLICATION SLAVE的权限。
登录master节点mysql控制台：

```sql
GRANT FILE ON *.* TO 'root'@'192.168.1.2' IDENTIFIED BY '123456';

GRANT REPLICATION SLAVE ON *.* TO 'root'@'192.168.1.2' IDENTIFIED BY '123456';

FLUSH PRIVILEGES;
```

> 这里使用的仍是 root 用户作为同步的时候使用到的用户，可以自己设定。

重启mysql，登录mysql，显示主库信息:
```sql
show master status;
```
显示内容：
```sql
mysql> show master status;
+------------------+----------+--------------+----------------------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB                 | Executed_Gtid_Set |
+------------------+----------+--------------+----------------------------------+-------------------+
| mysql-bin.000004 |    28125 | ufind_db     | information_schema,cluster,mysql |                   |
+------------------+----------+--------------+----------------------------------+-------------------+
1 row in set (0.00 sec)

```

这里的 File 、Position 是在配置Salve的时候要使用到的，Binlog_Do_DB表示要同步的数据库，
Binlog_Ignore_DB 表示Ignore的数据库，这些都是在配置的时候进行指定的。

> 注意：如果执行这个步骤始终为Empty set(0.00 sec)，那说明前面的my.cnf没配置对。

# 配置slave节点
从节点my.cnf配置如下：
```
[mysqld]
#服务端编码
collation-server = utf8_unicode_ci
#设置连接字符集
init-connect='SET NAMES utf8'
#服务端字符集
character-set-server = utf8

log-bin=mysql-bin
#服务器ID
server-id=2
#表示同步的时候ignore的数据库
binlog-ignore-db=information_schema
binlog-ignore-db=cluster
binlog-ignore-db=mysql
#指定需要同步的数据库
binlog-do-db=test

#slave节点配置
log-slave-updates
slave-skip-errors=all
slave-net-timeout=60
```
重启slave节点，登录slave节点mysql控制台：
```sql
stop slave;  

change master to master_host='192.168.1.1',master_user='root',master_password='123456',master_log_file='mysql-bin.000004', master_log_pos=28125;

start slave; 
```

> 注意：在这里指定Master的信息，master_log_file是在配置Master的时候的File选项， master_log_pos是在配置Master的Position 选项，这里要进行对应。

然后可以通过show slave status; 查看配置的信息：
```sql
mysql > show slave status;
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 192.167.1.1
                  Master_User: root
                  Master_Port: 3306
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000004
          Read_Master_Log_Pos: 28125
               Relay_Log_File: VM_128_194_centos-relay-bin.000004
                Relay_Log_Pos: 26111
        Relay_Master_Log_File: mysql-bin.000004
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB: ufind_db
          Replicate_Ignore_DB: mysql
           Replicate_Do_Table: 
       Replicate_Ignore_Table: 
      Replicate_Wild_Do_Table: 
  Replicate_Wild_Ignore_Table: 
                   Last_Errno: 0
                   Last_Error: 
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 28125
              Relay_Log_Space: 26296
              Until_Condition: None
               Until_Log_File: 
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File: 
           Master_SSL_CA_Path: 
              Master_SSL_Cert: 
            Master_SSL_Cipher: 
               Master_SSL_Key: 
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error: 
               Last_SQL_Errno: 0
               Last_SQL_Error: 
  Replicate_Ignore_Server_Ids: 
             Master_Server_Id: 2
                  Master_UUID: 8ac3066a-9680-11e5-a2ec-5254007529fd
             Master_Info_File: /data/mysqldb/master.info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for the slave I/O thread to update it
           Master_Retry_Count: 86400
                  Master_Bind: 
      Last_IO_Error_Timestamp: 
     Last_SQL_Error_Timestamp: 
               Master_SSL_Crl: 
           Master_SSL_Crlpath: 
           Retrieved_Gtid_Set: 
            Executed_Gtid_Set: 
                Auto_Position: 0
1 row in set (0.00 sec)

ERROR: 
No query specified
```

到此mysql 5.6主从复制搭建完成。

参考：

MySQL5.6 数据库主从（Master/Slave）同步安装与配置详解：
https://blog.csdn.net/xlgen157387/article/details/51331244/