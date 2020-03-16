package firemage.dynfx.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

public abstract class FXPolygonBase extends FXShape {

    private final Polygon polygon;

    public FXPolygonBase(Polygon polygon, Paint paint) {
        this(polygon, paint, null);
    }

    public FXPolygonBase(Polygon polygon, Paint paint, Paint outlinePaint) {

        super(paint, outlinePaint);
        this.polygon = polygon;
    }

    @Override
    protected void drawImpl(GraphicsContext g, Transform transform, FXTransform fx) {
        g.setFill(getPaint());
        g.beginPath();

        for (int i = 0; i < polygon.getVertices().length; i++) {
            Vector2 vertex = fx.fit(transform.getTransformed(polygon.getVertices()[i]));
            if (i == 0) {
                g.moveTo(vertex.x, vertex.y);
            } else {
                g.lineTo(vertex.x, vertex.y);
            }
        }
        g.closePath();
        g.fill();
        if (getOutlinePaint() != null) {
            g.setStroke(getOutlinePaint());
            g.stroke();
        }
    }

    @Override
    public Convex getShape() {
        return polygon;
    }

    @Override
    public void translate(double x, double y) {
        polygon.translate(x, y);
    }

    @Override
    public void translate(Vector2 position) {
        polygon.translate(position);
    }

    @Override
    public void rotate(double radians) {
        polygon.rotate(radians);
    }
}
