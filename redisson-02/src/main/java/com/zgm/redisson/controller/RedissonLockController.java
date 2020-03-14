package com.zgm.redisson.controller;

import com.zgm.redisson.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/redisson")
public class RedissonLockController {

    @GetMapping("/test")
    public void lock() {
        String lockKey = "lock-test";
        try {
            // 加锁，设置超时时间2s
            RedisLockUtil.lock(lockKey,10,TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(20);
            System.out.println("---------------01已经释放锁");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 释放锁
           RedisLockUtil.unlock(lockKey);
        }

    }

    @GetMapping("/test2")
    public void lock2() {
        String lockKey = "lock-test";
        try {
            // 加锁，设置超时时间2s
            RedisLockUtil.lock(lockKey);
            System.out.println("---------------02已经释放锁");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 释放锁
            RedisLockUtil.unlock(lockKey);
        }

    }
}
