package org.smartcity.smartcity.dbProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbManagerProxy implements DbManager {

    private ConcreteDbManager Service;
    private Connection con = null;

    private Connection connect() throws SQLException {
        String path = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\DB\\SmartCityDb.sqlite"; //Database file Path, specificando i driver
        try {

            Connection conn = DriverManager.getConnection(path); //Connect
            conn.setAutoCommit(true);
            return conn; //Connection Done

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null; //Connection failed
        }


    }

    private Connection getCon() throws SQLException {
        if (con == null) {
            con = connect();
            return con;
        }
        if (con.isClosed()) {
            con = connect();
            return con;
        }
        return con;
    }

    @Override
    public List<Map<String, Object>> queryExec(String query) throws SQLException {
        Service = new ConcreteDbManager(getCon());
        List<Map<String, Object>> res = Service.queryExec(query);
        CloseConnection();
        return res;
    }

    @Override
    public void insert(String SQL) throws SQLException {
        Service = new ConcreteDbManager(getCon());
        Service.insert(SQL);
        CloseConnection();
    }

    private void CloseConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

}
