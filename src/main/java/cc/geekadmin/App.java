package cc.geekadmin;

import cc.geekadmin.entity.Column;
import cc.geekadmin.entity.Database;
import cc.geekadmin.entity.Table;
import cc.geekadmin.utils.DatabaseUtils;
import cc.geekadmin.utils.JDBC;
import cc.geekadmin.utils.TemplateUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

/**
 * @Author Admin
 * @create 2022/12/24 17:17
 */
public class App {

    public static void main(String[] args) throws IOException {

//        DatabaseUtils databaseUtils = new DatabaseUtils();
//        Database database = databaseUtils.getDatabase();
//        System.out.println(database);

        String zipFilePath = "D:/Java/apache-tomcat-8.5.84/webapps/GeekAdmin/test.zip";
        ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)));
        TemplateUtils.compress(out, new File("D:/Java/apache-tomcat-8.5.84/webapps/GeekAdmin" + File.separator + "test"), "test");
        out.close();



        // 创建数据库类型
//        Database database = new Database( getDatabaseName() );
//        // 创建表集合
//        List<Table> tables = new ArrayList<Table>();
//        // 获取表详细信息
//        for ( String table : getTableName() ) {
//            tables.add(new Table(table,getTableRemarksName(table),getColumns(database.getDataBasesName(),table)));
//        }
//        // 插入表详细信息
//        database.setTables(tables);
//
//        // 生成对应的entity、dao、service、servlet
//        TemplateUtils.generate(database);
//        // Template.generatePage(database,"list");
//
//        System.out.println(database);

        // test();

    }

    /**
     * 返回当前JDBC连接的数据库名称
     * @return String 数据库名称
     */
    private static String getDatabaseName(){
        String databaseName = "";
        try {
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT DATABASE()");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                databaseName = resultSet.getString(1) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return databaseName ;
    }

    /**
     * 返回数据库中所有表的名称
     * @return String[]
     */
    private static ArrayList<String> getTableName(){
        ArrayList<String> tables = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SHOW TABLES");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tables.add(resultSet.getString(1)) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tables ;
    }

    /**
     * 根据 表名 返回表的注释信息
     * @param tableName 表名
     * @return String 注释信息
     */
    private static String getTableRemarksName(String tableName){
        String tableRemarksName = "" ;
        try {
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("SELECT table_comment FROM information_schema.TABLES WHERE table_schema = ( SELECT DATABASE () ) AND table_name = '" + tableName + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tableRemarksName = resultSet.getString(1) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tableRemarksName ;
    }

    /**
     * 根据 数据库名 和 表名 返回表中所有列的信息
     * @param databaseName 数据库名
     * @param tableName 表名
     * @return ArrayList<Column> 所有列集合
     */
    private static ArrayList<Column> getColumns(String databaseName , String tableName) {
        ArrayList<Column> columns = new ArrayList<Column>();
        try {
            DatabaseMetaData databaseMetaData = JDBC.getConnection().getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(databaseName, null, tableName, "%");
            while (resultSet.next()) {
                Column column = new Column();
                // 获取列名
                column.setColumnName( resultSet.getString("COLUMN_NAME") );
                // 获取列备注
                column.setColumnRemarksName( resultSet.getString("REMARKS") );
                columns.add(column);
            }

            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement("select * from `" + tableName + "` where 1 = 2");
            ResultSetMetaData resultSetMetaData = preparedStatement.executeQuery().getMetaData();
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                // 获取列对应的Java类型
                String str = resultSetMetaData.getColumnClassName(i + 1);
                str = (String) str.subSequence(str.lastIndexOf(".")+1,str.length());
                str = "LocalDateTime".equals(str) ? "Date" : str ;
//                str = "Integer".equals(str) ? "int" : str ;
                columns.get(i).setColumnClassName(str);
                // 获取列对应的数据库类型
                columns.get(i).setColumnTypeName(resultSetMetaData.getColumnTypeName(i + 1));
                // 获取列对应的长度
                columns.get(i).setColumnDisplaySize(resultSetMetaData.getColumnDisplaySize(i + 1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return columns ;
    }

    /**
     * 可以查询出外键之间的关联
     * 正在开发中...
     */
    static void test() {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useInformationSchema=true";
        String username = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = conn.getMetaData();

            // 获取当前数据库中的所有表
            String catalog = conn.getCatalog();
            System.out.println(catalog+"__________________________");
            String schema = null;
            String tableNamePattern = null;
            String[] types = {"TABLE"};

            try (ResultSet tables = metaData.getTables(catalog, schema, tableNamePattern, types)) {
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");

                    System.out.println("Table: " + tableName);
                    System.out.println("-------------------------");

                    // 获取指定表作为外键参照的其他表的信息
                    try (ResultSet exportedKeys = metaData.getExportedKeys(catalog, null, tableName)) {
                        while (exportedKeys.next()) {
                            String foreignKeyTable = exportedKeys.getString("FKTABLE_NAME");
                            String foreignKeyColumn = exportedKeys.getString("FKCOLUMN_NAME");
                            String primaryKeyTable = exportedKeys.getString("PKTABLE_NAME");
                            String primaryKeyColumn = exportedKeys.getString("PKCOLUMN_NAME");

                            System.out.println("Foreign Key Table: " + foreignKeyTable);
                            System.out.println("Foreign Key Column: " + foreignKeyColumn);
                            System.out.println("Primary Key Table: " + primaryKeyTable);
                            System.out.println("Primary Key Column: " + primaryKeyColumn);
                            System.out.println("--------------------------------");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
