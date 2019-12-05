package com.xo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TypeUtil
 * Java 基本类型转换
 * 命名方式 [SrcType]To[DetType]
 * 基本类型列表及TypeName
 * String  [Str]----------字符串
 * Char    [Char]---------字节
 * Char[]  [CharArr]------字符数组
 * Integer [Int]----------整形
 * Short   [Short]--------短整形
 * Float   [Float]--------浮点型
 * Double  [Double]-------双精度浮点型
 * Date    [Date]---------日期
 * List<>  [List]---------------------该方法不在本类中实现
 * Map<>   [Map]----------------------该方法不在本类中实现
 * Entity  [Bean]---------------------同上
 * <p>
 * 本方法类提供以上类型间的转换方法参考
 */
public class TU {
    public static String IntToStr(Integer i) {
        //Method 1
        return String.valueOf(i);
        //Method 2
        //return Integer.toString(i);
        //Method 3
        //return "" + i;

    }

    public static Integer StrToInt(String s) {
        //Method 1
        return Integer.valueOf(s);
        //Method 2
        //return Integer.valueOf(s).intValue();
    }


    /**将String转换为List<String>
     * 对String格式有要求，分隔符为','
     */
    public static List<String> StrToList(String strs) {
        if (strs == null) {
            return new ArrayList<>();
        } else {
            String str[]=strs.split(",");
            return Arrays.asList(str);
        }
    }
    /**将String转换为List<String>
     * 参数strs:输入字符串
     * 参数regex:正则表达式
     */
    public static List<String> StrToList(String strs,String regex) {
        if (strs == null) {
            return new ArrayList<>();
        } else {
            String str[]=strs.split(regex);
            return Arrays.asList(str);
        }
    }

    /**将String转换为List<Integer>
     * 对String格式有要求，分隔符为','
     */
    public static List<Integer> StrToListInt(String strs) {
        if (strs == null) {
            return new ArrayList<>();
        } else {
            String str[]=strs.split(",");
            if(str.length>0){
                List<Integer> ret = new ArrayList<>();
                for(int i=0;i<str.length;i++){
                    ret.add(TU.StrToInt(str[i]));//转化成Integer
                }
                return ret;
            }else{
                return new ArrayList<>();
            }
        }
    }

    /**将String转换为List<Integer>
     * 参数strs:输入字符串
     * 参数regex:正则表达式
     */
    public static List<Integer> StrToListInt(String strs,String regex) {
        if (strs == null) {
            return new ArrayList<>();
        } else {
            String str[]=strs.split(regex);
            if(str.length>0){
                List<Integer> ret = new ArrayList<>();
                for(int i=0;i<str.length;i++){
                    ret.add(TU.StrToInt(str[i]));//转化成Integer
                }
                return ret;
            }else{
                return new ArrayList<>();
            }
        }
    }

}
