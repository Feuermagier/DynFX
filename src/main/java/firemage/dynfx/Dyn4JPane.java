package firemage.dynfx;

import firemage.dynfx.shape.FXTransform;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Dyn4JPane extends Canvas implements TimeStepListener {

    private static final double MS_PER_SECOND = Math.pow(10, 9);
    private static final double ZOOM_INTENSITY = 0.002;

    // Frames per second
    private final DoubleProperty currentFPS = new SimpleDoubleProperty(0);
    // Simulation steps per second
    private final DoubleProperty currentSPS = new SimpleDoubleProperty(0);

    private final AnimationTimer timer;
    private final FXSimulation simulation;

    private final double pixelsPerMeter;

    private double scale = 1;
    // Relative to the screen center
    private double centerX = 0;
    private double centerY = 0;
    private double pressedX, pressedY;

    private boolean started = false;

    public Dyn4JPane(FXSimulation simulation, double pixelsPerMeter) {
        super();

        this.pixelsPerMeter = pixelsPerMeter;
        this.simulation = simulation;

        simulation.addAfterStepListener(this);

        widthProperty().addListener(e -> repaint());
        heightProperty().addListener(e -> repaint());

        timer = new DifferenceAnimationTimer((now, diff) -> {
            if (started) {
                currentFPS.set(MS_PER_SECOND / diff);
                simulation.update(diff / MS_PER_SECOND);
            }
            repaint();
        });
        timer.start();

        setOnScroll(event -> {
            onScroll(event.getDeltaY(), event.getX(), event.getY());
        });

        setOnMousePressed(event -> {
            pressedX = event.getX();
            pressedY = event.getY();
        });

        setOnMouseDragged(event -> {
            centerX += event.getX() - pressedX;
            centerY += event.getY() - pressedY;

            pressedX = event.getX();
            pressedY = event.getY();
        });
    }

    public void startSimulation() {
        started = true;
    }

    public ObservableDoubleValue currentFPSProperty() {
        return currentFPS;
    }

    public ObservableDoubleValue currentSPSProperty() {
        return currentSPS;
    }

    private void repaint() {
        GraphicsContext g = getGraphicsContext2D();
        g.clearRect(0, 0, getWidth(), getHeight());

        FXTransform currentTransform = new FXTransform(scale * pixelsPerMeter, getWidth() / 2, getHeight() / 2, centerX, centerY);

        simulation.getBodies().forEach(body -> body.paint(g, currentTransform));
    }

    @Override
    public void onStep(long currentStep, double diff) {
        currentSPS.set(1 / diff);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    private void onScroll(double delta, double mouseX, double mouseY) {
        double zoomFactor = Math.exp(delta * ZOOM_INTENSITY);
        scale *= zoomFactor;
    }
}
