package co.demo.java8.lambda;

import org.junit.Test;

import java.io.*;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用：若Lambda中的内容有方法已经实现了，那么我们可以使用“方法引用”
 * （可以理解为方法引用是lambda表达式的另外一种表现形式）
 * <p>
 * 主要有三种语法
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * <p>
 * 注意：
 * 1.Lambda体中的调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的函数列表和返回值保持一致
 * <p>
 * 二：构造器方法引用
 * 格式：
 * ClassName:new
 * 注意：需要调用的构造器的参数列表要与函数式接口中的抽象方法的参数列表保持一致
 * <p>
 * 三、数组引用：
 * <p>
 * Type[]::new
 */
public class TestLambda3 {

    //对象::实例方法名
    //注意前提是：Consumer 中的 void accept(T t); 的返回类型和参数列表与void println()的返回类型和参数列表一直
    @Test
    public void test1() {
        //注意前提是：Consumer 中的 void accept(T t); 的返回类型和参数列表与void println()的返回类型和参数列表一直
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> stringSupplier = () -> employee.getName();
        stringSupplier.get();
        System.out.println(stringSupplier.get());

        Supplier<Integer> supplier = employee::getAge;
        System.out.println(supplier.get());
    }

    //类::静态方法名
    //注意前提是：Comparator 中的 int compare(T o1, T o2); 的返回类型和参数列表与int compare(T o1, T o2)的返回类型和参数列表一直
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> comparator1 = Integer::compare;
    }

    // 类::实例方法名
    @Test
    public void test4() {

        BiPredicate<String, String> biPredicate = new BiPredicate<String, String>() {
            @Override
            public boolean test(String s, String s2) {
                return s.equals(s2);
            }
        };
        boolean test = biPredicate.test("1", "1");

        BiPredicate<String, String> biPredicate1 = (x, y) -> x.equals(y);
        String x = "1";
        String y = "1";
        boolean test1 = biPredicate1.test(x, y);
        //注意：如果第一个参数是实例方法的调用者 而第二个参数是这个实例方法的参数时则可以使用 ClassName :: method

        BiPredicate<String, String> biPredicate2 = String::equals;
        boolean test2 = biPredicate2.test("1", "1");
        System.out.println("----");
    }

    //构造器方法引用
    @Test
    public void test5() {
        Supplier<Employee> supplier = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return null;
            }
        };

        Supplier<Employee> supplier1 = () -> new Employee();
        Employee employee = supplier1.get();
        //构造器引用方式（无参构造）,有参无参还是要看函数方法 Supplier  T get(); 是无参那么 Employee::new 无参的
        Supplier<Employee> supplier2 = Employee::new;
        Employee employee1 = supplier2.get();
        System.out.println(employee1);
    }

    @Test
    public void test6() {
        Function<Integer, Employee> function = (x) -> new Employee(x);
        Employee apply = function.apply(11);
        System.out.println(apply);

        Function<Integer, Employee> function1 = Employee::new;
        Employee apply1 = function1.apply(1);
        System.out.println(apply1);

        BiFunction<String, Integer, Employee> function2 = Employee::new;
        Employee employee = function2.apply("nihao", 1);
        System.out.println(employee);
    }

    //数组引用
    @Test
    public void test7() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] apply = function.apply(10);
        System.out.println(apply.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] apply1 = function1.apply(10);
        System.out.println(apply1.length);
    }


    /**
     * 题外记录：在java中运行cmd命令
     * java的Runtime.getRuntime().exec(commandStr)可以调用执行cmd指令。
     *
     *  
     * cmd /c dir 是执行完dir命令后关闭命令窗口。
     *
     * cmd /k dir 是执行完dir命令后不关闭命令窗口。
     *
     * cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
     *
     * cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭
     cmd常用命令


     gpedit.msc-----组策略

     sndrec32-------录音机

     Nslookup-------IP地址侦测器

     explorer-------打开资源管理器

     logoff---------注销命令

     tsshutdn-------60秒倒计时关机命令

     lusrmgr.msc----本机用户和组

     services.msc---本地服务设置

     oobe/msoobe /a----检查XP是否激活

     notepad--------打开记事本

     cleanmgr-------垃圾整理

     net start messenger----开始信使服务

     compmgmt.msc---计算机管理

     net stop messenger-----停止信使服务

     conf-----------启动netmeeting

     dvdplay--------DVD播放器

     charmap--------启动字符映射表

     diskmgmt.msc---磁盘管理实用程序

     calc-----------启动计算器

     dfrg.msc-------磁盘碎片整理程序

     chkdsk.exe-----Chkdsk磁盘检查

     devmgmt.msc--- 设备管理器

     regsvr32 /u *.dll----停止dll文件运行

     drwtsn32------ 系统医生

     rononce -p ----15秒关机

     dxdiag---------检查DirectX信息

     regedt32-------注册表编辑器

     Msconfig.exe---系统配置实用程序

     rsop.msc-------组策略结果集

     mem.exe--------显示内存使用情况

     regedit.exe----注册表

     winchat--------XP自带局域网聊天

     progman--------程序管理器

     winmsd---------系统信息

     perfmon.msc----计算机性能监测程序

     winver---------检查Windows版本

     sfc /scannow-----扫描错误并复原

     taskmgr-----任务管理器（2000／xp／2003

     winver---------检查Windows版本

     wmimgmt.msc----打开windows管理体系结构(WMI)

     wupdmgr--------windows更新程序

     wscript--------windows脚本宿主设置

     write----------写字板

     winmsd---------系统信息

     wiaacmgr-------扫描仪和照相机向导

     winchat--------XP自带局域网聊天

     mem.exe--------显示内存使用情况

     Msconfig.exe---系统配置实用程序

     mplayer2-------简易widnows media player

     mspaint--------画图板

     mstsc----------远程桌面连接

     mplayer2-------媒体播放机

     magnify--------放大镜实用程序

     mmc------------打开控制台

     mobsync--------同步命令

     dxdiag---------检查DirectX信息

     drwtsn32------ 系统医生

     devmgmt.msc--- 设备管理器

     dfrg.msc-------磁盘碎片整理程序

     diskmgmt.msc---磁盘管理实用程序

     dcomcnfg-------打开系统组件服务

     ddeshare-------打开DDE共享设置

     dvdplay--------DVD播放器

     net stop messenger-----停止信使服务

     net start messenger----开始信使服务

     notepad--------打开记事本

     nslookup-------网络管理的工具向导

     ntbackup-------系统备份和还原

     narrator-------屏幕“讲述人”

     ntmsmgr.msc----移动存储管理器

     ntmsoprq.msc---移动存储管理员操作请求

     netstat -an----(TC)命令检查接口

     syncapp--------创建一个公文包

     sysedit--------系统配置编辑器

     sigverif-------文件签名验证程序

     sndrec32-------录音机

     shrpubw--------创建共享文件夹

     secpol.msc-----本地安全策略

     syskey---------系统加密，一旦加密就不能解开，保护windows xp系统的双重密码

     services.msc---本地服务设置

     Sndvol32-------音量控制程序

     sfc.exe--------系统文件检查器

     sfc /scannow---windows文件保护

     tsshutdn-------60秒倒计时关机命令

     tsshutdn-------60秒倒计时关机命令

     tourstart------xp简介（安装完成后出现的漫游xp程序）

     taskmgr--------任务管理器

     eventvwr-------事件查看器

     eudcedit-------造字程序

     explorer-------打开资源管理器

     packager-------对象包装程序

     perfmon.msc----计算机性能监测程序

     progman--------程序管理器

     regedit.exe----注册表

     rsop.msc-------组策略结果集

     regedt32-------注册表编辑器

     rononce -p ----15秒关机

     regsvr32 /u *.dll----停止dll文件运行

     regsvr32 /u zipfldr.dll------取消ZIP支持

     cmd.exe--------CMD命令提示符

     chkdsk.exe-----Chkdsk磁盘检查

     certmgr.msc----证书管理实用程序

     calc-----------启动计算器

     charmap--------启动字符映射表

     cliconfg-------SQL SERVER 客户端网络实用程序

     Clipbrd--------剪贴板查看器

     conf-----------启动netmeeting

     compmgmt.msc---计算机管理

     cleanmgr-------垃圾整理

     ciadv.msc------索引服务程序

     osk------------打开屏幕键盘

     odbcad32-------ODBC数据源管理器

     oobe/msoobe /a----检查XP是否激活

     lusrmgr.msc----本机用户和组

     logoff---------注销命令

     iexpress-------木马捆绑工具，系统自带

     Nslookup-------IP地址侦测器

     fsmgmt.msc-----共享文件夹管理器

     utilman--------辅助工具管理器

     gpedit.msc-----组策略

     explorer-------打开资源管理器

     run.exec("cmd.exe /k start " + explorer)   打开资源管理器
     */
    @Test
    public void runbat() {
//        String cmd = "cmd.exe C: & \\\"cd C:/Users/dell/Downloads/ffmpeg-20200617-0b3bd00-win64-static/bin/\\\" \\\"ffmpeg -f gdigrab -i desktop -vcodec libx264 -f flv -s 1980x1080 rtmp://192.168.1.142/live/h264Stream\\\"";
//        String cmd = "ping www.baidu.com";
//        String cmd = "D:" + File.separator + "win64-static" + File.separator + "bin" + File.separator + "ffmpeg -f gdigrab -i desktop -vcodec libx264 -f flv -s 1980x1080 rtmp://192.168.1.142/live/h264Stream";
        String cmd = "cmd.exe /k start D:" + File.separator + "win64-static" + File.separator + "bin" + File.separator + "ffmpeg -f gdigrab -i desktop -vcodec libx264 -f flv -s 1980x1080 rtmp://192.168.1.142/live/h264Stream";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String content = br.readLine();
            while (content != null) {
                System.out.println(content);
                content = br.readLine();
            }
            is.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//            Process ps = Runtime.getRuntime().exec(cmd);
//            InputStream in = ps.getInputStream();
//            int c;
//            while ((c = in.read()) != -1) {
//                System.out.print(c);// 如果你不需要看输出，这行可以注销掉
//            }
//            in.close();
//            ps.waitFor();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        System.out.println("child thread donn");
    }

}
