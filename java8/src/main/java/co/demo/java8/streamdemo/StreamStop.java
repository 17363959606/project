package co.demo.java8.streamdemo;

import co.demo.java8.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * 查找与匹配
 * allMatch --- 检查是否匹配所以元素
 * anyMatch --- 检查是否至少匹配一个元素
 * noneMatch --- 检查是否没有匹配所有元素
 * findFirst --- 返回第一个元素
 * findAny --- 返回当前流中的任意元素
 * count --- 返回流中的元素总个数
 * max --- 返回流中最大值
 * min --- 返回流中最小值
 * <p>
 * 规约
 * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) --可以将流中元素反复结合起来，得到一个值
 * <p>
 * 收集
 * collect --将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
 */
public class StreamStop {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 100000),
            new Employee("王五", 50, 6666.99),
            new Employee("天田七5", 5, 7777.77),
            new Employee("赵六", 16, 3333.33),
            new Employee("天田七", 8, 7777.77)

//                new Employee("天田七", 8, 7777.77)
    );

    @Test
    public void test4() {
        employees.stream().forEach((x) -> {
            x.setAge(x.getAge() == 0 ? 1 : x.getAge());
            x.setName(x.getName().equals("李四") ? "李四爸爸" : x.getName());
        });
    }

    @Test
    public void test() {
//        boolean b1 = employees.stream().allMatch((x) -> x.getName().equals("张三"));
//        System.out.println(b1);
//        boolean b2 = employees.stream().anyMatch((x) -> x.getName().equals("张三"));
//        System.out.println(b2);
//        boolean b3 = employees.stream().noneMatch((e) -> e.getName().equals("张三"));
//        System.out.println(b3);
//        Optional<Employee> first = employees.stream().findFirst();
//        System.out.println(first.get());
//        Optional<Employee> any = employees.stream().findAny();
//        System.out.println(any.get());

        Optional<Employee> max = employees.stream()
                .max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(max.get());

        Optional<Employee> min1 = employees.stream()
                .min((x, y) -> Integer.compare(x.getAge(), y.getAge()));
        System.out.println(min1.get());
    }

    //规约
    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        System.out.println("--------------");
        //求工资的总和 1 这个有初始值为0 所以不会有空值，直接返回Integer
        Integer reduce2 = employees.stream()
                .map(Employee::getAge)
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce2);
        //有可能为空 所以才会封装到Optional中去
        Optional<Integer> reduce1 = employees.stream().map(Employee::getAge).reduce((x, y) -> x + y);
        System.out.println(reduce1.get());

    }

    //收集
    @Test
    public void test3() {
        List<String> collect = employees.stream().map(Employee::getName).collect(Collectors.toList());
        Set<String> collect1 = employees.stream().map(Employee::getName).collect(Collectors.toCollection(() -> new HashSet<>()));
        collect.forEach(System.out::println);
    }
}
