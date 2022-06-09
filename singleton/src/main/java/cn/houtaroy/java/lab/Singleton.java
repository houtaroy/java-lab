package cn.houtaroy.java.lab;

/**
 * @author Houtaroy
 */
public class Singleton {
  private static volatile Singleton uniqueSingleton;

  private Singleton() {
  }

  public Singleton getInstance() {
    if (null == uniqueSingleton) {
      synchronized (Singleton.class) {
        if (null == uniqueSingleton) {
          uniqueSingleton = new Singleton();   // error
        }
      }
    }
    return uniqueSingleton;
  }
}
