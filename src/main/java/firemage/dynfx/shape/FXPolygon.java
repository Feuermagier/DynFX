package firemage.dynfx.shape;

import javafx.scene.paint.Paint;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Vector2;

public class FXPolygon extends FXPolygonBase {

    public FXPolygon(Paint paint, Vector2... vertices) {
        super(new Polygon(vertices), paint);
    }

    public FXPolygon(Paint paint, Paint outlinePaint, Vector2... vertices) {
        super(new Polygon(vertices), paint, outlinePaint);
    }
}
