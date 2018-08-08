package com.szss.shardingjdbc.demo.strategy;

import java.util.Collection;

import com.alibaba.fastjson.JSON;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * HashCodePreciseShardingAlgorithm 哈希取模精确分片算法
 * 
 * @author naah
 * @date 2018-07-26 下午3:33
 * @desc
 */
@Slf4j
public class HashCodePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        log.info("availableTargetNames:" + JSON.toJSONString(availableTargetNames) + ",preciseShardingValue:"
            + JSON.toJSONString(shardingValue));
        // 通过hashcode取模
        int index = (shardingValue.getValue() + "").hashCode() % availableTargetNames.size();
        return (String)availableTargetNames.toArray()[Math.abs(index)];
    }
}
