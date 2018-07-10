package com.test.java.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.test.java.clazz.polimophic.entity.Employee;
import com.test.java.clazz.polimophic.entity.Manager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import org.junit.Test;

/**
 * @author Ryan Miao at 2018-07-09 19:22
 **/
public class ClassTest {

    @Test
    public void testClass() throws ClassNotFoundException {
        Employee employee = new Employee("a", 1);

        Class clazz = Employee.class;

        Class<? extends Employee> aClass = employee.getClass();
        String name = aClass.getName();
        assertEquals("com.test.java.clazz.polimophic.entity.Employee", name);

        //通过名字加载
        Class<?> fromName = Class.forName("com.test.java.clazz.polimophic.entity.Employee");

        assertTrue(clazz == aClass);
        assertEquals(clazz, aClass);
        assertEquals(clazz, fromName);

        String doubleArrayName = Double[].class.getName();
        assertEquals("[Ljava.lang.Double;", doubleArrayName);

        String intArrayName = int[].class.getName();
        assertEquals("[I", intArrayName);

    }

    @Test
    public void printClazz() {
        StringBuilder sb = new StringBuilder();
        Class clazz = Employee.class;
        Class superclass = clazz.getSuperclass();
        String modifiers = Modifier.toString(clazz.getModifiers());
        if (modifiers.length() > 0) {
            sb.append(modifiers).append(" ");
        }

        sb.append("class ").append(clazz.getName());

        if (superclass != null && superclass != Object.class) {
            sb.append(" extends ").append(superclass.getName());
        }

        assertEquals("public class com.test.java.clazz.polimophic.entity.Employee", sb.toString());
    }

    @Test
    public void prinConstructor() {
        StringBuilder sb = new StringBuilder();
        Class clazz = Manager.class;
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            String name = declaredConstructor.getName();
            String modifiers = Modifier.toString(clazz.getModifiers());
            if (modifiers.length() > 0) {
                sb.append(modifiers).append(" ");
            }
            sb.append(name).append("(");

            //打印参数
            Class[] parameterTypes = declaredConstructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(parameterTypes[i].getName());
            }
            sb.append(");");
        }

        assertEquals(
            "public com.test.java.clazz.polimophic.entity.Manager(java.lang.String, int, int);",
            sb.toString());
    }

    /**
     * public java.lang.String getName(); public int getSalary(); public void setBonus(arg0); public
     * int getBonus();
     */
    @Test
    public void printMethod() {
        Class clazz = Manager.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            String name = method.getName();

            System.out.print(" ");
            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }

            System.out.print(returnType.getName() + " " + name + "(");

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameters[i].getName());
            }
            System.out.print(");\n");
        }
    }

    /**
     *  private int bonus;
     */
    @Test
    public void printFields() {
        Class clazz = Manager.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Class<?> type = declaredField.getType();
            String name = declaredField.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(declaredField.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name + ";");
        }
    }

}
