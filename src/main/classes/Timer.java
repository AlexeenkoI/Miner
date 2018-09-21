package main.classes;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread timer
 */

public class Timer extends Task<Void>{
    private final Label timerLabel;
    final AtomicInteger time = new AtomicInteger();

    public Timer(Label timerLabel) {
        this.timerLabel = timerLabel;
        time.set(0);
    }

    /**
     * Platform usage needed to access the UI thread
     * @return null
     * @throws Exception
     */

    @Override
    protected Void call() throws Exception {
        while(!isCancelled()){
            try{
                Platform.runLater(() -> {
                    timerLabel.setText("Time: " + time);
                    int newTime = time.get() + 1;
                    time.set(newTime);
                });
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }
        return null;
    }

}

