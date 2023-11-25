import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FTask<T> extends FutureTask {
    String name;

    public FTask(Callable<T> callable) {
        super(callable);
        name = "" + callable;
    }

    @Override
    protected void done() {
        T result;
        try {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(this + " interrupted");
                return;
            }
            if (isCancelled()) {
                System.out.println(this + " canceled");
                return;
            }
            result = (T) this.get();
            System.out.println("Task " + this + "done: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FTask [" + name + "]";
    }
}
