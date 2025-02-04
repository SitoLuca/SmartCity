package org.smartcity.smartcity.dbProxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DbManager {

    List<Map<String, Object>> queryExec(String query) throws SQLException;
    void insert(String SQL) throws SQLException;
}
