/**
 *
 */
package com.zbwfisher.redis.common.service;


import com.zbwfisher.redis.common.entity.CacheBean;
import com.zbwfisher.redis.common.vo.CacheTree;

import java.util.List;


/**
 * 解决问题：
 *
 * @author Ace
 * @version 1.0
 * @date 2017年5月3日
 * @since 1.7
 */
public interface ICacheManager {
    public void removeAll();

    public void remove(String key);

    public void remove(List<CacheBean> caches);

    public void removeByPre(String pre);

    public List<CacheTree> getAll();

    public List<CacheTree> getByPre(String pre);

    public void update(String key, int hour);

    public void update(List<CacheBean> caches, int hour);

    public String get(String key);
}
