package firemage.dynfx.shape;

import javafx.scene.paint.Paint;
import org.dyn4j.geometry.Rectangle;

public class FXRectangle extends FXPolygonBase {
    public FXRectangle(double width, double height, Paint paint) {
        super(new Rectangle(width, height), paint);
    }

    public FXRectangle(double width, double height, Paint paint, Paint outlinePaint) {
        super(new Rectangle(width, height), paint, outlinePaint);
    }
}
