package com.zbwfisher.async.proxy;


/**
 * <p>
 * 
 *
 *
 * </p>
 * @author	woter 
 * @date	2016-3-23 上午10:58:31
 * @version      
 */
public interface AsyncProxy {
    
    public Object buildProxy(Object target, boolean all);
    
    public Object buildProxy(Object target, long timeout, boolean all);

}
 