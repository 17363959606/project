package co.demo.java8.streamdemo;

import co.demo.java8.lambda.Employee;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 一：Stream的三个操作步骤
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStream {
    //创建Stream
    @Test
    public void test1() {
        //1.通过Collection系列集合提供的Stream()'串行流'和parallelStream()'并行流'
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2。通过Array中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        //3.通过Stream类中的静态方法of()
        Stream<String> stringStream = Stream.of("1", "2", "3");

        //4.创建无限流

        Stream<Integer> iterate = Stream.iterate(0, (x) -> 0 + 2);
        iterate.limit(2).forEach(System.out::println);
        Stream.generate(() -> Math.random())
                .limit(2).forEach(System.out::println);
    }

    //并行流处理大数据
    @Test
    public void test2() {
        Instant start = Instant.now();
        Long sum = 0l;
        long reduce = LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时间：" + Duration.between(start, end).toMillis());//1525ms
    }

    //串行流处理大数据
    @Test
    public void test3() {
        Instant start = Instant.now();
        Long sum = 0l;
        long reduce = LongStream.rangeClosed(0, 10000000000L)
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时间：" + Duration.between(start, end).toMillis());//4466ms
    }
}
