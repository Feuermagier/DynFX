package firemage.dynfx.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

public abstract class FXShape {

    private Paint paint;
    private Paint outlinePaint;
    private boolean visible = true;

    protected FXShape(Paint paint, Paint outlinePaint) {
        this.paint = paint;
        this.outlinePaint = outlinePaint;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getOutlinePaint() {
        return outlinePaint;
    }

    public void setOutlinePaint(Paint outlinePaint) {
        this.outlinePaint = outlinePaint;
    }

    public final void draw(GraphicsContext g, Transform transform, FXTransform fxTransform) {
        if (visible) {
            drawImpl(g, transform, fxTransform);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    protected abstract void drawImpl(GraphicsContext g, Transform transform, FXTransform fx);

    public abstract Convex getShape();

    public abstract void translate(double x, double y);

    public abstract void translate(Vector2 position);

    public abstract void rotate(double radians);
}
