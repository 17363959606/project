package co.demo.java8.generics;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 泛型数组
 * <p>
 * GenericArrayType，泛型数组，
 * 描述的是ParameterizedType类型以及TypeVariable类型数组，
 * 即形如：Test<T>[][]、T[]等，是GenericArrayType的子接口
 */
public class GGenericArrayType<T> {
    T[] param;

    public static void main(String[] args) throws NoSuchFieldException {
        Class<GGenericArrayType> gGenericArrayTypeClass = GGenericArrayType.class;
        Field param = gGenericArrayTypeClass.getDeclaredField("param");
        GenericArrayType genericType = (GenericArrayType) param.getGenericType();
        TypeVariable genericComponentType = (TypeVariable) genericType.getGenericComponentType();
    }
}
