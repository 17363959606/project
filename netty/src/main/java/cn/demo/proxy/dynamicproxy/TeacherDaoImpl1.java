package cn.demo.proxy.dynamicproxy;

public class TeacherDaoImpl1 implements ITeacherDao1 {

    @Override
    public void teach() {
        System.out.println("授课咯");
    }

    @Override
    public String teach1(String msg) {
        System.out.println("授课咯1111");
        return "授课咯1111";
    }
}
