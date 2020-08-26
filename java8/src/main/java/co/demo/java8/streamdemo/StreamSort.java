package co.demo.java8.streamdemo;

import co.demo.java8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 中间操作
 * 排序：
 * sorted() ==自然排序  Comparable 排序
 * sorted(Comparator com) -- 定制排序
 */
public class StreamSort {
    @Test
    public void test() {
        List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd", "eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("----------------------");
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 38, 5555.99),
                new Employee("王五", 50, 6666.99),
                new Employee("赵六", 16, 3333.33),
                new Employee("天田七", 8, 7777.77),
                new Employee("天田七", 8, 7777.77)
        );
        employees.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        // > 0正序（升序） < 0 降序  默认升序
                        return -(e1.getAge() - e2.getAge());
                    }
                }).forEach(System.out::println);
    }
}
