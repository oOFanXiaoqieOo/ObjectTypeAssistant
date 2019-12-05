package com.xo.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.ArrayList;
import java.util.List;

/**
 * ListUtil Tester.
 *
 * @author <Authors Fan>
 * @version 1.0
 * @since <pre>十月 25, 2019</pre>
 */
public class ListUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: ListToBean(List<Object> list, T t)
     */
    @Test
    public void testListToBean() throws Exception {
        List<Object> student = new ArrayList<>();
        student.add("fan");//数据类型顺序需要与entity中定义的顺序一致
        student.add(1);
        student.add(100);
        TestEntity entity = new TestEntity();
        ListUtil.ListToBean(student,entity);
        System.out.println("Entity:"+ JSON.toJSONString(entity));
    }

    @Test
    public void testIsListEqual() throws Exception {
        List<Object> la = new ArrayList<>();
        List<Object> lb = new ArrayList<>();
        la.add(1);
        la.add(2);
        la.add(null);
        lb.add(1);
        lb.add("2");
        lb.add(null);
        boolean ret = ListUtil.IsListEqual(la,lb);
        System.out.println("Ret:"+ ret);
    }

    @Test
    public void testIsListEqual2() throws Exception {
        List<Object> la = new ArrayList<>();
        List<Object> lb = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        la.add("1");
        la.add("18");
        la.add("1");
        lb.add("1");
        lb.add("18");
        lb.add("1");
        index.add(0);
        index.add(1);
        index.add(2);
        boolean ret = ListUtil.IsListEqual(la,lb,index);
        System.out.println("Ret:"+ ret);
    }

    @Test
    public void testIsListValueEqual() throws Exception {
        List<Object> la = new ArrayList<>();
        List<Object> lb = new ArrayList<>();
        la.add("2");
        la.add(1);
        la.add(1);
        lb.add(1);
        lb.add("2");
        lb.add(1);

        boolean ret = ListUtil.IsListValueEqual(la,lb);
        System.out.println("Ret:"+ ret);
    }

    @Test
    public void testIsListContain() throws Exception {
        List<Object> la = new ArrayList<>();
        List<Object> lb = new ArrayList<>();
        la.add("2");
        la.add(1);
        la.add(2);
        lb.add(1);
        lb.add("2");
        lb.add(3);
        lb.add(2);

        boolean ret = ListUtil.IsListContain(la,lb);
        System.out.println("Ret:"+ ret);
    }



} 
