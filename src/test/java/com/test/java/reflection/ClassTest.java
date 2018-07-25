package com.test.java.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.test.java.clazz.polimophic.entity.Employee;
import com.test.java.clazz.polimophic.entity.Manager;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    public void printConstructor() {
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
     * 该类声明的全部方法。 public java.lang.String getName(); public int getSalary(); public void
     * setBonus(arg0); public int getBonus();
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
     * 该类中声明的全部字段。 private int bonus;
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

    @Test
    public void getFieldVal() throws NoSuchFieldException, IllegalAccessException {
        Manager manager = new Manager("a", 1, 100);
        Class<? extends Manager> clazz = manager.getClass();
        Field bonus = clazz.getDeclaredField("bonus");
        bonus.setAccessible(true);
        Object bonusVal = bonus.get(manager);
        System.out.println((int) bonusVal);
        System.out.println(bonus.getInt(manager));
    }

    @Test
    public void writeFiledVal() throws NoSuchFieldException, IllegalAccessException {
        Manager manager = new Manager("a", 1, 100);
        Class<? extends Manager> clazz = manager.getClass();
        Field bonus = clazz.getDeclaredField("bonus");
        bonus.setAccessible(true);
        bonus.set(manager, 1000);

        assertEquals(1000, manager.getBonus());
    }

    @Test
    public void newInstanceTest()
        throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class clazz = Manager.class;
        Constructor constructor = clazz.getConstructor(String.class, int.class, int.class);
        Manager instance = (Manager) constructor.newInstance("a", 1, 1);
        assertEquals(1, instance.getBonus());
    }

    @Test
    public void invokeMethod()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Manager manager = new Manager("a", 1, 100);
        Class<? extends Manager> clazz = manager.getClass();
        Method method = clazz.getDeclaredMethod("getBonus");
        int rs = (int) method.invoke(manager);
        assertEquals(100, rs);

        Method setBonus = clazz.getDeclaredMethod("setBonus", int.class);
        Object invoke = setBonus.invoke(manager, 0);
        assertNull(invoke);
        assertEquals(0, manager.getBonus());
    }

    @Test
    public void newArray() {
        int[] a = {1, 2, 3};
        int[] a2 = (int[]) goodCopyOf(a, 2);
        assertEquals(1, a2[0]);
        assertEquals(2, a2[1]);

        String[] str = {"a", "b", "c"};
        String[] str2 = (String[]) goodCopyOf(str, 2);
        assertEquals("a", str2[0]);
        assertEquals("b", str2[1]);
    }

    private Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if (!cl.isArray()) {
            return null;
        }

        int length = Array.getLength(a);
        Object newArray = Array.newInstance(cl.getComponentType(), newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }

}
