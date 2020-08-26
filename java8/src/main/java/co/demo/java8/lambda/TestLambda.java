package co.demo.java8.lambda;

import org.junit.Test;

import java.util.*;

public class TestLambda {

    //原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> comparable = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(comparable);
    }

    //Lambda表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.99),
            new Employee("赵六", 16, 3333.33),
            new Employee("天田七", 8, 7777.77)
    );

    /*
     java 8 之前的处理方式：
     */
    //需求：获取当前公司中员工年龄大于35的员工信息
    @Test
    public void test3() {
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee epm : list) {
            if (epm.getAge() >= 35) {
                emps.add(epm);
            }
        }
        return emps;
    }

    //需求：获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee epm : list) {
            if (epm.getSalary() >= 5000) {
                emps.add(epm);
            }
        }
        return emps;
    }

    //优化方式1：设计模式：策略模式
    @Test
    public void test4() {
        List<Employee> employees = filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (mp.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    //优化方式2：匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() < 5000;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化方式3：
    /*
       java8 新特性： Lambda
     */
    @Test
    public void test6() {
        List<Employee> list = filterEmployee(employees, (e) -> e.getSalary() <= 5000);
        list.forEach(System.out::println);
    }

    //优化方式4: Stream API
    @Test
    public void test7() {
        employees.stream().filter((e) -> e.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("-----------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
