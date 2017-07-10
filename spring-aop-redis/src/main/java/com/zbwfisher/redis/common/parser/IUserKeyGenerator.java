package com.zbwfisher.redis.common.parser;

/**
 * 当前用户信息身份标志
 *
 * @author wanghaobin
 * @description
 * @date 2017年5月18日
 * @since 1.7
 */
public interface IUserKeyGenerator {
    public String getCurrentUserAccount();
}
