package com.zj.tools.mylibrary;

import com.zj.tools.mylibrary.reflect.PrivateConstructors;
import com.zj.tools.mylibrary.reflect.Test1;
import com.zj.tools.mylibrary.reflect.Test10;
import com.zj.tools.mylibrary.reflect.Test2;
import com.zj.tools.mylibrary.reflect.Test3;
import com.zj.tools.mylibrary.reflect.Test4;
import com.zj.tools.mylibrary.reflect.Test5;
import com.zj.tools.mylibrary.reflect.Test6;
import com.zj.tools.mylibrary.reflect.Test7;
import com.zj.tools.mylibrary.reflect.Test8;
import com.zj.tools.mylibrary.reflect.Test9;
import com.zj.tools.mylibrary.reflect.TestHierarchicalMethodsBase;
import com.zj.tools.mylibrary.reflect.TestHierarchicalMethodsSubclass;
import com.zj.tools.mylibrary.reflect.TestPrivateStaticFinal;

import static org.junit.Assert.*;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/12/15
 *     desc  : ZJReflectUtils 单元测试
 * </pre>
 */
public class ZJReflectUtilsTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void reflect() {
        Assert.assertEquals(
                ZJReflectUtils.reflect(Object.class),
                ZJReflectUtils.reflect("java.lang.Object", ClassLoader.getSystemClassLoader())
        );
        assertEquals(
                ZJReflectUtils.reflect(Object.class),
                ZJReflectUtils.reflect("java.lang.Object")
        );
        assertEquals(
                ZJReflectUtils.reflect(String.class).get(),
                ZJReflectUtils.reflect("java.lang.String").get()
        );
        assertEquals(
                Object.class,
                ZJReflectUtils.reflect(Object.class).get()
        );
        assertEquals(
                "abc",
                ZJReflectUtils.reflect((Object) "abc").get()
        );
        assertEquals(
                1,
                ZJReflectUtils.reflect(1).get()
        );
    }

    @Test
    public void newInstance() {
        assertEquals(
                "",
                ZJReflectUtils.reflect(String.class).newInstance().get()
        );
        assertEquals(
                "abc",
                ZJReflectUtils.reflect(String.class).newInstance("abc").get()
        );
        assertEquals(
                "abc",
                ZJReflectUtils.reflect(String.class).newInstance("abc".getBytes()).get()
        );
        assertEquals(
                "abc",
                ZJReflectUtils.reflect(String.class).newInstance("abc".toCharArray()).get()
        );
        assertEquals(
                "b",
                ZJReflectUtils.reflect(String.class).newInstance("abc".toCharArray(), 1, 1).get()
        );
    }

    @Test
    public void newInstancePrivate() {
        assertNull(ZJReflectUtils.reflect(PrivateConstructors.class).newInstance().field("string").get());

        assertEquals(
                "abc",
                ZJReflectUtils.reflect(PrivateConstructors.class).newInstance("abc").field("string").get()
        );
    }

    @Test
    public void newInstanceNull() {
        Test2 test2 = ZJReflectUtils.reflect(Test2.class).newInstance((Object) null).get();
        assertNull(test2.n);
    }

    @Test
    public void newInstanceWithPrivate() {
        Test7 t1 = ZJReflectUtils.reflect(Test7.class).newInstance(1).get();
        assertEquals(1, (int) t1.i);
        assertNull(t1.s);

        Test7 t2 = ZJReflectUtils.reflect(Test7.class).newInstance("a").get();
        assertNull(t2.i);
        assertEquals("a", t2.s);

        Test7 t3 = ZJReflectUtils.reflect(Test7.class).newInstance("a", 1).get();
        assertEquals(1, (int) t3.i);
        assertEquals("a", t3.s);
    }

    @Test
    public void newInstanceAmbiguity() {
        Test2 test;

        test = ZJReflectUtils.reflect(Test2.class).newInstance().get();
        assertEquals(null, test.n);
        assertEquals(Test2.ConstructorType.NO_ARGS, test.constructorType);

        test = ZJReflectUtils.reflect(Test2.class).newInstance("abc").get();
        assertEquals("abc", test.n);
        assertEquals(Test2.ConstructorType.OBJECT, test.constructorType);

        test = ZJReflectUtils.reflect(Test2.class).newInstance(new Long("1")).get();
        assertEquals(1L, test.n);
        assertEquals(Test2.ConstructorType.NUMBER, test.constructorType);

        test = ZJReflectUtils.reflect(Test2.class).newInstance(1).get();
        assertEquals(1, test.n);
        assertEquals(Test2.ConstructorType.INTEGER, test.constructorType);

        test = ZJReflectUtils.reflect(Test2.class).newInstance('a').get();
        assertEquals('a', test.n);
        assertEquals(Test2.ConstructorType.OBJECT, test.constructorType);
    }

    @Test
    public void method() {
        // instance methods
        assertEquals(
                "",
                ZJReflectUtils.reflect((Object) " ").method("trim").get()
        );
        assertEquals(
                "12",
                ZJReflectUtils.reflect((Object) " 12 ").method("trim").get()
        );
        assertEquals(
                "34",
                ZJReflectUtils.reflect((Object) "1234").method("substring", 2).get()
        );
        assertEquals(
                "12",
                ZJReflectUtils.reflect((Object) "1234").method("substring", 0, 2).get()
        );
        assertEquals(
                "1234",
                ZJReflectUtils.reflect((Object) "12").method("concat", "34").get()
        );
        assertEquals(
                "123456",
                ZJReflectUtils.reflect((Object) "12").method("concat", "34").method("concat", "56").get()
        );
        assertEquals(
                2,
                ZJReflectUtils.reflect((Object) "1234").method("indexOf", "3").get()
        );
        assertEquals(
                2.0f,
                (float) ZJReflectUtils.reflect((Object) "1234").method("indexOf", "3").method("floatValue").get(),
                0.0f
        );
        assertEquals(
                "2",
                ZJReflectUtils.reflect((Object) "1234").method("indexOf", "3").method("toString").get()
        );

        // static methods
        assertEquals(
                "true",
                ZJReflectUtils.reflect(String.class).method("valueOf", true).get()
        );
        assertEquals(
                "1",
                ZJReflectUtils.reflect(String.class).method("valueOf", 1).get()
        );
        assertEquals(
                "abc",
                ZJReflectUtils.reflect(String.class).method("valueOf", "abc".toCharArray()).get()
        );
        assertEquals(
                "abc",
                ZJReflectUtils.reflect(String.class).method("copyValueOf", "abc".toCharArray()).get()
        );
        assertEquals(
                "b",
                ZJReflectUtils.reflect(String.class).method("copyValueOf", "abc".toCharArray(), 1, 1).get()
        );
    }

    @Test
    public void methodVoid() {
        // instance methods
        Test4 test4 = new Test4();
        assertEquals(
                test4,
                ZJReflectUtils.reflect(test4).method("i_method").get()
        );

        // static methods
        assertEquals(
                Test4.class,
                ZJReflectUtils.reflect(Test4.class).method("s_method").get()
        );
    }

    @Test
    public void methodPrivate() {
        // instance methods
        Test5 test8 = new Test5();
        assertEquals(
                test8,
                ZJReflectUtils.reflect(test8).method("i_method").get()
        );

        // static methods
        assertEquals(
                Test5.class,
                ZJReflectUtils.reflect(Test5.class).method("s_method").get()
        );
    }

    @Test
    public void methodNullArguments() {
        Test6 test9 = new Test6();
        ZJReflectUtils.reflect(test9).method("put", "key", "value");
        assertTrue(test9.map.containsKey("key"));
        assertEquals("value", test9.map.get("key"));

        ZJReflectUtils.reflect(test9).method("put", "key", null);
        assertTrue(test9.map.containsKey("key"));
        assertNull(test9.map.get("key"));
    }

    @Test
    public void methodSuper() {
        TestHierarchicalMethodsSubclass subclass = new TestHierarchicalMethodsSubclass();
        assertEquals(
                TestHierarchicalMethodsBase.PUBLIC_RESULT,
                ZJReflectUtils.reflect(subclass).method("pub_base_method", 1).get()
        );

        assertEquals(
                TestHierarchicalMethodsBase.PRIVATE_RESULT,
                ZJReflectUtils.reflect(subclass).method("very_priv_method").get()
        );
    }

    @Test
    public void methodDeclaring() {
        TestHierarchicalMethodsSubclass subclass = new TestHierarchicalMethodsSubclass();
        assertEquals(
                TestHierarchicalMethodsSubclass.PRIVATE_RESULT,
                ZJReflectUtils.reflect(subclass).method("priv_method", 1).get()
        );

        TestHierarchicalMethodsBase baseClass = new TestHierarchicalMethodsBase();
        assertEquals(
                TestHierarchicalMethodsBase.PRIVATE_RESULT,
                ZJReflectUtils.reflect(baseClass).method("priv_method", 1).get()
        );
    }

    @Test
    public void methodAmbiguity() {
        Test3 test;

        test = ZJReflectUtils.reflect(Test3.class).newInstance().method("method").get();
        assertEquals(null, test.n);
        assertEquals(Test3.MethodType.NO_ARGS, test.methodType);

        test = ZJReflectUtils.reflect(Test3.class).newInstance().method("method", "abc").get();
        assertEquals("abc", test.n);
        assertEquals(Test3.MethodType.OBJECT, test.methodType);

        test = ZJReflectUtils.reflect(Test3.class).newInstance().method("method", new Long("1")).get();
        assertEquals(1L, test.n);
        assertEquals(Test3.MethodType.NUMBER, test.methodType);

        test = ZJReflectUtils.reflect(Test3.class).newInstance().method("method", 1).get();
        assertEquals(1, test.n);
        assertEquals(Test3.MethodType.INTEGER, test.methodType);

        test = ZJReflectUtils.reflect(Test3.class).newInstance().method("method", 'a').get();
        assertEquals('a', test.n);
        assertEquals(Test3.MethodType.OBJECT, test.methodType);
    }

    @Test
    public void field() {
        // instance field
        Test1 test1 = new Test1();
        ZJReflectUtils.reflect(test1).field("I_INT1", 1);
        assertEquals(1, ZJReflectUtils.reflect(test1).field("I_INT1").get());

        ZJReflectUtils.reflect(test1).field("I_INT2", 1);
        assertEquals(1, ZJReflectUtils.reflect(test1).field("I_INT2").get());

        ZJReflectUtils.reflect(test1).field("I_INT2", null);
        assertNull(ZJReflectUtils.reflect(test1).field("I_INT2").get());

        // static field
        ZJReflectUtils.reflect(Test1.class).field("S_INT1", 1);
        assertEquals(1, ZJReflectUtils.reflect(Test1.class).field("S_INT1").get());

        ZJReflectUtils.reflect(Test1.class).field("S_INT2", 1);
        assertEquals(1, ZJReflectUtils.reflect(Test1.class).field("S_INT2").get());

        ZJReflectUtils.reflect(Test1.class).field("S_INT2", null);
        assertNull(ZJReflectUtils.reflect(Test1.class).field("S_INT2").get());

        // hierarchies field
        TestHierarchicalMethodsSubclass test2 = new TestHierarchicalMethodsSubclass();

        ZJReflectUtils.reflect(test2).field("invisibleField1", 1);
        assertEquals(1, ZJReflectUtils.reflect(test2).field("invisibleField1").get());

        ZJReflectUtils.reflect(test2).field("invisibleField2", 1);
        assertEquals(1, ZJReflectUtils.reflect(test2).field("invisibleField2").get());

        ZJReflectUtils.reflect(test2).field("invisibleField3", 1);
        assertEquals(1, ZJReflectUtils.reflect(test2).field("invisibleField3").get());

        ZJReflectUtils.reflect(test2).field("visibleField1", 1);
        assertEquals(1, ZJReflectUtils.reflect(test2).field("visibleField1").get());

        ZJReflectUtils.reflect(test2).field("visibleField2", 1);
        assertEquals(1, ZJReflectUtils.reflect(test2).field("visibleField2").get());

        ZJReflectUtils.reflect(test2).field("visibleField3", 1);
        assertEquals(1, ZJReflectUtils.reflect(test2).field("visibleField3").get());
    }

    @Test
    public void fieldPrivate() {
        class Foo {
            private String bar;
        }
        Foo foo = new Foo();
        ZJReflectUtils.reflect(foo).field("bar", "FooBar");
        assertThat(foo.bar, Matchers.is("FooBar"));
        assertEquals("FooBar", ZJReflectUtils.reflect(foo).field("bar").get());

        ZJReflectUtils.reflect(foo).field("bar", null);
        assertNull(foo.bar);
        assertNull(ZJReflectUtils.reflect(foo).field("bar").get());
    }

    @Test
    public void fieldFinal() {
        // instance field
        Test8 test11 = new Test8();
        ZJReflectUtils.reflect(test11).field("F_INT1", 1);
        assertEquals(1, ZJReflectUtils.reflect(test11).field("F_INT1").get());

        ZJReflectUtils.reflect(test11).field("F_INT2", 1);
        assertEquals(1, ZJReflectUtils.reflect(test11).field("F_INT2").get());

        ZJReflectUtils.reflect(test11).field("F_INT2", null);
        assertNull(ZJReflectUtils.reflect(test11).field("F_INT2").get());

        // static field
        ZJReflectUtils.reflect(Test8.class).field("SF_INT1", 1);
        assertEquals(1, ZJReflectUtils.reflect(Test8.class).field("SF_INT1").get());

        ZJReflectUtils.reflect(Test8.class).field("SF_INT2", 1);
        assertEquals(1, ZJReflectUtils.reflect(Test8.class).field("SF_INT2").get());

        ZJReflectUtils.reflect(Test8.class).field("SF_INT2", null);
        assertNull(ZJReflectUtils.reflect(Test8.class).field("SF_INT2").get());
    }

    @Test
    public void fieldPrivateStaticFinal() {
        assertEquals(1, ZJReflectUtils.reflect(TestPrivateStaticFinal.class).field("I1").get());
        assertEquals(1, ZJReflectUtils.reflect(TestPrivateStaticFinal.class).field("I2").get());

        ZJReflectUtils.reflect(TestPrivateStaticFinal.class).field("I1", 2);
        ZJReflectUtils.reflect(TestPrivateStaticFinal.class).field("I2", 2);

        assertEquals(2, ZJReflectUtils.reflect(TestPrivateStaticFinal.class).field("I1").get());
        assertEquals(2, ZJReflectUtils.reflect(TestPrivateStaticFinal.class).field("I2").get());
    }

    @Test
    public void fieldAdvanced() {
        ZJReflectUtils.reflect(Test1.class)
                .field("S_DATA", ZJReflectUtils.reflect(Test1.class).newInstance())
                .field("S_DATA")
                .field("I_DATA", ZJReflectUtils.reflect(Test1.class).newInstance())
                .field("I_DATA")
                .field("I_INT1", 1)
                .field("S_INT1", 2);
        assertEquals(2, Test1.S_INT1);
        assertEquals(null, Test1.S_INT2);
        assertEquals(0, Test1.S_DATA.I_INT1);
        assertEquals(null, Test1.S_DATA.I_INT2);
        assertEquals(1, Test1.S_DATA.I_DATA.I_INT1);
        assertEquals(null, Test1.S_DATA.I_DATA.I_INT2);
    }

    @Test
    public void fieldFinalAdvanced() {
        ZJReflectUtils.reflect(Test8.class)
                .field("S_DATA", ZJReflectUtils.reflect(Test8.class).newInstance())
                .field("S_DATA")
                .field("I_DATA", ZJReflectUtils.reflect(Test8.class).newInstance())
                .field("I_DATA")
                .field("F_INT1", 1)
                .field("F_INT2", 1)
                .field("SF_INT1", 2)
                .field("SF_INT2", 2);
        assertEquals(2, Test8.SF_INT1);
        assertEquals(new Integer(2), Test8.SF_INT2);
        assertEquals(0, Test8.S_DATA.F_INT1);
        assertEquals(new Integer(0), Test8.S_DATA.F_INT2);
        assertEquals(1, Test8.S_DATA.I_DATA.F_INT1);
        assertEquals(new Integer(1), Test8.S_DATA.I_DATA.F_INT2);
    }

    @Test
    public void _hashCode() {
        Object object = new Object();
        assertEquals(ZJReflectUtils.reflect(object).hashCode(), object.hashCode());
    }

    @Test
    public void _toString() {
        Object object = new Object() {
            @Override
            public String toString() {
                return "test";
            }
        };
        assertEquals(ZJReflectUtils.reflect(object).toString(), object.toString());
    }

    @Test
    public void _equals() {
        Object object = new Object();
        ZJReflectUtils a = ZJReflectUtils.reflect(object);
        ZJReflectUtils b = ZJReflectUtils.reflect(object);
        ZJReflectUtils c = ZJReflectUtils.reflect(object);

        assertTrue(b.equals(a));
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
        //noinspection ObjectEqualsNull
        assertFalse(a.equals(null));
    }

    @Test
    public void testProxy() {
        assertEquals("abc", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(0));
        assertEquals("bc", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(1));
        assertEquals("c", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(2));

        assertEquals("a", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(0, 1));
        assertEquals("b", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(1, 2));
        assertEquals("c", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(2, 3));

        assertEquals("abc", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(0));
        assertEquals("bc", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(1));
        assertEquals("c", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(2));

        assertEquals("a", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(0, 1));
        assertEquals("b", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(1, 2));
        assertEquals("c", ZJReflectUtils.reflect((Object) "abc").proxy(Test9.class).substring(2, 3));
    }

    @Test
    public void testMapProxy() {
        class MyMap extends HashMap<String, Object> {
            private String baz;

            public void setBaz(String baz) {
                this.baz = "MyMap: " + baz;
            }

            public String getBaz() {
                return baz;
            }
        }

        Map<String, Object> map = new MyMap();

        ZJReflectUtils.reflect(map).proxy(Test10.class).setFoo("abc");
        assertEquals(1, map.size());
        assertEquals("abc", map.get("foo"));
        assertEquals("abc", ZJReflectUtils.reflect(map).proxy(Test10.class).getFoo());

        ZJReflectUtils.reflect(map).proxy(Test10.class).setBar(true);
        assertEquals(2, map.size());
        assertEquals(true, map.get("bar"));
        assertEquals(true, ZJReflectUtils.reflect(map).proxy(Test10.class).isBar());

        ZJReflectUtils.reflect(map).proxy(Test10.class).setBaz("baz");
        assertEquals(2, map.size());
        assertEquals(null, map.get("baz"));
        assertEquals("MyMap: baz", ZJReflectUtils.reflect(map).proxy(Test10.class).getBaz());

        try {
            ZJReflectUtils.reflect(map).proxy(Test10.class).testIgnore();
            fail();
        } catch (ZJReflectUtils.ReflectException ignored) {
        }
    }
}