package ${classPath}.model;

import java.util.*;

public class ${className?cap_first} {

    <#list columns as column>
    /** ${column.columnRemarksName} */
    private ${column.columnClassName} ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first};
    </#list>

    public ${className?cap_first}() {
    }

    public ${className?cap_first}(<#list columns as column>${column.columnClassName} ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}<#if column_has_next>, </#if></#list>) {
    <#list columns as column>
        this.${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first} = ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first};
    </#list>
    }

    <#list columns as column>
    public ${column.columnClassName} get${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}() {
        return ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first};
    }

    public void set${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")}(${column.columnClassName} ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}) {
        this.${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first} = ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first};
    }

    </#list>
    @Override
    public String toString() {
        return "${className?cap_first}{" +
        <#list columns as column>
        "<#if column_index!=0>, </#if>${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first}=" + ${column.columnName?replace("_([a-z])"," $1","r")?capitalize?replace(" ","","r")?uncap_first} +
        </#list>
        '}';
    }

}