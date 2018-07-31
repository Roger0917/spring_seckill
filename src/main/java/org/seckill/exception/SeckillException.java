package org.seckill.exception;

/**
 * @Author: Roger
 * @Date: 2018/7/31 20:49
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
