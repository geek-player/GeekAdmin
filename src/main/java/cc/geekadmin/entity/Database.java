package cc.geekadmin.entity;

import cc.geekadmin.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Admin
 * @create 2022/12/24 22:19
 */
public class Database {

    private String dataBasesName ;
    private List<Table> tables;

    public Database() {
    }

    public Database(String dataBasesName) {
        this.dataBasesName = dataBasesName;
    }

    public Database(String dataBasesName, List<Table> tables) {
        this.dataBasesName = dataBasesName;
        this.tables = tables;
    }

    public int size(){
        return tables.size();
    }

    public String getDataBasesName() {
        return dataBasesName;
    }

    public void setDataBasesName(String dataBasesName) {
        this.dataBasesName = dataBasesName;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for ( Table table : tables ) {
            str.append(table.toString());
        }
        return "Database：\n" +
                "  数据库名：" + dataBasesName + "\n" +
                "  数据库表：" + str ;
    }

}
