package cc.geekadmin.utils;

import cc.geekadmin.entity.Column;
import cc.geekadmin.entity.Database;
import cc.geekadmin.entity.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Admin
 * @create 2023/1/11 23:11
 */
public class DatabaseUtils {

    public Database getDatabase() {
        // 创建数据库类型
        Database database = new Database(getDatabaseName());
        // 创建表集合
        List<Table> tables = new ArrayList<Table>();
        // 获取表详细信息
        for (String table : getTableName()) {
            tables.add(new Table(table, getTableRemarksName(table), getColumns(database.getDataBasesName(), table)));
        }
        // 插入表详细信息
        database.setTables(tables);
        return database;
    }

    /**
     * 返回当前JDBC连接的数据库名称
     *
     * @return String 数据库名称
     */
    public String getDatabaseName() {
        String databaseName = "";
        try {
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT DATABASE()");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                databaseName = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return databaseName;
    }

    /**
     * 返回数据库中所有表名
     *
     * @return String[]
     */
    public ArrayList<String> getTableName() {
        ArrayList<String> tables = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SHOW TABLES");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tables.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tables;
    }

    /**
     * 根据 表名 返回表的注释信息
     *
     * @param tableName 表名
     * @return String 注释信息
     */
    public String getTableRemarksName(String tableName) {
        String tableRemarksName = "";
        try {
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT table_comment FROM information_schema.TABLES WHERE table_schema = ( SELECT DATABASE () ) AND table_name = '" + tableName + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tableRemarksName = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tableRemarksName;
    }

    /**
     * 根据 数据库名 和 表名 返回表中所有列的信息
     *
     * @param databaseName 数据库名
     * @param tableName    表名
     * @return ArrayList<Column> 所有列集合
     */
    public ArrayList<Column> getColumns(String databaseName, String tableName) {
        ArrayList<Column> columns = new ArrayList<Column>();
        try {
            DatabaseMetaData databaseMetaData = JDBC.getConnection().getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(databaseName, null, tableName, "%");
            while (resultSet.next()) {
                Column column = new Column();
                // 获取列名
                column.setColumnName(resultSet.getString("COLUMN_NAME"));
                // 获取列备注
                column.setColumnRemarksName(resultSet.getString("REMARKS"));
                columns.add(column);
            }

            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("select * from " + tableName + " where 1 = 2");
            ResultSetMetaData resultSetMetaData = preparedStatement.executeQuery().getMetaData();
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                // 获取列对应的Java类型
                String str = resultSetMetaData.getColumnClassName(i + 1);
                str = (String) str.subSequence(str.lastIndexOf(".") + 1, str.length());
                str = "LocalDateTime".equals(str) ? "Date" : str;
                columns.get(i).setColumnClassName(str);
                // 获取列对应的数据库类型
                columns.get(i).setColumnTypeName(resultSetMetaData.getColumnTypeName(i + 1));
                // 获取列对应的长度
                columns.get(i).setColumnDisplaySize(resultSetMetaData.getColumnDisplaySize(i + 1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return columns;
    }


}
