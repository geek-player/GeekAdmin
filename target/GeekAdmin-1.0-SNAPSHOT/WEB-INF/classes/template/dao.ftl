package ${classPath}.dao;

import java.util.*;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ${classPath}.model.${className?cap_first};
import cc.geekadmin.utils.C3p0Utils;

/**
 * @Author ${SystemUser}
 * @create ${DateTime?datetime}
 */
public class ${className?cap_first}Dao {

    public boolean insert${className?cap_first}Dao(${className?cap_first} ${className?uncap_first}) {
        Connection connection = C3p0Utils.getConnection();
        String sql = "INSERT INTO ${tableName} VALUES (<#list columns as column>?<#if column_has_next>, </#if></#list>)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            <#list columns as column>
            preparedStatement.set${column.columnClassName?replace("Integer", "Int")}(${column_index+1}, <#if column.columnClassName == "Date">(Date) </#if>${className?uncap_first}.get${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}());
            </#list>
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }

    public boolean delete${className?cap_first}Dao(${className?cap_first} ${className?uncap_first}) {
        Connection connection = C3p0Utils.getConnection();
        String sql = "DELETE FROM ${tableName} WHERE ${columns[0].columnName} = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.set${columns[0].columnClassName?replace("Integer", "Int")}(1, ${className?uncap_first}.get${columns[0].columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }

    public boolean update${className?cap_first}Dao(${className?cap_first} ${className?uncap_first}) {
        Connection connection = C3p0Utils.getConnection();
        String sql = "UPDATE ${tableName} SET <#list columns as column><#if column_index!=0>${column.columnName} = ? <#if column_has_next>, </#if></#if></#list>WHERE ${columns[0].columnName} = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            <#list columns as column>
            <#if column_index!=0>
            preparedStatement.set${column.columnClassName?replace("Integer", "Int")}(${column_index}, <#if column.columnClassName == "Date">(Date) </#if>${className?uncap_first}.get${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}());
            </#if>
            </#list>
            preparedStatement.set${columns[0].columnClassName?replace("Integer", "Int")}(${columns?size}, ${className?uncap_first}.get${columns[0].columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }

    public List<${className?cap_first}> select${className?cap_first}Dao() {
        Connection connection = C3p0Utils.getConnection();
        String sql = "SELECT * FROM ${tableName}";
        List<${className?cap_first}> list = new ArrayList<${className?cap_first}>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ${className?cap_first} ${className?uncap_first} = new ${className?cap_first}();
                <#list columns as column>
                ${className?uncap_first}.set${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}(resultSet.get${column.columnClassName?replace("Integer", "Int")}(${column_index+1}));
                </#list>
                list.add(${className?uncap_first});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list ;
    }

    public List<${className?cap_first}> select${className?cap_first}ByPageDao(int page, int limit) {
        Connection connection = C3p0Utils.getConnection();
        String sql = "SELECT * FROM ${tableName} LIMIT ?,?";
        List<${className?cap_first}> list = new ArrayList<${className?cap_first}>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,(page-1)*limit);
            preparedStatement.setInt(2,limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ${className?cap_first} ${className?uncap_first} = new ${className?cap_first}();
                <#list columns as column>
                ${className?uncap_first}.set${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}(resultSet.get${column.columnClassName?replace("Integer", "Int")}(${column_index+1}));
                </#list>
                list.add(${className?uncap_first});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list ;
    }

    public ${className?cap_first} select${className?cap_first}ByIdDao(${columns[0].columnClassName?replace("Integer", "int")} id) {
        Connection connection = C3p0Utils.getConnection();
        String sql = "SELECT * FROM ${tableName} WHERE id = ?";
        ${className?cap_first} ${className?uncap_first} = new ${className?cap_first}();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.set${columns[0].columnClassName?replace("Integer", "Int")}(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            <#list columns as column>
            ${className?uncap_first}.set${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}(resultSet.get${column.columnClassName?replace("Integer", "Int")}(${column_index+1}));
            </#list>
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ${className?uncap_first} ;
    }

    public int select${className?cap_first}CountDao(){
        Connection connection = C3p0Utils.getConnection();
        String sql = "SELECT COUNT(1) FROM ${tableName}";
        int sum = 0 ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sum = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum ;
    }

}