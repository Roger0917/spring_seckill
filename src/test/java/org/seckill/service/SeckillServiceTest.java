package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Roger
 * @Date: 2018/8/1 11:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    //测试代码完整逻辑,可重复执行
    @Test
    public void exportSeckillUrl() {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 15747968522L;
            String md5 = exposer.getMd5();
            try{
                SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",execution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else{
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }

        /*
        exposer=Exposer
        {exposed=true,
        md5='6eb4bfc328f9faff3bdf015a79e10fab',
        seckillId=1000, now=0, start=0, end=0}*/
    }



   /* @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 15747968522L;
        String md5 = "6eb4bfc328f9faff3bdf015a79e10fab";

    }*/
   @Test
   public void executeSeckillProcedure(){
       long seckillId = 1001;
       long phone;
       Exposer exposer = seckillService.exportSeckillUrl(seckillId);
       if (exposer.isExposed()){
           String md5 = exposer.getMd5();
           SeckillExecution seckillExecution = seckillService.executeSeckillProducer(seckillId,phone,md5);
           logger.info(seckillExecution.getStateInfo());
       }

   }
}