package CBC.Views;

import CBC.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


public class MainView {
    /**
     * The login scene with login functionality
     * @return
     */
    public static Scene mainScene() {
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

        tabPane.setTabMinWidth(180);
        tabPane.setTabMinHeight(33);
        tabPane.setTabMaxWidth(180);
        tabPane.setTabMaxHeight(69);

        // Encrypt box
        VBox encryptBox = new VBox(10);
        //Content
        GridPane encryptInputGridPane = new GridPane();
        FileChooser encryptFileChooser = new FileChooser();
        Button openButtonEncrypt = new Button("Encrypt File");

        Button createKeystoreButton = new Button("Create Keystore");

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
                        File file = encryptFileChooser.showOpenDialog(mainScene().getWindow());
                        if (file != null) {
                            //String fileAsString = file.toString();
                            try {
                                Encrypt.encryptFile(file);
                            } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

        createKeystoreButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        Keystore.addKeystore();
                    }
                });


        encryptBox.getChildren().addAll(encryptText, openButtonEncrypt, createKeystoreButton);
        tab1.setContent(encryptBox);

        // -------------------------------------------- Decrypt Tab
        VBox decryptBox = new VBox(10);

        //Content
        GridPane decryptInputGridPane = new GridPane();
        FileChooser decryptFileChooser = new FileChooser();
        Button openButtonDecrypt = new Button("Decrypt Files");

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

                        File file = decryptFileChooser.showOpenDialog(mainScene().getWindow());
                        if (file != null) {
                            String fileAsString = file.toString();
                            String fileName = file.getName();
                            String[] fileParts = fileName.split("\\.");
                            System.out.println("its here: " + fileName);
                            System.out.println("Frontend" + file);
                            Decrypt.decryptFile(fileAsString, Global.decryptFolder + "/" + fileParts[0] + "." + fileParts[1] + ".sha256");
                        }
                    }
                });
        decryptBox.getChildren().addAll(decryptText, openButtonDecrypt);
        tab2.setContent(decryptBox);

        // --------------------------------------------  Log Tab
        VBox logBox = new VBox(10);
        // Content
        GridPane logBoxInputGridPane = new GridPane();

        // Styling
        logBoxInputGridPane.setHgap(6);
        logBoxInputGridPane.setVgap(6);
        logBox.setPadding(new Insets(12, 12, 12, 12));
        Button refreshLogsBtn = new Button("Refresh Logs");

        Text logText = new Text();
        logText.setText("User logs");

        TableView tableView = new TableView();

        //Column 1
        TableColumn<Logs, String> column1 = new TableColumn<>("Timestamp");
        column1.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

        TableColumn<Logs, String> column2 = new TableColumn<>("Username");
        column2.setCellValueFactory(new PropertyValueFactory<>("userName"));

        //Column 3
        TableColumn<Logs, String> column3= new TableColumn<>("Action");
        column3.setCellValueFactory(new PropertyValueFactory<>("action"));

        //Column 4
        TableColumn<Logs, String> column4 = new TableColumn("Filename");
        column4.setCellValueFactory(new PropertyValueFactory<>("fileName"));

        //Column 4
        TableColumn<Logs, String> column5 = new TableColumn("Filepath");
        column5.setCellValueFactory(new PropertyValueFactory<>("path"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);

        refreshLogsBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        tableView.getItems().clear();
                        ObservableList<Logs> log= FXCollections.observableArrayList();
                        for (int i = 0; i < Global.logs.toArray().length; i++) {

                            if (Global.logs.get(i) != null) {
                                Logs l = new Logs(Global.logs.get(i).userName, Global.logs.get(i).action, Global.logs.get(i).fileName, Global.logs.get(i).path);
                                log.add(l);               }
                        }

                        tableView.setItems(log);


                    }
                });

        logBox.getChildren().addAll(logText, refreshLogsBtn, tableView);
        tab3.setContent(logBox);

        // --------------------------------------------  Test Tab
        VBox testBox = new VBox(10);
        GridPane testBoxInputGridPane = new GridPane();

        // Styling
        testBoxInputGridPane.setHgap(6);
        testBoxInputGridPane.setVgap(6);
        testBox.setPadding(new Insets(12, 12, 12, 12));
        Button testBtn = new Button("Perform Epic Test");

        testBtn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Test.testEncryption();
                        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        Text testText = new Text();
        testText.setText("Test that the system performs as expected");

        testBox.getChildren().addAll(testText, testBtn);

        tab4.setContent(testBox);

        FileChooser fileChooser = new FileChooser();

        Button openButton = new javafx.scene.control.Button("Encrypt Files");
        Button openMultipleButton = new Button("Decrypt File");

        /*
        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if(Login.login("Patrick", "Pizza")){
                            System.out.print("success");
                        };
                        //if(!Decrypt.checkForKeystore()){
                           // Decrypt.createKeystore();
                        //}
                    }
                }
        );
        */

        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(mainScene().getWindow());
                        if (file != null) {
                            String fileAsString = file.toString();
                            System.out.println(file);
                            try {
                                Encrypt.encryptFile(file);
                            } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

        GridPane inputGridPane = new GridPane();

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

        return scene;
    }
}


