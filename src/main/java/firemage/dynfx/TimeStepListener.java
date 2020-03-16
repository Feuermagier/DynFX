package firemage.dynfx;

@FunctionalInterface
public interface TimeStepListener {
    // Diff in seconds
    void onStep(long currentStep, double diff);
}
