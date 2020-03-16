package firemage.dynfx.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

public class FXCircle extends FXShape {

    private final Circle circle;

    public FXCircle(Circle circle, Paint paint) {
        this(circle, paint, null);
    }

    public FXCircle(Circle circle, Paint paint, Paint outlinePaint) {

        super(paint, outlinePaint);
        this.circle = circle;
    }

    @Override
    protected void drawImpl(GraphicsContext g, Transform transform, FXTransform fx) {
        Vector2 center = circle.getCenter();
        double radius = circle.getRadius();

        g.setFill(getPaint());
        g.fillOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);

        if (getOutlinePaint() != null) {
            g.setStroke(getOutlinePaint());
            g.strokeOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
        }
    }

    @Override
    public Convex getShape() {
        return circle;
    }

    @Override
    public void translate(double x, double y) {
        circle.translate(x, y);
    }

    @Override
    public void translate(Vector2 position) {
        circle.translate(position);
    }

    @Override
    public void rotate(double radians) {
        circle.rotate(radians);
    }
}
