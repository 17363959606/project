package cn.demo.proxy.staticproxy;

public class TeacherDaoImpl implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("---我被调用了");
    }
}
