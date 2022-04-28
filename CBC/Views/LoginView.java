package CBC.Views;

import CBC.Global;
import CBC.Utility;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginView {

    /**
     * Login View, adds the login view where the user has to input username and password
     * @return
     */
    public static Scene loginScene() {
        StackPane login = new StackPane();
        VBox vBox1 = new VBox();

        Label uNameLabel = new Label("Username");
        Label pWordLabel = new Label("Password");

        TextField uNameTextField = new TextField("psec");
        TextField pWordTextField = new TextField("pizza");

        Button loginBtn = new Button("Login");
        loginBtn.setPrefWidth(100);

        vBox1.setSpacing(8);
        vBox1.setPadding(new Insets(20,20,20,20));
        vBox1.getChildren().addAll(uNameLabel, uNameTextField, pWordLabel, pWordTextField, loginBtn);

        login.getChildren().addAll(vBox1);

        loginBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        if(checkCredentials(uNameTextField.getText(), pWordTextField.getText()) == true){
                            System.out.print("Authorized");
                            Utility.logAction(uNameTextField.getText(), "Authorized Login", "nil", "nil");
                            Main.changeScene(MainView.mainScene());
                        } else {
                            Utility.errorDialog("nil", "nil", "Unauthorized Login Attempt", "Your login or password is incorrect", "Check your username and password and try again!");
                        }
                    }
                });

        Scene scene = new Scene(login, 400, 200);

        return scene;
    }

    /**
     * private function that checks if the users credentials match the stored credentials
     * @param uName String representing the users username
     * @param pWord String representing the users password
     * @return if the credentials match true is returned, else false
     */
    private static boolean checkCredentials(String uName, String pWord){
        if(uName.equals(Global.username) && pWord.equals(Global.password)){
            System.out.print("auth");
            return true;
        }
        return false;
    }
}
