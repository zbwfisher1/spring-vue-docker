package com.zbwfisher.async.exception;

/**
 * <p>
 * 
 * 
 *
 * </p>
 * @author	woter 
 * @date	2016-3-23 下午7:50:44
 * @version      
 */
public class AsyncException extends RuntimeException{
    
    private static final long serialVersionUID = -2128834565845654572L;

    public AsyncException(){
        super();
    }

    public AsyncException(String message, Throwable cause){
        super(message, cause);
    }

    public AsyncException(String message){
        super(message);
    }

    public AsyncException(Throwable cause){
        super(cause);
    }
    
}
 