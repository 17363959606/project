package co.demo.java8.optional;

import co.demo.java8.lambda.Employee;
import org.junit.Test;

import java.util.Optional;
import java.util.OptionalLong;

public class TestOptional {
    /**
     * Optional 容器类的常用方法
     * Optional.of(T t) :创建一个Optional实例
     * Optional.empty() :创建一个空的Optional实例
     * Optional.ofNullable(T t):若t不为null,创建Optional实例，否则创建空实例
     * isPresent():判断是否包含值
     * orElse(T t): 如果调用对象包含值，返回该值，否则返回t
     * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s获取的值
     * map(Function f) : 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     * flatMap(Function mapper): 与map类似，要求返回值必须是Optional
     *
     */

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);
    }
    @Test
    public void test2(){
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }
    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(null);
        if (op.isPresent()){
            System.out.println(op.get());
        }
        Employee employee = op.orElse(new Employee("12", 12, 12));
        System.out.println(employee);
    }
}
