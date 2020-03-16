package firemage.dynfx;

import firemage.dynfx.shape.FXShape;
import firemage.dynfx.shape.FXTransform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.Force;
import org.dyn4j.dynamics.Torque;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rotation;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

import java.util.LinkedList;
import java.util.List;

public class FXBody {
    private final Body body;
    private final List<FXFixture> fixtures;

    private boolean visible = true;
    private FXLogic logic = null;

    public FXBody() {
        this.body = new Body();
        this.fixtures = new LinkedList<>();
    }

    protected void paint(GraphicsContext g, FXTransform fxTransform) {
        if (visible) {
            fixtures.forEach(fixtures -> fixtures.draw(g, body.getTransform(), fxTransform));
        }
    }

    protected void examineLogic(long step) {
        if (logic != null) {
            logic.examineLogic(this, step);
        }
    }

    protected Body getBody() {
        return body;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void addFXFixture(FXFixture fixture) {
        fixtures.add(fixture);
        body.addFixture(fixture.getFixture());
    }

    public void addFXShape(FXShape shape) {
        fixtures.add(new FXFixture(shape));
        //body.addFixture(shape.getShape());
    }

    public void addFXFixtureFromFXShape(FXShape shape) {
        fixtures.add(new FXFixture(shape));
        body.addFixture(shape.getShape());
    }

    public void setLogic(FXLogic logic) {
        this.logic = logic;
    }

    public void setPaint(Paint paint) {
        fixtures.forEach(fxFixture -> fxFixture.getShape().setPaint(paint));
    }

    ////////////////////// Delegates from Body ///////////////////////////////////

    public Body updateMass() {
        return body.updateMass();
    }

    public Body setMass(MassType type) {
        return body.setMass(type);
    }

    public Mass getMass() {
        return body.getMass();
    }

    public Body applyForce(Vector2 force) {
        return body.applyForce(force);
    }

    public Body applyForce(Force force) {
        return body.applyForce(force);
    }

    public Body applyTorque(double torque) {
        return body.applyTorque(torque);
    }

    public Body applyTorque(Torque torque) {
        return body.applyTorque(torque);
    }

    public Body applyForce(Vector2 force, Vector2 point) {
        return body.applyForce(force, point);
    }

    public Body applyImpulse(Vector2 impulse) {
        return body.applyImpulse(impulse);
    }

    public Body applyImpulse(double impulse) {
        return body.applyImpulse(impulse);
    }

    public Body applyImpulse(Vector2 impulse, Vector2 point) {
        return body.applyImpulse(impulse, point);
    }

    public void clearForce() {
        body.clearForce();
    }

    public void clearAccumulatedForce() {
        body.clearAccumulatedForce();
    }

    public void clearTorque() {
        body.clearTorque();
    }

    public void clearAccumulatedTorque() {
        body.clearAccumulatedTorque();
    }

    public boolean isStatic() {
        return body.isStatic();
    }

    public boolean isKinematic() {
        return body.isKinematic();
    }

    public boolean isDynamic() {
        return body.isDynamic();
    }

    public boolean isAutoSleepingEnabled() {
        return body.isAutoSleepingEnabled();
    }

    public void setAutoSleepingEnabled(boolean flag) {
        body.setAutoSleepingEnabled(flag);
    }

    public boolean isAsleep() {
        return body.isAsleep();
    }

    public void setAsleep(boolean flag) {
        body.setAsleep(flag);
    }

    public boolean isActive() {
        return body.isActive();
    }

    public void setActive(boolean flag) {
        body.setActive(flag);
    }

    public boolean isBullet() {
        return body.isBullet();
    }

    public void setBullet(boolean flag) {
        body.setBullet(flag);
    }

    public boolean isConnected(Body body) {
        return this.body.isConnected(body);
    }

    public boolean isConnected(Body body, boolean collisionAllowed) {
        return this.body.isConnected(body, collisionAllowed);
    }

    public boolean isInContact(Body body) {
        return this.body.isInContact(body);
    }

    public Transform getInitialTransform() {
        return body.getInitialTransform();
    }

    public Vector2 getLocalCenter() {
        return body.getLocalCenter();
    }

    public Vector2 getWorldCenter() {
        return body.getWorldCenter();
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public void setLinearVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public Vector2 getLinearVelocity(Vector2 point) {
        return body.getLinearVelocity(point);
    }

    public void setLinearVelocity(double x, double y) {
        body.setLinearVelocity(x, y);
    }

    public double getAngularVelocity() {
        return body.getAngularVelocity();
    }

    public void setAngularVelocity(double angularVelocity) {
        body.setAngularVelocity(angularVelocity);
    }

    public Vector2 getForce() {
        return body.getForce();
    }

    public Vector2 getAccumulatedForce() {
        return body.getAccumulatedForce();
    }

    public double getTorque() {
        return body.getTorque();
    }

    public double getAccumulatedTorque() {
        return body.getAccumulatedTorque();
    }

    public double getLinearDamping() {
        return body.getLinearDamping();
    }

    public void setLinearDamping(double linearDamping) {
        body.setLinearDamping(linearDamping);
    }

    public double getAngularDamping() {
        return body.getAngularDamping();
    }

    public void setAngularDamping(double angularDamping) {
        body.setAngularDamping(angularDamping);
    }

    public double getGravityScale() {
        return body.getGravityScale();
    }

    public void setGravityScale(double scale) {
        body.setGravityScale(scale);
    }

    public void rotate(double theta, double x, double y) {
        body.rotate(theta, x, y);
    }

    public void rotate(Rotation rotation, double x, double y) {
        body.rotate(rotation, x, y);
    }

    public void rotate(double theta, Vector2 point) {
        body.rotate(theta, point);
    }

    public void rotate(Rotation rotation, Vector2 point) {
        body.rotate(rotation, point);
    }

    public void rotate(double theta) {
        body.rotate(theta);
    }

    public void rotate(Rotation rotation) {
        body.rotate(rotation);
    }

    public void rotateAboutCenter(double theta) {
        body.rotateAboutCenter(theta);
    }

    public void translate(double x, double y) {
        body.translate(x, y);
    }

    public void translate(Vector2 vector) {
        body.translate(vector);
    }

    public void translateToOrigin() {
        body.translateToOrigin();
    }

    public void shift(Vector2 shift) {
        body.shift(shift);
    }

    public Vector2 getLocalPoint(Vector2 worldPoint) {
        return body.getLocalPoint(worldPoint);
    }

    public Vector2 getWorldPoint(Vector2 localPoint) {
        return body.getWorldPoint(localPoint);
    }

    public Vector2 getLocalVector(Vector2 worldVector) {
        return body.getLocalVector(worldVector);
    }

    public Vector2 getWorldVector(Vector2 localVector) {
        return body.getWorldVector(localVector);
    }

    public boolean contains(Vector2 point) {
        return body.contains(point);
    }

    public Transform getTransform() {
        return body.getTransform();
    }
}
