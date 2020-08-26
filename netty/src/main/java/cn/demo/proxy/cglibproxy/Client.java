package cn.demo.proxy.cglibproxy;

public class Client {
    public static void main(String[] args) {
        //实例化一个目标对象
        TeacherDao target = new TeacherDao();
        //生成一个代理对象
        TeacherDao proxyFactory = (TeacherDao) new ProxyFactory(target).getProxyInterceptor();
        //调用目标方法
        proxyFactory.teach();
    }
}
