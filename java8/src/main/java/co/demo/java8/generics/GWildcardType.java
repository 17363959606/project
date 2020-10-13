package co.demo.java8.generics;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通配符
 * 无边界通配符：无界通配符 ? 可以适配任何引用类型:
 * <p>
 * 当方法参数需要传入一个泛型时，而且无法确定其类型时。直接使用无具体泛型变量的泛型，
 * 容易造成安全隐患；若在方法代码里进行类型转换，
 * 极容易出现ClassCastException错误那泛型变量用Object代替不就行了？但是泛型类+具体参数转变的ParameterizedType(参数化类型)是不存在继承关系；
 * 即Object是String的父类，但是List<Object>和List<String>的类型是不同的两个ParameterizedType，不存在继承关系。于是有了类型通配符 ？
 * <p>
 * 无界通配符可以匹配任意类型；但是在使用？时，不能给泛型类的变量设置值，
 * 因为我们不知道具体类型是什么；如果强行设置新值，后面的读容易出现ClassCastException错误。
 * 因此编译器限制了**通配符 ？**的泛型只能读不能写
 */
public class GWildcardType<T> {
    @Setter
    @Getter
    T param;

    //上界限定通配符<? extends E>
    /*
    想接收一个List集合，它只能操作数字类型的元素【Float、Integer、Double、Byte等数字类型都行】，
    怎么做？可以使用List<? extends Number的子类>，
    表明List里的元素都是Number的子类
    存在上界通配符，因为具体类型不确定，也是只能读不能写的
     */
    public static void print(List<? extends Number> list) {
        Number n = new Double("1.0");
//        ArrayList<Integer> list1 = new ArrayList<>();
//        list.add(n);

        Number tmp = list.get(0);
    }

    //下界限定通配符<? super E>
    /*
    如果定义了通配符是谁的父类，则是下界限定通配符；
    此类通配符可读可写，转成任意父类都不会出现ClassCastException错误。
     */
    public static void print2() {
        GWildcardType<? super Child> gWildcardType = new GWildcardType<>();
        gWildcardType.setParam(new Child());
//        gWildcardType.setParam(new Parent());
        Object param = gWildcardType.getParam();
        System.out.println(param);
    }

    public static void main(String[] args) {
        GWildcardType<?> test = new GWildcardType<>();
        //只能读不能写
//        test.setParam("set");
        Object param = test.getParam();
    }
}

class Parent {
}

class Child extends Parent {
}