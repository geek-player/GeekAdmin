package cc.geekadmin.server;

import cc.geekadmin.utils.Arith;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JVM�����Ϣ
 *
 * @author jeethink  �ٷ���վ:www.jeethink.vip
 */
public class Jvm {
    /**
     * ��ǰJVMռ�õ��ڴ�����(M)
     */
    private double total;

    /**
     * JVM�������ڴ�����(M)
     */
    private double max;

    /**
     * JVM�����ڴ�(M)
     */
    private double free;

    /**
     * JDK�汾
     */
    private String version;

    /**
     * JDK·��
     */
    private String home;

    public double getTotal() {
        return Arith.div(total, (1024 * 1024), 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMax() {
        return Arith.div(max, (1024 * 1024), 2);
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getFree() {
        return Arith.div(free, (1024 * 1024), 2);
    }

    public void setFree(double free) {
        this.free = free;
    }

    public double getUsed() {
        return Arith.div(total - free, (1024 * 1024), 2);
    }

    public double getUsage() {
        return Arith.mul(Arith.div(total - free, total, 4), 100);
    }

    /**
     * ��ȡJDK����
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    /**
     * JDK����ʱ��
     */
    public String getStartTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ManagementFactory.getRuntimeMXBean().getStartTime());
    }

    /**
     * JDK����ʱ��
     */
    public String getRunTime() {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // �������ʱ��ĺ���ʱ�����
        long diff = System.currentTimeMillis() - new Date(ManagementFactory.getRuntimeMXBean().getStartTime()).getTime() ;
        // ����������
        long day = diff / nd;
        // ��������Сʱ
        long hour = diff % nd / nh;
        // �������ٷ���
        long min = diff % nd % nh / nm;
        // ����������//������
        // long sec = diff % nd % nh % nm / ns;
        return day + "��" + hour + "Сʱ" + min + "����";
    }

    @Override
    public String toString() {
        return "Jvm{" +
                "name=" + ManagementFactory.getRuntimeMXBean().getVmName() +
                ", total=" + getTotal() +
                ", max=" + getMax() +
                ", free=" + getFree() +
                ", version='" + getVersion() + '\'' +
                ", home='" + getHome() + '\'' +
                '}';
    }
}
