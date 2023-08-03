package cc.geekadmin.entity;

/**
 * @Author Admin
 * @create 2022/12/24 22:30
 */
public class Column {

    private String columnName ;
    private String columnRemarksName ;
    private String columnClassName ;
    private String columnTypeName ;
    private int columnDisplaySize ;

    public Column() {
    }

    public Column(String columnName, String columnRemarksName, String columnClassName, String columnTypeName, int columnDisplaySize) {
        this.columnName = columnName;
        this.columnRemarksName = columnRemarksName;
        this.columnClassName = columnClassName;
        this.columnTypeName = columnTypeName;
        this.columnDisplaySize = columnDisplaySize;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnRemarksName() {
        return columnRemarksName;
    }

    public void setColumnRemarksName(String columnRemarksName) {
        this.columnRemarksName = columnRemarksName;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    @Override
    public String toString() {
        return "\n        Column��\n" +
                "          ������" + columnName + "\n" +
                "          ��ע��" + columnRemarksName + "\n" +
                "          Java���ͣ�" + columnClassName + "\n" +
                "          ���ݿ����ͣ�" + columnTypeName + "\n" +
                "          ���ȣ�" + columnDisplaySize ;
    }
}
