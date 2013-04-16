import java.util.Timer;
import java.util.TimerTask;

public class GlobalTimer  {
    private int counter;
    
    private void modifyUserObject() {
        System.out.println("modifyUserObject: " + ++counter);
    }
    
    private void startTiming() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                GlobalTimer.this.modifyUserObject();
            }
        }, 2000, 1000);
    }

    public static void main(String[] args)  {
        new GlobalTimer().startTiming();
    }
}