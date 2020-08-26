package cn.demo.proxy.staticproxy;

public class TeacherDaoProxy implements ITeacherDao {
    private ITeacherDao teacherDao;

    //构造一个函数
    public TeacherDaoProxy(ITeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        System.out.println("代理开始前");
        teacherDao.teach();
        System.out.println("代理完成后");
    }
}
