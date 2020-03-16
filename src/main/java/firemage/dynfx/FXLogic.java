package firemage.dynfx;

@FunctionalInterface
public interface FXLogic {
    void examineLogic(FXBody body, long tick);
}
