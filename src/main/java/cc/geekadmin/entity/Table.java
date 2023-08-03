package cc.geekadmin.entity;

import java.util.List;

/**
 * @Author Admin
 * @create 2022/12/24 22:19
 */
public class Table {

    private String tableName ;
    private String tableRemarksName ;
    private List<Column> columns ;

    public Table() {
    }

    public Table(String tableName, String tableRemarksName, List<Column> columns) {
        this.tableName = tableName;
        this.tableRemarksName = tableRemarksName;
        this.columns = columns;
    }

    public int size() {
        return columns.size();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableRemarksName() {
        return tableRemarksName;
    }

    public void setTableRemarksName(String tableRemarksName) {
        this.tableRemarksName = tableRemarksName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        String str = "" ;
        for ( Column column : columns ) {
            str += column.toString();
        }
        return "\n    Table：\n" +
                "      表名：" + tableName + "\n" +
                "      备注：" + tableRemarksName + "\n" +
                "      列：" + str ;
    }
}
