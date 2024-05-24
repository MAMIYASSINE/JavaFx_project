module JavaFx_project {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
