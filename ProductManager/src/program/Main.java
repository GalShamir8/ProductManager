package program;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import views.ProductManagerView;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		Controller controller = new Controller(new ProductManagerView(mainStage, false));
	}
}
