package co.demo.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置的四大核心函数式接口
 * <p>
 * Consumer<T> : 消费型接口
 * void accept<T t>
 * <p>
 * Supplier<T> : 供给型接口
 * T get()
 * <p>
 * Function<T, R> : 函数型接口
 * R apply(T t)
 * <p>
 * Predicate<T> :断言型接口
 * boolean test(T t);
 */
public class TestLambda4 {
    // Consumer<T> : 消费型接口
    @Test
    public void test() {
        happy(10000, (x) -> System.out.println("大保健消费：" + x + "元"));

        Consumer consumer = (x) -> System.out.println("大保健消费：" + x + "元");
        consumer.accept(100000);
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    // Supplier<T> : 供给型接口
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }

    //需求：产生指定整数并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    //Function<T, R> : 函数型接口
    @Test
    public void test3() {
        String y = "妈的";
        Function<String, String> str = (x) -> x + y;
        String result = str.apply("我叼");
        System.out.println(result);
//        String s = strHandler("我叼你妈得", (x) -> x + ".");
//        System.out.println(s);
    }

    //需求：用户处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    //Predicate<T> :断言型接口
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "asb", "hahah", "12");
        List<String> list1 = filterStr(list, (s) -> s.length() > 3);
        System.out.println(list1);
    }

    //需求：将满足条件的字符串放入集合中去
    public List<String> filterStr(List<String> list, Predicate<String> p) {
        List<String> strings = new ArrayList<>();
        for (String str : list) {
            if (p.test(str)) {
                strings.add(str);
            }
        }
        return strings;
    }
}
