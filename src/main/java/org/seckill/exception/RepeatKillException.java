package org.seckill.exception;

/**
 * @Author: Roger
 * @Date: 2018/7/31 20:43
 * 重复秒杀异常(运行期异常)
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
