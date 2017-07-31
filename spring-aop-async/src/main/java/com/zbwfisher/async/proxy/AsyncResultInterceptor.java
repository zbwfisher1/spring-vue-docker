package com.zbwfisher.async.proxy;


import com.zbwfisher.async.constant.AsyncConstant;
import com.zbwfisher.async.exception.AsyncException;
import com.zbwfisher.async.exception.AsyncTimeoutException;
import com.zbwfisher.async.pool.AsyncFutureTask;
import com.zbwfisher.async.util.CommonUtil;
import com.zbwfisher.async.util.ReflectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * 
 * 
 * </p>
 * 
 * @author woter
 * @date 2016-3-23 下午7:46:03
 * @version
 */
@SuppressWarnings("all")
public class AsyncResultInterceptor implements MethodInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AsyncResultInterceptor.class);

    private boolean runed;

    private AsyncFutureTask future;

    private long timeout;

    public AsyncResultInterceptor(AsyncFutureTask future, long timeout) {
	this.future = future;
	this.timeout = timeout;
    }

    private Object loadFuture() throws Throwable {
	long startRunTime = System.currentTimeMillis();
	try {
	    if (timeout <= 0) {
		return future.get();
	    } else {
		return future.get(timeout, TimeUnit.MILLISECONDS);
	    }
	}catch (TimeoutException e) {
	    future.cancel(true);
	    throw new AsyncTimeoutException("future invoke timeoutException",e);
	} catch (InterruptedException e) {
	    throw new AsyncException("funture invoke interruptedException",e);
	}catch (Exception e) {
	    throw getThrowableCause(e);
	} finally {
	    if (logger.isDebugEnabled() && !runed) {
		logger.debug("{} invoking time:{} timeout:{},load time:{}", future, (future.getEndTime() - future.getStartTime()), timeout, (System.currentTimeMillis() - startRunTime));
	    }
	    runed = true;
	}
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
	if (AsyncConstant.ASYNC_DEFAULT_TRACE_LOG){
	    logger.debug("start call future:{},object:{} method:{}",future,object.getClass().getName(), CommonUtil.buildMethod(method));
	}
	object = loadFuture();
	if (object != null) {
	    return ReflectionHelper.invoke(object, args,method);
	}
	return null;
    }
    
    private Throwable getThrowableCause(Throwable e){
	if(e == null || e.getCause() == null){
	    return e; 
	}else{
	    Throwable throwable = getThrowableCause(e.getCause());
	    if(throwable == null){
		return e;
	    }else{
		return throwable;
	    }
	}
    }
}
