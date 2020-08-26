package co.demo.java8.streamdemo;

import co.demo.java8.lambda.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
public class StreamDemo {
    /**
     * 1. 给定一个数字列表，如何返回一个由每个数的平方构成的列表?
     * ,给定【1,2,3,4,5】,应该返回【1,4,9,16,25】
     */
    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.stream()
                .map((x) -> x * x)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 100000),
            new Employee("王五", 50, 6666.99),
            new Employee("天田七5", 5, 7777.77),
            new Employee("赵六", 16, 3333.33),
            new Employee("天田七", 8, 7777.77)

//                new Employee("天田七", 8, 7777.77)
    );

    /**
     * 2.怎么样用map和reduce方法数一数流中有多少个Employee呢
     */
    @Test
    public void test1() {
        Optional<Integer> reduce = employees.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());

        long count = employees.stream().count();
        System.out.println(count);
    }
}
