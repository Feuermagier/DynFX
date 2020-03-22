module dynfx {
    requires javafx.controls;
    requires javafx.graphics;
    requires org.dyn4j;

    opens firemage.dynfx.example to javafx.graphics;
    exports firemage.dynfx;
    exports firemage.dynfx.shape;
}
