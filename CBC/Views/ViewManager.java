package CBC.Views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager extends Application {

    static Stage stages = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        stages.setScene(LoginView.loginScene());
        stages.setTitle("Scene Switch Sample");
        stages.show();
    }

    /**
     * Changes the scene - works as routing manager
     * @param scene
     */
    public static void changeScene(Scene scene){
        stages.hide();
        Stage stage = new Stage();
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.setTitle("Super awesome specops encryption");

        stage.setScene(scene);

        stage.show();
    }
}
