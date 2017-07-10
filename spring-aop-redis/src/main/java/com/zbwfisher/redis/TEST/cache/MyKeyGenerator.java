package com.zbwfisher.redis.TEST.cache;


import com.zbwfisher.redis.common.constants.CacheScope;
import com.zbwfisher.redis.common.parser.IKeyGenerator;
import com.zbwfisher.redis.common.parser.IUserKeyGenerator;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-05-22 14:05
 */
public class MyKeyGenerator extends IKeyGenerator {
    @Override
    public IUserKeyGenerator getUserKeyGenerator() {
        return null;
    }

    @Override
    public String buildKey(String key, CacheScope scope, Class<?>[] parameterTypes, Object[] arguments) {
        return "myKey_"+arguments[0];
    }
}
