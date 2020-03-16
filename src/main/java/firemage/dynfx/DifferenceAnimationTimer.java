package firemage.dynfx;

import javafx.animation.AnimationTimer;

import java.util.function.BiConsumer;

public class DifferenceAnimationTimer extends AnimationTimer {

    private final BiConsumer<Long, Long> consumer;

    private long lastTimeStep = 0;
    private boolean paused = false;

    public DifferenceAnimationTimer(BiConsumer<Long, Long> consumer) {
        this.consumer = consumer;
    }


    @Override
    public void handle(long now) {
        if (!paused) {
            consumer.accept(now, now - lastTimeStep);
        }
        lastTimeStep = now;
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }
}
