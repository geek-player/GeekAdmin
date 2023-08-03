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

    public static void generate(String path, Database database) {
        generate(database, "model", path);
        generate(database, "dao", path);
        generate(database, "service", path);
        generate(database, "InsertServlet", path);
        generate(database, "DeleteServlet", path);
        generate(database, "UpdateServlet", path);
        generate(database, "SelectServlet", path);
        generate(database, "EditServlet", path);
    }

    public static void generate(Database database) {
        generate(database, "model");
        generate(database, "dao");
        generate(database, "service");
        generate(database, "InsertServlet");
        generate(database, "DeleteServlet");
        generate(database, "UpdateServlet");
        generate(database, "SelectServlet");
        generate(database, "EditServlet");
    }

    public static void zipGenerate(Database database, String sourceFolderPath) throws IOException {
        String zipFilePath = sourceFolderPath + File.separator + database.getDataBasesName() + ".zip";
        ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)));
        compress(out, new File(sourceFolderPath + File.separator + database.getDataBasesName()), database.getDataBasesName());
        out.close();
    }

    /**
     * ���� ���ݿ� �ļ����� ���ɶ�Ӧ�Ĵ����ļ�
     *
     * @param database ���ݿ�
     * @param type     ��Ҫ���ɵ��ļ�����
     */
    public static void generate(Database database, String type) {
        generate(database, type, CLASS_PATH);
    }

    /**
     * ���� ���ݿ� �ļ����� ���ɶ�Ӧ�Ĵ����ļ�
     *
     * @param database ���ݿ�
     * @param type     ��Ҫ���ɵ��ļ�����
     * @param path     ���ɴ���ı���·��
     */
    public static void generate(Database database, String type, String path) {
        Writer out = null;
        try {
            for (Table table : database.getTables()) {
                // ����ʵ��
                Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                // ��ȡģ��·��
                configuration.setClassForTemplateLoading(TemplateUtils.class, TEMPLATE_PATH);
                // ��������ģ��
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("SystemUser", System.getenv("USERNAME"));
                dataMap.put("DateTime", new Date());
                dataMap.put("classPath", "cc.geekadmin." + database.getDataBasesName());
                dataMap.put("className", StringUtils.UnderlineToHump(StringUtils.Uppercase(table.getTableName())));
                dataMap.put("columns", table.getColumns());
                dataMap.put("tableName", table.getTableName());
                // ����ģ���ļ�
                Template template = configuration.getTemplate("\\" + type + ".ftl");
                // ��������
                ArrayList<File> files = new ArrayList<File>();
                files.add(new File(path + File.separator + database.getDataBasesName() + "/model/"));
                files.add(new File(path + File.separator + database.getDataBasesName() + "/dao/"));
                files.add(new File(path + File.separator + database.getDataBasesName() + "/service/"));
                files.add(new File(path + File.separator + database.getDataBasesName() + "/servlet/"));
                //���Ŀ¼�������򴴽�
                for (File file : files) {
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdirs();
                    }
                }
                StringBuilder fileName;
                if ("model".equals(type)) {
                    fileName = new StringBuilder(path + database.getDataBasesName() + "/" + type + "/").append(StringUtils.Uppercase(StringUtils.UnderlineToHump(table.getTableName()))).append(".java");
                } else if ("dao".equals(type) || "service".equals(type)) {
                    fileName = new StringBuilder(path + database.getDataBasesName() + "/" + type + "/").append(StringUtils.Uppercase(StringUtils.UnderlineToHump(table.getTableName()))).append(StringUtils.Uppercase(type)).append(".java");
                } else {
                    fileName = new StringBuilder(path + database.getDataBasesName() + "/servlet/").append(StringUtils.Uppercase(StringUtils.UnderlineToHump(table.getTableName()))).append(StringUtils.Uppercase(type)).append(".java");
                }
                File docFile = new File(fileName.toString());
                out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(docFile.toPath())));
                // ����ļ�
                template.process(dataMap, out);
                System.out.println(table.getTableRemarksName() + " " + type + " ������");
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
     * ���� ���ݿ� �ļ����� ���ɶ�Ӧ��jsp�ļ�
     *
     * @param database ���ݿ�
     * @param type     ��Ҫ���ɵ��ļ�����
     */
    /*
    public static void generatePage(Database database, String type) {
        Writer out = null;
        try {
            for (Table table : database.getTables()) {
                // ����ʵ��
                Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
                // ���ñ����ʽ
                configuration.setDefaultEncoding("GBK");
                // ��ȡģ��·��
                configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH+"page/"));
                // ��������ģ��
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("SystemUser", System.getenv("USERNAME"));
                dataMap.put("DateTime", new Date());
                dataMap.put("tableName", table.getTableName());
                dataMap.put("tableRemarksName", table.getTableRemarksName());
                dataMap.put("columns", table.getColumns());
                // ����ģ���ļ�
                freemarker.template.Template template = configuration.getTemplate(type+".ftl");
                template.setOutputEncoding("UTF-8");
                // ��������
                StringBuilder path = new StringBuilder(PAGE_PATH + "/" + table.getTableName() + "/");
                File file = new File(path.toString());
                //���Ŀ¼�������򴴽�
                if(!file.exists() && !file.isDirectory()) {
                    file.mkdir();
                }
                path.append(type).append(".jsp");
                File docFile = new File(path.toString());
                out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(docFile.toPath()),"GBK"));
                // ����ļ�
                template.process(dataMap, out);
                System.out.println(table.getTableRemarksName()+" "+type+" ������");
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
     * �����ɵĴ����ļ������ѹ������
     *
     * @param out        ZIPѹ����
     * @param sourceFile ѹ���ļ�
     * @param base       ѹ�����ڸ�Ŀ¼�ļ���
     * @throws IOException IO�쳣
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

