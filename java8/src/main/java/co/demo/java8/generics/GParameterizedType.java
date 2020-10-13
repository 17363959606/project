package co.demo.java8.generics;


import java.lang.reflect.Type;

/**
 * 参数化类型ParameterizedType
 * 需要注意的点，我们不能直接获取指定具体参数的泛型的类型，
 * 如Class clazz = List<String>.class编译时不通过的；还有就是直接通过泛型类new创建的对象，
 * 其Class并非ParameterizedType类型，而是泛型本身的class，
 */
public class GParameterizedType<T> {

    public static void main(String[] args) {
        GParameterizedType<String> str = new GParameterizedType<>();
        Class<? extends GParameterizedType> aClass = str.getClass();
        Type genericSuperclass = aClass.getGenericSuperclass();
        System.out.println("genericSuperclass：" + genericSuperclass);
    }
}
