package com.zbwfisher.async.pool;


import com.zbwfisher.async.core.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * <p>
 * 
 *
 *
 * </p>
 * @author	woter
 * @date	2016-7-28 下午2:08:30
 * @version      
 */
public abstract class AsyncRunnable extends AsyncTask implements AsyncPoolCallable<Void>{
    
    private static Logger logger = LoggerFactory.getLogger(AsyncRunnable.class);
    
    public AsyncRunnable(){};
    
    public AsyncRunnable(Map<String,Object> dataMap){
	this.dataMap = dataMap;
    }
    
    public abstract void doAsync(Map<String,Object> dataMap);
    
    public Void call(){
	run();
	return null;
    }
    
    public void run(){
	try{
	    doAsync(dataMap);
	}catch(Throwable e){
	    logger.error("async runnable invoke error",e);
	}
    }
    
}
 