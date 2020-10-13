package co.demo.java8.generics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * 6 泛型变量TypeVariable
 * <p>
 * （先临时定义一个名称，Test<E>里的E为泛型参数）；
 * 泛型变量TypeVariable：泛型的泛型参数就是TypeVariable；
 * 当父类使用子类的泛型参数指定自身的泛型参数时；
 * 或者泛型属性定义在泛型类A<T>中，并使用泛型类A<T>的泛型参数T时，
 * 其泛型参数都会被编译器定为泛型变量TypeVariable，而不是被擦除
 * <p>
 */
public class GenericsTypeVariable<T> {
    List<T> param;

    static class SubTest<R> extends GenericsTypeVariable<R> {
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Class<GenericsTypeVariable> genericsTypeVariableClass = GenericsTypeVariable.class;
        TypeVariable<Class<GenericsTypeVariable>>[] typeParameters = genericsTypeVariableClass.getTypeParameters();
        Field field = genericsTypeVariableClass.getDeclaredField("param");
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        //interface List<E> 的泛型类型E被T具体化，因此其被识别为TypeVariable
        TypeVariable actualTypeArguments = (TypeVariable) genericType.getActualTypeArguments()[0];
        ParameterizedType genericSuperclass = (ParameterizedType) SubTest.class.getGenericSuperclass();
        TypeVariable actualTypeArgument = (TypeVariable) genericSuperclass.getActualTypeArguments()[0];

    }
}
