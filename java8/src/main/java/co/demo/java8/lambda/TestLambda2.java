package co.demo.java8.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Lambda表达式的基础语法：java8中引入了一个新的操作符“->”该操作符称为箭头操作符或者lambda操作符
 * 箭头操作符将lambda表达式拆分两个部分
 * <p>
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需要执行的功能，即Lambda体
 * <p>
 * 语法格式1： 无参数，无返回值
 * () -> System.out.println("hello lambda!");
 * 语法格式2：有一个参数，无返回值
 * (x) -> System.out.println("hello lambda!");
 * <p>
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * x -> System.out.println("hello lambda!");
 * 语法格式四：有两个以上的参数，有返回值，并且lambda体中有多条语句
 * Comparator<Integer> comparator = (x, y) -> {
 * System.out.println("函数式接口");
 * return Integer.compare(x, y);
 * };
 * <p>
 * 语法格式五：若lambda体中只有一条语句,return 和 {}可以省略不写
 * Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为jvm编译器通过上下文推断出，
 * 数据类型 即“类型推断”
 * Comparator<Integer> comparator = (Integer x,Integer y) -> Integer.compare(x, y);
 * <p>
 * 二：lambda表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
 * 可以使用注解：@FunctionalInterface修饰 可以检查是否是函数式接口
 */
public class TestLambda2 {
    @Test
    public void test1() {
        //匿名内部类在调用同级别的局部变量时变量必须定义为：final，
        final int num = 0;//jdk 1.7前，必须是final
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello" + num);
            }
        };
        r.run();
        System.out.println("-----------");
        Runnable r1 = () -> System.out.println("hello" + num);
        r1.run();
    }

    @Test
    public void test2() {
        Consumer<String> d = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("xssss");
            }
        };
        d.accept("");

        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("lambda xssss");

        Consumer<String> com = x -> System.out.println(x);
        con.accept("lambda 3 xssss");
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.99),
            new Employee("赵六", 16, 3333.33),
            new Employee("天田七", 8, 7777.77)
    );

    @Test
    public void test5() {
//        Collections.sort(employees, new Comparator<Employee>() {
//
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                System.out.println("o1:" + o1.getAge() + " o2:" + o2.getAge() + "," + (o1.getAge() - o2.getAge()));
//                return o1.getAge() - o2.getAge();
//            }
//        });
//        System.out.println(employees);

        Collections.sort(employees, (x, y) -> y.getAge() - x.getAge());
        System.out.println(employees);
    }
}
