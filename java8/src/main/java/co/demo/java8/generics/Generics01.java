package co.demo.java8.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型知识： 1.类型体系
 * 先了解下java的Type类型体系(类的类=>类型)，Type是所有类型（原生类型-Class、参数化类型-ParameterizedType、数组类型-GenericArrayType、类型变量-TypeVariable、基本类型-Class）的共同接口;
 * Type下面又有四个子接口类ParameterizedType、TypeVariable、GenericArrayType、WildcardType
 * <p>
 * List<E>表示泛型，E是TypeVariable类型，
 * List<String>则是ParameterizedType(参数化类型)，
 * List<String>里的String称为实际参数类型具体化泛型中的类型时，可以使用 ? extends 或 ? super来表示继承关系；如List<? extends Data>，
 * 而里面的 ? 称为通配符类型WildcardTypeGenericArrayType 表示一种元素类型是ParameterizedType（参数化类型）或者TypeVariable（类型变量）的数组类型，如T[] 或者 List<E>[]
 * 注解是JDK1.5才出现了的，为了表示被注解的类型的，加入AnnotatedElement类型，字面意思就是被注解的元素。JDK1.8又有了AnnotatedType将Type和被注解元素的概念关联起来。
 * AnnotatedType也有四个子接口，和Type的四个子接口一一对应，如：ParameterizedType类型被注解则被编译器解析成AnnotatedParameterizedType: @AnTest("list")List<String>list
 * 2.泛型的概念
 * Java 泛型（generics）是JDK1.5中引入的一个新特性，其本质是参数化类型，解决不确定具体对象类型的问题;其所操作的数据类型被指定为一个参数（type parameter）这种参数类型可以用在类、接口和方法的创建中，分别称为泛型类、泛型接口、泛型方法
 * <p>
 * 泛型: 把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊的类型
 * <p>
 * 接口和抽象类都可以使用泛型
 * 3.类型擦除
 * 创建泛型的实例时，jvm是会把具体类型擦除的；编译生成的字节码中不包含泛型中的类型参数，即ArrayList<String>和ArrayList<Integer>都擦除成了ArrayList，
 * 也就是被擦除成"原生类型"，这就是泛型擦除
 * 4. 泛型的继承
 * 子类可以指定父类的泛型参数，可以是已知类（Integer、String等），
 * 也可以用子类自己的泛型参数指定泛型被继承时，且指定父类泛型参数，
 * 则额外生成的ParameterizedType类型作为子类的父类；如果没有指定父类泛型参数，则直接继承原生类型
 */

//泛型类的定义
public class Generics01<T> {
    private T param;

    //泛型方法的定义
    private <T> T demo(T t) {
        System.out.println(t);
        return t;
    }

    //类型擦除
    private void demo2() {
        List<String> strArr = new ArrayList<>();
        List<Integer> intArr = new ArrayList<>();
        Type strClass = strArr.getClass();
        Class<? extends List> intClass = intArr.getClass();
        System.out.println("strClass: " + strClass);
        System.out.println("intClass: " + intClass);
    }

    //泛型的继承
    static public class SubTest extends Generics01<String> {
    }

    static public class SubTest2<R> extends Generics01<R> {
    }

    //SubTest3继承原生类型
    static public class SubTest3 extends Generics01 {
    }

    public static void main(String[] args) {
        Generics01<String> generics01 = new Generics01<String>();
        String k = generics01.demo("k");
        System.out.println(k);
        generics01.demo2();
        Type genericSuperclass = generics01.getClass().getGenericSuperclass();
    }
}
