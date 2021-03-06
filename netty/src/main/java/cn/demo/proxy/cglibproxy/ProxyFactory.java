package cn.demo.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {

    //创建一个被代理对象
    private Object target;


    //构造一个函数，生成被代理对象
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //返回一个代理对象：target
    public Object getProxyInterceptor() {
        //1.创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.创建子类对象，即被代理对象
        return enhancer.create();
    }
    //重写intercept方法，会调用目标对象的方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 代理开始");
        Object invoke = method.invoke(target, objects);
        System.out.println("cglib 代理结束");
        return invoke;
    }
}
