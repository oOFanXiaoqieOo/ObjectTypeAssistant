package com.xo.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MapUtil Tester.
 *
 * @author <Authors Fan>
 * @version 1.0
 * @since <pre>十月 25, 2019</pre>
 */
public class MapUtilTest {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: MapToBean(Map<String,Object> map, T t)
     */
    @Test
    public void testMapToBean() throws Exception {
//        String filePath = "E://HONG/1.xlsx";
//        String path = "E://HONG/1.xml";         //从配置文件中加载挡板组装配置数据
//        EasyExcelMask load=new EasyExcelMask();
//        List<EasyExcelMask> dMask =load.LoadMask(path,"Map");//加载根节点->LoadTest节点下的Element数据，第一个属性或MaskName为mask,第二个属性或FakeName为fakemask,Text为matchmask
//        EasyExcelUtil.setdMask(dMask);
//        List<Object> objects=EasyExcelUtil.readExcelData(filePath, 2);


        Map<String,Object> map = new HashMap<>();
        map.put("name",(Object)("hehehe"));
        map.put("age",(Object)1);
        map.put("sex",(Object)999);
        TestEntity entity = new TestEntity();
        //System.out.println("map:"+JSON.toJSONString((Map)(objects.get(0))));
        //MapUtil.MapToBean((Map)(objects.get(0)),entity);
        //System.out.println("Entity:"+JSON.toJSONString(MapUtil.BeanToMap(entity)));
        System.out.println("Entity:"+JSON.toJSONString(MapUtil.BeanToMap(map)));
    }

    @Test
    public void testBeanToMap() throws Exception {
        TestEntity entity = new TestEntity();
        entity.setName("Fan");
        entity.setAge(666);
        entity.setSex(999);
        System.out.println("Map:"+JSON.toJSONString(MapUtil.BeanToMap(entity)));
    }





}
