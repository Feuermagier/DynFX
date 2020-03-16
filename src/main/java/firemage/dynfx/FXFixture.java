package firemage.dynfx;

import firemage.dynfx.shape.FXShape;
import firemage.dynfx.shape.FXTransform;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.collision.Filter;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Transform;

public class FXFixture {
    private final BodyFixture fixture;
    private final FXShape shape;

    public FXFixture(FXShape shape) {
        this.shape = shape;

        fixture = new BodyFixture(shape.getShape());
    }

    protected void draw(GraphicsContext g, Transform transform, FXTransform fxTransform) {
        shape.draw(g, transform, fxTransform);
    }

    protected BodyFixture getFixture() {
        return fixture;
    }

    public FXShape getShape() {
        return shape;
    }

    /////////////////////// Delegates from BodyFixture ////////////////////////////


    @Override
    public String toString() {
        return fixture.toString();
    }

    public double getDensity() {
        return fixture.getDensity();
    }

    public void setDensity(double density) {
        fixture.setDensity(density);
    }

    public double getFriction() {
        return fixture.getFriction();
    }

    public void setFriction(double friction) {
        fixture.setFriction(friction);
    }

    public double getRestitution() {
        return fixture.getRestitution();
    }

    public void setRestitution(double restitution) {
        fixture.setRestitution(restitution);
    }

    public Mass createMass() {
        return fixture.createMass();
    }

    public Filter getFilter() {
        return fixture.getFilter();
    }

    public void setFilter(Filter filter) {
        fixture.setFilter(filter);
    }

    public boolean isSensor() {
        return fixture.isSensor();
    }

    public void setSensor(boolean flag) {
        fixture.setSensor(flag);
    }
}
