import UI.UiFrame;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();

        UiFrame ui = new UiFrame();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ui.createAndShowGUI();
            }
        });

        long endTime   = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Import data time: " + totalTime);
    }
}