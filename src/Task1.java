import java.util.concurrent.Callable;
public class Task1 implements Callable<Integer> {
    int value;
    String name;
    public Task1(String name, int value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        for (int i = 0; i < 4; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(this + " interrupted");
                return null;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(this + " sleep interrupted");
                return null;
            }
            result += i + value;
        }
        System.out.println(this +" final result: "+result);
        return result;
    }
    @Override
    public String toString() {
        return "TimerCallable[name=" + name + "]";
    }
}
