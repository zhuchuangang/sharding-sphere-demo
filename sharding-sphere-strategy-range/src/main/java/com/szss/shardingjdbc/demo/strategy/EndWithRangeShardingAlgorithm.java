package com.szss.shardingjdbc.demo.strategy;

import java.util.Collection;
import java.util.HashSet;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;

import io.shardingsphere.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 鼠笑天
 * @date 2018/8/5
 */
@Slf4j
public class EndWithRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
        RangeShardingValue<Long> shardingValue) {
        log.info("Range collection:" + JSON.toJSONString(availableTargetNames) + ",rangeShardingValue:"
            + JSON.toJSONString(shardingValue));
        Collection<String> collect = new HashSet<>();
        Range<Long> valueRange = shardingValue.getValueRange();
        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % availableTargetNames.size() + "")) {
                    collect.add(each);
                }
            }
        }
        return collect;
    }
}
