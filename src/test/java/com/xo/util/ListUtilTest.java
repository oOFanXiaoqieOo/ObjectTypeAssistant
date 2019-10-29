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
//TODO: Test goes here... 
    }

} 
