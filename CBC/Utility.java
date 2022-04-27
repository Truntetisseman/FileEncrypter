package CBC;

import javafx.scene.control.Alert;

public class Utility {

    /**
     * Adds a new log to the log array
     * @param inputLog object of type Logs
     */
    public static void addLogToLogs(Logs inputLog){
        Global.logs.add(inputLog);
        for (int i = 0; i < Global.logs.toArray().length; i++) {
            System.out.println(Global.logs.get(i).toString());
        }
    }


    /**
     * Creates a new Logs object and calls the addToLogs method
     * @param _userName string
     * @param _action string Encrypt/Decrypt/Login
     * @param _fileName string fileName
     * @param _path string Path of file
     */
    public static void logAction(String _userName, String _action, String _fileName, String _path){
        Logs l = new Logs(_userName, _action, _fileName, _path);
        addLogToLogs(l);
    }

    /**
     * Shows an information dialog window with hardcoded values, when the encryption succeeds
     */
    public static void successDialog(String fileName, String filePath, String action, String header, String context){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("The file was encrypted without any errors");
        alert.setContentText("Close this window and feel safe, knowing your encryption software is secure and works!");
        alert.showAndWait();
        Utility.logAction("Testuser", action, fileName, filePath);
    }

    /**
     * Shows an error dialog when encryption fails
     */
    public static void errorDialog(String fileName, String filePath, String action, String header, String context){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
        Utility.logAction("Testuser", action, fileName, filePath);
    }

}
