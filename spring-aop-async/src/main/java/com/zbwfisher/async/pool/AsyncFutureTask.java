package com.zbwfisher.async.pool;


import com.zbwfisher.async.bean.Profiler;
import com.zbwfisher.async.bean.SyncResult;
import com.zbwfisher.async.core.AsyncFutureCallback;
import com.zbwfisher.async.core.AsyncProfiler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * 
 * 
 * 
 * </p>
 * 
 * @author woter
 * @date 2016-3-23 下午2:16:04
 * @version
 */
public class AsyncFutureTask<V> extends FutureTask<V> {
    

    private long startTime = 0;

    private long endTime = 0;
    
    private Profiler profiler ;

    private AsyncFutureCallback<V> futureCallback;
    
    private SyncResult<V> sync;
    
    public AsyncFutureTask(AsyncPoolCallable<V> callable) {
	super(callable);
    }

    public AsyncFutureTask(AsyncPoolCallable<V> callable, AsyncFutureCallback<V> futureCallback) {
	super(callable);
	if (futureCallback != null) {
	    this.futureCallback = futureCallback;
	}
	this.profiler = AsyncProfiler.get();
	AsyncProfiler.release();
    }

    @Override
    protected void done() {
	endTime = System.currentTimeMillis();
	if(profiler.getNumber() == 1){
	    AsyncProfiler.release();
	}else{
	    AsyncProfiler.getAndSet(profiler.decrementAndGet()).getNumber();
	}
	
	if (futureCallback != null) {
	    try {
		futureCallback.onSuccess(this.get());
	    } catch (Throwable e) {
		futureCallback.onFailure(e);
	    }
	}
    }

    @Override
    public void run() {
	startTime = System.currentTimeMillis();
	AsyncProfiler.getAndSet(profiler.getAndIncrement());
	super.run();
    }
    
    @Override
    public V get() throws InterruptedException, ExecutionException {
	if(sync != null ){
	    if(sync.isSucceed()){
		return sync.getValue();
	    }else{
		throw new ExecutionException(sync.getThrowable());
	    }
	}
	
	return super.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
	if(sync != null ){
	    if(sync.isSucceed()){
		return sync.getValue();
	    }else{
		throw new ExecutionException(sync.getThrowable());
	    }
	}
	return super.get(timeout, unit);
    }
    

    public long getStartTime() {
	return startTime;
    }

    public void setStartTime(long startTime) {
	this.startTime = startTime;
    }

    public long getEndTime() {
	return endTime;
    }

    public void setEndTime(long endTime) {
	this.endTime = endTime;
    }
    
    public Profiler getProfiler() {
        return profiler;
    }

    public void setProfiler(Profiler profiler) {
        this.profiler = profiler;
    }
    
    public SyncResult<V> getSync() {
        return sync;
    }
    
    public void setSync(SyncResult<V> sync) {
        this.sync = sync;
    }
}
