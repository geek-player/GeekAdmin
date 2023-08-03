package ${classPath}.service;

import java.util.*;
import ${classPath}.model.${className?cap_first};
import ${classPath}.dao.${className?cap_first}Dao;

public class ${className?cap_first}Service {

    private final ${className?cap_first}Dao ${className?uncap_first}Dao = new ${className?cap_first}Dao();

    public boolean insert${className?cap_first}Service(${className?cap_first} ${className?uncap_first}) {
        return ${className?uncap_first}Dao.insert${className?cap_first}Dao(${className?uncap_first});
    }

    public boolean delete${className?cap_first}Service(${className?cap_first} ${className?uncap_first}) {
        return ${className?uncap_first}Dao.delete${className?cap_first}Dao(${className?uncap_first});
    }

    public boolean update${className?cap_first}Service(${className?cap_first} ${className?uncap_first}) {
        return ${className?uncap_first}Dao.update${className?cap_first}Dao(${className?uncap_first});
    }

    public List<${className?cap_first}> select${className?cap_first}Service() {
        return ${className?uncap_first}Dao.select${className?cap_first}Dao();
    }

    public List<${className?cap_first}> select${className?cap_first}ByPageService(int page, int limit) {
        return ${className?uncap_first}Dao.select${className?cap_first}ByPageDao(page,limit);
    }

    public ${className?cap_first} select${className?cap_first}ByIdService(${columns[0].columnClassName?replace("Integer", "int")} id) {
        return ${className?uncap_first}Dao.select${className?cap_first}ByIdDao(id);
    }

    public int select${className?cap_first}CountService() {
        return ${className?uncap_first}Dao.select${className?cap_first}CountDao();
    }

}