保存表数据的时候，分片主键不要出现在insert语句中，否则可能会出现分片主键无法生成的情况。例如：
```sql
insert into t_user(username,password) values(#{user.username},#{user.password})
```
user_id为t_user表的分片主键，insert语句中不出现user_id，sharding jdbc会自动添加user_id字段，并填充分布式ID