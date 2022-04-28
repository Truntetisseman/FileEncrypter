package CBC;

import java.sql.Timestamp;

public class Logs {
    Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    public String userName;
    public String action;
    public String fileName;
    public String path;

    public Logs(String _userName, String _action, String _fileName, String _path){
        userName = _userName;
        action = _action;
        fileName = _fileName;
        path = _path;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + "[Username: " + userName + ", Action: " + action + " Filename: " + fileName + " Filepath: " + path + "]";
    }

}





