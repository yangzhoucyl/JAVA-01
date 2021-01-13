package homework;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

/**
 * 自定义类加载器
 * @author YangZhou
 */
public class HelloClazzLoader extends ClassLoader{

    /**
     * 字节码数组文件
     */
    private final static String CLAZZ_PATH = "D:\\idea_workspase\\bank\\Java_Week1_01\\Hello.xlass";
    /**
     * 加载类名
     */
    private final static String CLAZZ_NAME = "Hello";
    /**
     * 调用方法名
     */
    private final static String INVOKE_METHOD_NAME = "hello";

    public static void main(String[] args) {
        try {
            Class<?> clazz = new HelloClazzLoader().findClass(CLAZZ_NAME);
            Method method = clazz.getMethod(INVOKE_METHOD_NAME);
            method.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(CLAZZ_PATH);
        try {
            // 从文件中读取字节数组
            byte[] clazzBytes = Files.readAllBytes(file.toPath());
            byte[] handleClazzBytes = new byte[clazzBytes.length];
            for (int i = 0; i < clazzBytes.length; i++) {
                handleClazzBytes[i] = (byte) (255 - clazzBytes[i]);
            }
            return defineClass(name,handleClazzBytes, 0, handleClazzBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
