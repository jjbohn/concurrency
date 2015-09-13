import java.util.Random;

class Chopstick {
  private int id;
  public Chopstick(int id) { this.id = id; }
  public int getId() { return id; }
}

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;

  public Philosopher(Chopstick left, Chopstick right) {
    this.left = left; this.right = right;
    random = new Random();
  }

  public void run() {
    try {
      while(true) {
        Thread.sleep(random.nextInt(1000));
        synchronized(left) {
          synchronized(right) {
            Thread.sleep(random.nextInt(1000));
          }
        }
      }
    } catch(InterruptedException e) {};
  }
}
