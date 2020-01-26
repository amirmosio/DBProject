package Com.Service;

import Com.DLL.DataBaseConnection;

import java.util.ArrayList;

public class Service {
    DataBaseConnection dc = new DataBaseConnection();

    public ArrayList<ArrayList<String>> executeQuery(String query) {
        return dc.executeQuery(query);
    }
}
