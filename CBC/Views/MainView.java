package CBC.Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class MainView {

    /**
     * Tableview sourced from jenkov.com
     *
     * @return
     */
    public static Scene loginScene() {
        StackPane login = new StackPane();
        VBox vBox1 = new VBox();

        File folder = new File("/Users/patrickorum/Desktop/testfolder");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }




        TableView tableView = new TableView();

        //Column 1
        TableColumn<File, String> column1 = new TableColumn<>("Filename");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Column 2
        TableColumn<File, String> column2 = new TableColumn<>("Filepath");
        column2.setCellValueFactory(new PropertyValueFactory<>("path"));

        //Column 3
        Button loginBtn = new Button("Login");
        TableColumn<File, String> column3 = new TableColumn("Encryption Status");
        column2.setCellValueFactory(new PropertyValueFactory<>("suffix"));



        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);


        VBox vbox = new VBox(tableView);

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                tableView.getItems().add(listOfFiles[i]);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        Scene scene = new Scene(vbox, 400, 200);

        return scene;
    }

    public static String setEncryptionStatus(){

        var status = "Not encrypted";

        return status;
    }
}
