package com.xo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Map常用操作
 * @author: by FanXiaoQie
 * @date:
 */

public class MapUtil {
    //将Entity转为Map<String,Object>,Entity中的空数据自动忽略
    public static <T> Map<String, Object> BeanToMap(T myEntity) throws IllegalAccessException {
        Map<String, Object> ret=new HashMap<>();
        Field[] fields=myEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod=field.getModifiers();
            //System.out.println("fieldName["+i+"]:"+field.getName());
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                //System.out.println("continue");
                continue;
            }
            field.setAccessible(true);
            if (field.get(myEntity) != null) {
                ret.put(field.getName(), field.get(myEntity));
            }
        }
        return ret;
    }

    //

    /**
     * 把Map内容逐个取出来放进对应的实体类中
     * 实体方法中排除首字母的大小写影响，排除Integer和int的区别
     * 支持String自适应 int和Integer类型（未实现）
     * 参数map：输入Map数据
     * 参数t：返回值
     * 返回值：无
     */
    public static <T> void MapToBean(Map<String, Object> map, T t) throws Exception {
        Field[] fields=t.getClass().getDeclaredFields();
        if (map == null || map.size() == 0) {
            System.out.println("size empty");
            return;
        } else if (map.size() != fields.length) {//有可能存在有些属性没有赋值的情况
            System.out.println("size error:map[" + map.size() + "]   bean[" + fields.length + "]");
        }
        Method method=null;
        Class<?> clazz=t.getClass();
        for (String key : map.keySet()) {
            String setMethodName="set" + key.substring(0, 1).toUpperCase() + key.substring(1);//调用首字母大写方法
            try {
                method=clazz.getMethod(setMethodName, new Class[]{map.get(key).getClass()});
            } catch (SecurityException e1) {
                e1.printStackTrace();
                return;
            } catch (NoSuchMethodException e1) {
                System.out.println("NoSuchMethod：" + setMethodName + "(" + map.get(key).getClass().getSimpleName() + ")");
                String newMethodName="set" + key.substring(0, 1).toLowerCase() + key.substring(1);//调用首字母小写的方法
                try {
                    method=clazz.getMethod(newMethodName, new Class[]{map.get(key).getClass()});
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                    return;
                } catch (NoSuchMethodException e2) {
                    System.out.println("NoSuchMethod：" + newMethodName + "(" + map.get(key).getClass().getSimpleName() + ")");
                    if (map.get(key).getClass() == Integer.class) {
                        try {
                            method=clazz.getMethod(setMethodName, new Class[]{int.class});
                        } catch (SecurityException e3) {
                            e3.printStackTrace();
                            return;
                        } catch (NoSuchMethodException e3) {
                            System.out.println("NoSuchMethod：" + setMethodName + "(int )");
                            try {//Integer 与int 转换
                                method=clazz.getMethod(newMethodName, new Class[]{int.class});
                            } catch (SecurityException e4) {
                                e4.printStackTrace();
                                return;
                            } catch (NoSuchMethodException e4) {
                                System.out.println("noSuchMethod：" + newMethodName + "(int )");
                                e4.printStackTrace();
                                return;
                            }
                        }
                    } else {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
            method.invoke(t, map.get(key));
        }
    }
}
