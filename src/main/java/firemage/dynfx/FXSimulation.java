package firemage.dynfx;

import org.dyn4j.dynamics.Step;
import org.dyn4j.dynamics.StepListener;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import java.util.LinkedList;
import java.util.List;

public class FXSimulation implements StepListener {

    private static final double MS_PER_SECOND = Math.pow(10, 9);

    private final List<FXBody> bodies = new LinkedList<>();
    private final World world;
    private final List<TimeStepListener> beforeStepListeners = new LinkedList<>();
    private final List<TimeStepListener> afterStepListeners = new LinkedList<>();
    private boolean parallelLogicExecution = false;
    private long timeStep = 0;

    public FXSimulation() {
        this(World.ZERO_GRAVITY);
    }

    public FXSimulation(Vector2 gravity) {
        this.world = new World();
        world.setGravity(gravity);
        world.addListener(this);
    }

    public void addFXBody(FXBody body) {
        bodies.add(body);
        world.addBody(body.getBody());
    }

    public boolean update(double elapsedTime) {
        return world.update(elapsedTime);
    }

    public void updatev(double elapsedTime) {
        world.updatev(elapsedTime);
    }

    public void step(int steps) {
        world.step(steps);
    }

    public void setParallelLogicExecution(boolean parallelLogicExecution) {
        this.parallelLogicExecution = parallelLogicExecution;
    }

    public void addBeforeStepListener(TimeStepListener listener) {
        beforeStepListeners.add(listener);
    }

    public void removeBeforeStepListener(TimeStepListener listener) {
        beforeStepListeners.remove(listener);
    }

    public void addAfterStepListener(TimeStepListener listener) {
        afterStepListeners.add(listener);
    }

    public void removeAfterStepListener(TimeStepListener listener) {
        afterStepListeners.remove(listener);
    }

    public List<FXBody> getBodies() {
        return bodies;
    }

    // Never modify the world here!
    public World getWorld() {
        return world;
    }

    private void runLogic(long step) {
        if (parallelLogicExecution) {
            bodies.parallelStream().forEach(body -> body.examineLogic(step));
        } else {
            bodies.forEach(body -> body.examineLogic(step));
        }
    }

    @Override
    public void begin(Step step, World world) {
        timeStep++;
        beforeStepListeners.forEach(listener -> listener.onStep(timeStep, step.getDeltaTime()));
        runLogic(timeStep);
    }

    @Override
    public void end(Step step, World world) {
        afterStepListeners.forEach(listener -> listener.onStep(timeStep, step.getDeltaTime()));
    }

    @Override
    public void updatePerformed(Step step, World world) {

    }

    @Override
    public void postSolve(Step step, World world) {

    }
}
