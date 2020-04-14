package org.semesteroppgave;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class OpenWithThread extends Task<Void> {
    private final ProgressBar progressBar;

    public OpenWithThread(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected Void call() throws Exception {
        try {
            Thread.sleep(1000);
            progressBar.setProgress(0.25);
            Thread.sleep(1000);
            progressBar.setProgress(0.50);
            Thread.sleep(1000);
            progressBar.setProgress(0.75);
            Thread.sleep(1000);
            progressBar.setProgress(1);
        } catch (InterruptedException e) {
            // gjør ikke noe her, men hvis hvis du er i en løkke
            // burde løkken stoppes med break hvis isCancelled() er true...
        }
        FileHandler.openFileJobj();
        return null;
    }
}
