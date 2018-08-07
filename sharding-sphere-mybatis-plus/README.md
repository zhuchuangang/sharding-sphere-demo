sharding-jdbc集成mybatis-plus，使用的mybatis-plus类库提供的分布式ID，具体的类为com.baomidou.mybatisplus.toolkit.Sequence，Sequence类出自开源项目echo/sequence，项目地址为 https://gitee.com/yu120/sequence  

高效GUID产生算法(sequence),基于Snowflake实现64位自增ID算法。

mybatis-plus配置ID类型为2，mybatis-plus会自动填充分布式ID，配置如下：
```
# mybatis配置
mybatis-plus:
  #实体扫描，多个package用逗号或者分号分隔
  typeAliasesPackage: com.szss.shardingjdbc.demo.domain
  global-config:
    #主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
    id-type: 2
    #字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
    field-strategy: 2
    #驼峰下划线转换
    db-column-underline: true
    #刷新mapper 调试神器
    #refresh-mapper: true
    #数据库大写下划线转换
    capital-mode: true
  configuration:
    map-underscore-to-camel-case: true
    cache-enabled: false
    #配置JdbcTypeForNull
    jdbc-type-for-null: 'null'
```


com.baomidou.mybatisplus.MybatisDefaultParameterHandler类的populateKeys方法会根据id类型进行id填充。代码如下：
```
   /**
     * <p>
     * 自定义元对象填充控制器
     * </p>
     *
     * @param metaObjectHandler 元数据填充处理器
     * @param tableInfo         数据库表反射信息
     * @param ms                MappedStatement
     * @param parameterObject   插入数据库对象
     * @return Object
     */
    protected static Object populateKeys(MetaObjectHandler metaObjectHandler, TableInfo tableInfo,
                                         MappedStatement ms, Object parameterObject) {
        if (null == tableInfo || StringUtils.isEmpty(tableInfo.getKeyProperty()) || null == tableInfo.getIdType()) {
            /* 不处理 */
            return parameterObject;
        }
        /* 自定义元对象填充控制器 */
        MetaObject metaObject = ms.getConfiguration().newMetaObject(parameterObject);
        if (ms.getSqlCommandType() == SqlCommandType.INSERT) {
            if (tableInfo.getIdType().getKey() >= 2) {
                Object idValue = metaObject.getValue(tableInfo.getKeyProperty());
                /* 自定义 ID */
                if (StringUtils.checkValNull(idValue)) {
                    if (tableInfo.getIdType() == IdType.ID_WORKER) {
                        metaObject.setValue(tableInfo.getKeyProperty(), IdWorker.getId());
                    } else if (tableInfo.getIdType() == IdType.ID_WORKER_STR) {
                        metaObject.setValue(tableInfo.getKeyProperty(), IdWorker.getIdStr());
                    } else if (tableInfo.getIdType() == IdType.UUID) {
                        metaObject.setValue(tableInfo.getKeyProperty(), IdWorker.get32UUID());
                    }
                }
            }
            // 插入填充
            if (metaObjectHandler.openInsertFill()) {
                metaObjectHandler.insertFill(metaObject);
            }
        } else if (ms.getSqlCommandType() == SqlCommandType.UPDATE && metaObjectHandler.openUpdateFill()) {
            // 更新填充
            metaObjectHandler.updateFill(metaObject);
        }
        return metaObject.getOriginalObject();
    }
```

demo地址： https://github.com/zhuchuangang/sharding-sphere-demo/tree/master/sharding-sphere-mybatis-plus

