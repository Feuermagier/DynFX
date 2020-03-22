package firemage.dynfx.example;

import firemage.dynfx.Dyn4JPane;
import firemage.dynfx.FXBody;
import firemage.dynfx.FXFixture;
import firemage.dynfx.FXSimulation;
import firemage.dynfx.shape.FXCircle;
import firemage.dynfx.shape.FXRectangle;
import firemage.dynfx.shape.FXTriangle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

public class RocketDemo extends Application {

    private boolean upArrowPressed = false;
    private boolean downArrowPressed = false;
    private boolean rightArrowPressed = false;
    private boolean leftArrowPressed = false;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rocket Demo");

        double scale = 32;
        FXSimulation simulation = new FXSimulation(World.EARTH_GRAVITY);

        FXBody player = new FXBody();
        player.addFXFixtureFromFXShape(new FXTriangle(new Vector2(0, 2), new Vector2(-1, -2), new Vector2(1, -2), Color.BLACK));

        FXRectangle leftFoot = new FXRectangle(0.5, 2, Color.BLACK);
        leftFoot.rotate(Math.toDegrees(45));
        leftFoot.translate(new Vector2(-1, -2));

        FXRectangle rightFood = new FXRectangle(0.5, 2, Color.BLACK);
        rightFood.rotate(-Math.toDegrees(45));
        rightFood.translate(new Vector2(1, -2));

        FXRectangle thruster = new FXRectangle(0.25, 0.5, Color.RED);
        thruster.translate(0, -2.25);
        thruster.setVisible(false);

        player.addFXFixtureFromFXShape(leftFoot);
        player.addFXFixtureFromFXShape(rightFood);
        player.addFXShape(thruster);
        player.setMass(MassType.NORMAL);
        //player.translate(4, 20);
        simulation.addFXBody(player);

        FXBody floor = new FXBody();
        FXFixture floorRect = new FXFixture(new FXRectangle(6, 0.5, Color.SANDYBROWN, Color.BROWN));
        floor.addFXFixture(floorRect);
        floorRect.setFriction(5);
        floor.setMass(MassType.INFINITE);
        floor.translate(50, 30);
        simulation.addFXBody(floor);

        FXBody center = new FXBody();
        center.addFXShape(new FXCircle(1, Color.GREEN));
        simulation.addFXBody(center);

        Dyn4JPane dyn4JPane = new Dyn4JPane(simulation, scale);
        Pane simPane = new Pane(dyn4JPane);
        dyn4JPane.widthProperty().bind(simPane.widthProperty());
        dyn4JPane.heightProperty().bind(simPane.heightProperty());

        player.setLogic((body, tick) -> {
            if (upArrowPressed) {
                player.applyForce(new Vector2(0, 200).rotate(player.getTransform().getRotationAngle()));
            }
            if (downArrowPressed) {
                player.applyForce(new Vector2(0, -100).rotate(player.getTransform().getRotationAngle()));
            }
            if (rightArrowPressed) {
                player.applyTorque(-30);
            }
            if (leftArrowPressed) {
                player.applyTorque(30);
            }
            if (upArrowPressed || downArrowPressed || leftArrowPressed || rightArrowPressed) {
                thruster.setVisible(true);
            } else {
                thruster.setVisible(false);
            }
        });

        HBox labelBox = new HBox();
        Label fpsLabel = new Label();
        dyn4JPane.currentFPSProperty().addListener(fps -> fpsLabel.setText(String.format("FPS: %.1f", dyn4JPane.currentFPSProperty().get())));
        Label spsLabel = new Label();
        dyn4JPane.currentSPSProperty().addListener(sps -> spsLabel.setText(String.format("SPS: %.1f", dyn4JPane.currentSPSProperty().get())));

        labelBox.setPadding(new Insets(20));
        labelBox.setSpacing(20);
        labelBox.getChildren().addAll(fpsLabel, spsLabel);

        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(labelBox, simPane);
        VBox.setVgrow(simPane, Priority.ALWAYS);

        Scene scene = new Scene(mainPane);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP)
                upArrowPressed = true;
            else if (event.getCode() == KeyCode.DOWN)
                downArrowPressed = true;
            else if (event.getCode() == KeyCode.RIGHT)
                rightArrowPressed = true;
            else if (event.getCode() == KeyCode.LEFT)
                leftArrowPressed = true;
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.UP)
                upArrowPressed = false;
            else if (event.getCode() == KeyCode.DOWN)
                downArrowPressed = false;
            else if (event.getCode() == KeyCode.RIGHT)
                rightArrowPressed = false;
            else if (event.getCode() == KeyCode.LEFT)
                leftArrowPressed = false;
        });

        primaryStage.setScene(scene);
        dyn4JPane.startSimulation();
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
