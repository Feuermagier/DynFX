package firemage.dynfx.shape;

import javafx.scene.paint.Paint;
import org.dyn4j.geometry.Triangle;
import org.dyn4j.geometry.Vector2;

public class FXTriangle extends FXPolygonBase {
    public FXTriangle(Vector2 point1, Vector2 point2, Vector2 point3, Paint paint) {
        super(new Triangle(point1, point2, point3), paint);
    }

    public FXTriangle(Vector2 point1, Vector2 point2, Vector2 point3, Paint paint, Paint outlinePaint) {
        super(new Triangle(point1, point2, point3), paint, outlinePaint);
    }
}
