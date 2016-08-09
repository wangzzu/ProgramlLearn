package phaser;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 * Created by matt on 16/8/9.
 */
public class PhaserDemo {

    public static void main(String[] args) throws IOException {
        int parties = 3;
        final int phases = 4;
        final Phaser phaser = new Phaser(parties) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("====== Phase : " + phase + " ======");
                return registeredParties == 0;
            }
        };

        for (int i = 0; i < parties; i++) {
            final int threadId = i;
            final Thread thread = new Thread() {
                public void run() {
                    for (int phase = 0; phase < phases; phase++) {
                        System.out.println(String.format("Thread %s, phase %s", threadId, phase));
                        phaser.arriveAndAwaitAdvance();
                    }
                }

            };
            thread.start();
        }
    }
}
