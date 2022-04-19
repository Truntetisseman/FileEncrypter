package CBC;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontEnd extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(final Stage stage) {
        stage.setTitle("Program with much security, password is pizza");

        stage.setWidth(900);
        stage.setHeight(700);

        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Encrypt");
        Tab tab2 = new Tab("Decrypt"  );
        Tab tab3 = new Tab("Log");
        Tab tab4 = new Tab("Testing");

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);

        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);

        tabPane.setTabMinWidth((stage.getWidth()/4) - 25);
        tabPane.setTabMinHeight(33);
        tabPane.setTabMaxWidth((stage.getWidth()/4) - 25);
        tabPane.setTabMaxHeight(69);



        // Encrypt box
        VBox encryptBox = new VBox(10);
        //Content
        GridPane encryptInputGridPane = new GridPane();
        FileChooser encryptFileChooser = new FileChooser();
        Button openButtonEncrypt = new javafx.scene.control.Button("Encrypt File");

        // Styling
        encryptInputGridPane.setHgap(6);
        encryptInputGridPane.setVgap(6);
        encryptBox.setPadding(new Insets(12, 12, 12, 12));

        Text encryptText = new Text();
        encryptText.setText("Select a file to begin encryption");

        openButtonEncrypt.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = encryptFileChooser.showOpenDialog(stage);
                        if (file != null) {
                            openFile(file);
                        }
                    }
                });
        encryptBox.getChildren().addAll(encryptText, openButtonEncrypt);
        tab1.setContent(encryptBox);


        // Log box
        VBox logBox = new VBox(10);
        // Content
        GridPane logBoxInputGridPane = new GridPane();

        // Styling
        logBoxInputGridPane.setHgap(6);
        logBoxInputGridPane.setVgap(6);
        logBox.setPadding(new Insets(12, 12, 12, 12));

        Text logText = new Text();
        logText.setText("All user logs are stored here");

        // Decrypt box
        VBox decryptBox = new VBox(10);
        //Content
        final GridPane decryptInputGridPane = new GridPane();
        final FileChooser decryptFileChooser = new FileChooser();
        final javafx.scene.control.Button openButtonDecrypt = new javafx.scene.control.Button("Decrypt File");

        // Styling
        decryptInputGridPane.setHgap(6);
        decryptInputGridPane.setVgap(6);
        decryptBox.setPadding(new Insets(12, 12, 12, 12));

        Text decryptText = new Text();
        decryptText.setText("Select a file to begin decryption");

        openButtonDecrypt.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = decryptFileChooser.showOpenDialog(stage);
                        if (file != null) {
                            openFile(file);
                        }
                    }
                });
        decryptBox.getChildren().addAll(decryptText, openButtonDecrypt);
        tab2.setContent(decryptBox);


        final FileChooser fileChooser = new FileChooser();

        final javafx.scene.control.Button openButton = new javafx.scene.control.Button("Encrypt File");
        final javafx.scene.control.Button openMultipleButton = new Button("Decrypt File");

        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            openFile(file);
                        }
                    }
                });

        openMultipleButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        List<File> list =
                                fileChooser.showOpenMultipleDialog(stage);
                        if (list != null) {
                            for (File file : list) {
                                openFile(file);
                            }
                        }
                    }
                });


        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(openMultipleButton, 1, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, openMultipleButton);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);



        //stage.setScene(new Scene(rootGroup));
        stage.setScene(scene);
        stage.show();
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    FrontEnd.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

}

