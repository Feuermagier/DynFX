package firemage.dynfx.shape;

import org.dyn4j.geometry.Vector2;

public class FXTransform {
    private final double scale;
    private final double width;
    private final double height;
    private final double centerX, centerY;

    // centerX, centerY in JavaFX-Coordinates
    public FXTransform(double scale, double width, double height, double centerX, double centerY) {
        this.scale = scale;
        this.width = width;
        this.height = height;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public Vector2 fit(Vector2 vector) {
        return new Vector2((width / 2) + vector.x * scale + centerX, (height / 2) - vector.y * scale + centerY);
    }

    public double getScale() {
        return scale;
    }
}
