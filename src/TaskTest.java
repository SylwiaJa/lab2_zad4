import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskTest {
    public static void main(String[] args) {
        Random r = new Random();

        ArrayList<FTask<Integer>> aL = new ArrayList<>();
        ExecutorService exec = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            aL.add(new FTask<Integer>(new Task1("t"+i, r.nextInt(10))));
        }
        for (var t : aL) {
            exec.execute(t);
        }
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(aL.get(0).cancel(true));
        System.out.println(aL.get(1).cancel(false));
        System.out.println(aL.get(7).cancel(true));

        exec.shutdown();
        try {
            exec.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
