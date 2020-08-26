package cn.demo.proxy.dynamicproxy;


public class Client {
    public static void main(String[] args) {
        //实例化目标对象
        ITeacherDao1 teacherDaoImpl1 = new TeacherDaoImpl1();

        //初始化代理对象
        ProxyFactory factory = new ProxyFactory(teacherDaoImpl1);
        ITeacherDao1 o = (ITeacherDao1) factory.ProxyInstance();
//        o.teach();
        o.teach1("haha");
    }

}
