package firemage.dynfx.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

public class FXCircle extends FXShape {

    private final Circle circle;

    public FXCircle(double radius, Paint paint) {
        this(radius, paint, null);
    }

    public FXCircle(double radius, Paint paint, Paint outlinePaint) {
        this(new Circle(radius), paint, outlinePaint);
    }

    public FXCircle(Circle circle, Paint paint, Paint outlinePaint) {

        super(paint, outlinePaint);
        this.circle = circle;
    }

    @Override
    protected void drawImpl(GraphicsContext g, Transform transform, FXTransform fx) {

        Vector2 center = circle.getCenter();
        double radius = circle.getRadius();

        Vector2 top = fx.fit(transform.getTransformed(center).subtract(radius, radius));


        g.setFill(getPaint());
        g.fillOval(top.x, top.y, 2 * radius * fx.getScale(), 2 * radius * fx.getScale());

        if (getOutlinePaint() != null) {
            g.setStroke(getOutlinePaint());
            g.strokeOval(top.x - radius, top.y, 2 * radius, 2 * radius);
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
