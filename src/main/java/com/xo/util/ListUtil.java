package com.xo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @description: List常用操作
 * @author: by FanXiaoQie
 * @date:
 */
public class ListUtil {
    /**
     * 把list内容逐个取出来放进实体类中
     * 实体方法中排除首字母的大小写影响，排除Integer和int的区别
     * 参数list：输入List数据
     * 参数t：返回值
     * 返回值：无
     */
    public static <T> void ListToBean(List<Object> list, T t) throws Exception {
        Field[] fields=t.getClass().getDeclaredFields();
        if (list.size() != fields.length) {
            System.out.println("size error" + list.size() + ";" + fields.length);
            //return;
        }
        for (int k=0, len=fields.length; k < len; k++) {
            // 根据属性名称,找寻合适的set方法
            String fieldName=fields[k].getName();
            String setMethodName="set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method=null;
            Class<?> clazz=t.getClass();
            try {
                //先调用首字母大写的方法
                method=clazz.getMethod(setMethodName, new Class[]{list.get(k).getClass()});
            } catch (SecurityException e1) {
                e1.printStackTrace();
                return;
            } catch (NoSuchMethodException e1) {
                System.out.println("NoSuchMethod：" + setMethodName + "(" + list.get(k).getClass().getSimpleName() + ")");
                String newMethodName="set" + fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);//调用首字母小写的方法
                try {
                    method=clazz.getMethod(newMethodName, new Class[]{list.get(k).getClass()});
                } catch (SecurityException e3) {
                    e3.printStackTrace();
                    return;
                } catch (NoSuchMethodException e3) {
                    System.out.println("noSuchMethod：" + newMethodName + "(" + list.get(k).getClass().getSimpleName() + ")");
                    if (list.get(k).getClass() == Integer.class) {
                        try {
                            method=clazz.getMethod(setMethodName, new Class[]{int.class});
                        } catch (SecurityException e2) {
                            e2.printStackTrace();
                            return;
                        } catch (NoSuchMethodException e2) {
                            System.out.println("NoSuchMethod：" + setMethodName + "(int)");
                            try {//Integer 与int 转换
                                method=clazz.getMethod(newMethodName, new Class[]{int.class});
                            } catch (SecurityException e4) {
                                e4.printStackTrace();
                                return;
                            } catch (NoSuchMethodException e4) {
                                System.out.println("noSuchMethod：" + newMethodName + "(int)");
                                e4.printStackTrace();
                                return;
                            }
                        }
                    } else {
                        e3.printStackTrace();
                        return;
                    }
                }
            }
            if (method == null) {
                System.out.println("method error:null");
                return;
            }
            method.invoke(t, new Object[]{list.get(k)});
        }
    }


    //将原List中的数据 根据mask 的下标数据重新取出重组 下标从0开始
    public static List<Object> ListMask(List<Object> org, List<Integer> mask) {
        int len=org.size();
        int ret_len=mask.size();
        List<Object> ret=new ArrayList<>();
        for (int i=0; i < ret_len; i++) {
            int index=mask.get(i);
            if (index < len) {
                ret.add(org.get(index));
            } else {//若存在越界的下标，则填空字符串
                ret.add("");
            }
        }
        return ret;
    }

    /**
     * 将原List中的数据 根据mask 的下标数据重新取出重组，再根据maskStr对应序号组装成Map数据
     * mask数据不足填“”，maskStr数据不足填“”，mask与maskStr最好对应
     * 该方法弃用
     */
    public static Map<String, Object> ListToMap(List<Object> org, List<Integer> mask, List<String> maskStr) {
        int len=org.size();
        int mask_len=mask.size();
        int maskstr_len=maskStr.size();
        Map<String, Object> ret=new HashMap<>();
        for (int i=0; i < mask_len; i++) {
            int index=mask.get(i);
            if (index < len && i < maskstr_len) {
                ret.put(maskStr.get(i), org.get(index));
            } else {//若存在越界的下标，则填空字符串
                if (i < maskstr_len) {
                    ret.put(maskStr.get(i), "");
                } else {//maskStr越界
                    ret.put("", "");
                }
            }
        }
        return ret;
    }

    //List转Map，并对数据进行组装
    public static Map<String, Object> ListToMap(List<Object> org, Map<String, Integer> mask) {
        int len=org.size();
        Map<String, Object> ret=new HashMap<>();
        for (String key : mask.keySet()) { //根据key值遍历mask
            int index=mask.get(key);
            if (index < len) {
                ret.put(key, org.get(index));
            } else {//若存在越界的下标，则填空字符串
                ret.put(key, "");
            }
        }
        return ret;
    }

    //判断两个数组是否相等（值+顺序）;空数组比较返回false
    public static boolean IsListEqual(List<Object> listA, List<Object> listB) {
        int lenA=listA == null ? 0 : listA.size();
        int lenB=listB == null ? 0 : listB.size();
        if (lenA == lenB) {
            if (lenA == 0) {
                return false;   //空数组比较返回false
            }
            for (int i=0; i < lenA; i++) {
                if (listA.get(i).toString() != listB.get(i).toString()) {//存在不相等的值 则返回false
                    return false;
                }
            }
        } else {//长度不等 则返回false
            return false;
        }
        return true;
    }

    //判断两个数组index中的数据是否相等
    public static boolean IsListEqual(List<Object> listA, List<Object> listB, List<Integer> index) {
        int lenA=listA == null ? 0 : listA.size();
        int lenB=listB == null ? 0 : listB.size();
        int num=index == null ? 0 : index.size();
        int validNum=0;
        if (num > 0) {
            for (int i=0; i < num; i++) {
                int t=index.get(i);
                if (t < lenA && t < lenB && t >= 0) {
                    Object la=(listA.get(t) == null) ? "" : listA.get(t).toString();
                    Object lb=(listB.get(t) == null) ? "" : listB.get(t).toString();
//                    if (listA.get(t) != listB.get(t)) {//空字符也会影响判断结果
                    if (!la.equals(lb)) {//使用 != 有些字符判断不了
                        //System.out.println(la + ":" + lb + "=" + (la.equals(lb)));
                        return false;
                    }
                    validNum++;
                }
            }
        } else {
            return false;
        }
        if (validNum > 0) {
            return true;
        } else {//index中没有有效数据
            return false;
        }
    }


    //判断两个数组是否相等（值，不对顺序进行比较）;空数组比较返回false
    public static boolean IsListValueEqual(List<Object> listA, List<Object> listB) {
        int lenA=listA == null ? 0 : listA.size();
        int lenB=listB == null ? 0 : listB.size();
        if (lenA == lenB) {
            if (lenA == 0) {
                return false;   //空数组比较返回false
            }
            //方法1：需要Object对象重写equals方法
            //if (!listA.containsAll(listB)) {
            //   return false;
            //}
            //方法2：
            List<Object> t_listA=listA;
            List<Object> t_listB=listB;
            for (Object o : t_listA) {
                if (!t_listB.remove(o)) {//移除失败则返回false
                    return false;
                }
            }
        } else {//长度不等 则返回false
            return false;
        }
        return true;
    }

    //判断数组ListArea是否包含listData,listData为空返回false
    public static boolean IsListContain(List<Object> listData, List<Object> listArea) {
        int lenA=listData == null ? 0 : listData.size();
        int lenB=listArea == null ? 0 : listArea.size();
        if (lenB >= lenA) {
            if (lenA == 0) {
                return false;   //空数组返回false
            }
            List<Object> t_listData=listData;
            List<Object> t_listArea=listArea;
            for (Object o : listData) {
//                if(!listArea.contains(o)){//listData中的重复值会被忽略
                if (!t_listArea.remove(o)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


}
