package co.demo.java8.streamdemo;

import co.demo.java8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStream2 {
    //中间操作
    /**
     * 筛选与切片
     * filter ---接受Lambda从流中排除某些元素
     * limit---截断流使其元素不超过给定数量
     * skip(n)----跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流
     * 与Limit(n)互补
     * distinct ---筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     *
     */

    /**
     * 映射
     * map --接收lambda，将元素转换成其他形式或提取信息、接收一个函数作为信息，该函数会被应用每个元素上，并将其映射成一个新的元素
     * flatMap---接收一个函数作为参数将流中的每个值都换成另一个流然后把所有流连接成一个流
     */

    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream().map((x) -> x.toUpperCase())
                .forEach(System.out::println);

        employees.stream().map((x) -> x.getName())
                .forEach(System.out::println);

        employees.stream().map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("-------------------");
        Stream<Stream<Character>> streamStream = list.stream().map(TestStream2::filterCharacter);
        streamStream.forEach((x) -> {
            x.forEach(System.out::println);
        });
        System.out.println("-------------------");

        Stream<Character> sm = list.stream().flatMap(TestStream2::filterCharacter);
        sm.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.99),
            new Employee("赵六", 16, 3333.33),
            new Employee("天田七", 8, 7777.77),
            new Employee("天田七", 8, 7777.77)
    );

    @Test
    public void test() {
        //中间操作不会执行任何操作
        Stream<Employee> employeeStream = employees.stream()
                .filter((x) -> x.getAge() > 35);
        //终止操作：一次性执行全部内容，即”惰性求值“
        employeeStream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        employees.stream().filter((e) -> {
            System.out.println("执行中");
            return e.getAge() > 38;
        }).limit(2).forEach(System.out::println);
    }

    @Test
    public void test4() {
        employees.stream().filter((x) -> x.getSalary() > 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

}
