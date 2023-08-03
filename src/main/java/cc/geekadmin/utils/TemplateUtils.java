package cc.geekadmin.utils;

import cc.geekadmin.entity.Database;
import cc.geekadmin.entity.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author Admin
 * @create 2022/12/25 10:51
 */
public class TemplateUtils {

    private static final String TEMPLATE_PATH = "/template/";
    private static final String CLASS_PATH = "code/";
    private static final String PATH_DEFAULT = "D://GeekAdmin/";
    private static final String PACKAGE_NAME = "code";


    public static void generate(String path, Database database, String packageName) {
        generate(database, "model", path, packageName);
        generate(database, "dao", path, packageName);
        generate(database, "service", path, packageName);
        generate(database, "InsertServlet", path, packageName);
        generate(database, "DeleteServlet", path, packageName);
        generate(database, "UpdateServlet", path, packageName);
        generate(database, "SelectServlet", path, packageName);
        generate(database, "EditServlet", path, packageName);
    }

    public static void generate(Database database) {
        generate(database, "model", PATH_DEFAULT, PACKAGE_NAME);
        generate(database, "dao", PATH_DEFAULT, PACKAGE_NAME);
        generate(database, "service", PATH_DEFAULT, PACKAGE_NAME);
        generate(database, "InsertServlet", PATH_DEFAULT, PACKAGE_NAME);
        generate(database, "DeleteServlet", PATH_DEFAULT, PACKAGE_NAME);
        generate(database, "UpdateServlet", PATH_DEFAULT, PACKAGE_NAME);
        generate(database, "SelectServlet", PATH_DEFAULT, PACKAGE_NAME);
        generate(database, "EditServlet", PATH_DEFAULT, PACKAGE_NAME);
    }

    public static void zipGenerate(String zipName, String sourceFolderPath) throws IOException {
        String zipFilePath = sourceFolderPath + File.separator + zipName + ".zip";
        ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)));
        compress(out, new File(sourceFolderPath + File.separator + zipName), zipName);
        out.close();
    }

    /**
     * 根据 数据库 文件类型 生成对应的代码文件
     *
     * @param database 数据库
     * @param type     需要生成的文件类型
     */
    public static void generate(Database database, String type) {
        generate(database, type, CLASS_PATH);
    }

    /**
     * 根据 数据库 文件类型 保存路径 生成对应的代码文件
     *
     * @param database 数据库
     * @param type     需要生成的文件类型
     * @param path     生成代码的保存路径
     */
    public static void generate(Database database, String type, String path) {
        generate(database, type, path, database.getDataBasesName());
    }

    /**
     * 根据 数据库 文件类型 保存路径 包名 生成对应的代码文件
     *
     * @param database    数据库
     * @param type        需要生成的文件类型
     * @param path        生成代码的保存路径
     * @param packageName 生成代码的包名
     */
    public static void generate(Database database, String type, String path, String packageName) {
        Writer out = null;
        try {
            for (Table table : database.getTables()) {
                // 创建实例
                Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                // 获取模版路径
                configuration.setClassForTemplateLoading(TemplateUtils.class, TEMPLATE_PATH);
                // 创建数据模型
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("SystemUser", System.getenv("USERNAME"));
                dataMap.put("DateTime", new Date());
                dataMap.put("classPath", "cc.geekadmin." + packageName);
                dataMap.put("className", StringUtils.UnderlineToHump(StringUtils.Uppercase(table.getTableName())));
                dataMap.put("columns", table.getColumns());
                dataMap.put("tableName", table.getTableName());
                // 加载模版文件
                Template template = configuration.getTemplate("\\" + type + ".ftl");
                // 生成数据
                ArrayList<File> files = new ArrayList<File>();
                files.add(new File(path + File.separator + packageName + "/model/"));
                files.add(new File(path + File.separator + packageName + "/dao/"));
                files.add(new File(path + File.separator + packageName + "/service/"));
                files.add(new File(path + File.separator + packageName + "/servlet/"));
                //如果目录不存在则创建
                for (File file : files) {
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdirs();
                    }
                }
                StringBuilder fileName;
                if ("model".equals(type)) {
                    fileName = new StringBuilder(path + packageName + "/" + type + "/").append(StringUtils.Uppercase(StringUtils.UnderlineToHump(table.getTableName()))).append(".java");
                } else if ("dao".equals(type) || "service".equals(type)) {
                    fileName = new StringBuilder(path + packageName + "/" + type + "/").append(StringUtils.Uppercase(StringUtils.UnderlineToHump(table.getTableName()))).append(StringUtils.Uppercase(type)).append(".java");
                } else {
                    fileName = new StringBuilder(path + packageName + "/servlet/").append(StringUtils.Uppercase(StringUtils.UnderlineToHump(table.getTableName()))).append(StringUtils.Uppercase(type)).append(".java");
                }
                File docFile = new File(fileName.toString());
                out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(docFile.toPath())));
                // 输出文件
                template.process(dataMap, out);
                System.out.println(table.getTableRemarksName() + " " + type + " 已生成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 根据 数据库 文件类型 生成对应的jsp文件
     *
     * @param database 数据库
     * @param type     需要生成的文件类型
     */
    /*
    public static void generatePage(Database database, String type) {
        Writer out = null;
        try {
            for (Table table : database.getTables()) {
                // 创建实例
                Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
                // 设置编码格式
                configuration.setDefaultEncoding("GBK");
                // 获取模版路径
                configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH+"page/"));
                // 创建数据模型
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("SystemUser", System.getenv("USERNAME"));
                dataMap.put("DateTime", new Date());
                dataMap.put("tableName", table.getTableName());
                dataMap.put("tableRemarksName", table.getTableRemarksName());
                dataMap.put("columns", table.getColumns());
                // 加载模版文件
                freemarker.template.Template template = configuration.getTemplate(type+".ftl");
                template.setOutputEncoding("UTF-8");
                // 生成数据
                StringBuilder path = new StringBuilder(PAGE_PATH + "/" + table.getTableName() + "/");
                File file = new File(path.toString());
                //如果目录不存在则创建
                if(!file.exists() && !file.isDirectory()) {
                    file.mkdir();
                }
                path.append(type).append(".jsp");
                File docFile = new File(path.toString());
                out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(docFile.toPath()),"GBK"));
                // 输出文件
                template.process(dataMap, out);
                System.out.println(table.getTableRemarksName()+" "+type+" 已生成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
*/

    /**
     * 将生成的代码文件打包到压缩包中
     *
     * @param out        ZIP压缩流
     * @param sourceFile 压缩文件
     * @param base       压缩包内根目录文件名
     * @throws IOException IO异常
     */
    public static void compress(ZipOutputStream out, File sourceFile, String base) throws IOException {
        if (sourceFile.isDirectory()) {
            File[] fileList = sourceFile.listFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    String filePath = base + "/" + file.getName();
                    compress(out, file, filePath);
                }
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(sourceFile.toPath()))) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
    }

}

