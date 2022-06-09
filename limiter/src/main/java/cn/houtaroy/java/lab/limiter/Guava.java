package cn.houtaroy.java.lab.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author Houtaroy
 */
public class Guava {
  public static void main(String[] args) {
    normal();
    preheat();
  }

  public static void normal() {
    System.out.println("普通定时器");
    RateLimiter rateLimiter = RateLimiter.create(5);
    for (int i = 0; i < 10; i++) {
      double sleepingTime = rateLimiter.acquire(1);
      System.out.printf("get 1 tokens: %ss%n", sleepingTime);
    }
  }

  public static void preheat() {
    System.out.println("预热定时器");
    RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);
    for (int i = 0; i < 20; i++) {
      double sleepingTime = rateLimiter.acquire(1);
      System.out.printf("get 1 tokens: %sds%n", sleepingTime);
    }
  }
}
