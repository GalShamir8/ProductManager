package program;

import java.io.File;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import views.ProductManagerView;

public class Main extends Application{
	public static final String FILE_NAME = "products.txt";
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unused")
	@Override
	public void start(Stage mainStage) throws Exception {
		File file = new File(FILE_NAME);
		boolean sortFlag = file.length() > 0; //Check if the file already contains data
		
		Controller controller = new Controller(new ProductManagerView(mainStage, sortFlag));
	}
}
