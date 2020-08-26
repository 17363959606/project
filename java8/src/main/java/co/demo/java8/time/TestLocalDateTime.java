package co.demo.java8.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TestLocalDateTime {
    //Instant:时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）（机器读的数据）

    @Test
    public void test2(){
        Instant ins1 = Instant.now();
        System.out.println(ins1);//默认获取utc时区和中国相差8个时差
        //加8小时时差
        OffsetDateTime offsetDateTime = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //毫秒显示
        long l = ins1.toEpochMilli();
        System.out.println(l);
    }

    //1.LocalDate LocalTime LocalDateTime  java1.8中是线程安全的 人读的
    @Test
    public void test1() {
        //获取当前时间
        LocalDateTime ldf = LocalDateTime.now();
        System.out.println(ldf);
        //设置时间
        LocalDateTime ldf2 = LocalDateTime.of(2020, 06, 12, 16, 05, 22);
        System.out.println(ldf2);
        //在时间年份上加2年
        LocalDateTime ldf3 = ldf.plusYears(2);
        System.out.println(ldf3);
        //在时间上减2分钟
        LocalDateTime ldf4 = ldf.minusMinutes(2);
        System.out.println(ldf4);
        //获取单独时间
        System.out.println(ldf.getHour());

    }

    //Duration:计算2个时间之间的间隔的
    //Period：计算2个日期之间的间隔
    @Test
    public void test3() throws InterruptedException {
        Instant ins1 = Instant.now();
        Thread.sleep(1000);
        Instant ins2 = Instant.now();
        Duration between = Duration.between(ins1, ins2);
        System.out.println(between.toMillis());
        System.out.println("-----------------");
    }
    @Test
    public void test4(){
        LocalDate ld1 = LocalDate.of(2015,1,1);
        LocalDate ld2 = LocalDate.now();
        Period between = Period.between(ld1, ld2);
    }
    //TemporalAdjuster 时间矫正器
    @Test
    public void test5(){
        LocalDateTime ldf = LocalDateTime.now();
        System.out.println(ldf);
        LocalDateTime localDateTime = ldf.withDayOfMonth(10);
        System.out.println(localDateTime);

        LocalDateTime with = ldf.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(with);
    }
}
