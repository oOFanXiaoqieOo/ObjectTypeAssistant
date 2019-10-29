ObjectTypeAssistant
1.简介（1.0-SNAPSHOT）
提供三个静态类处理对象类型转换

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
 * List<>  [List]
 * Map<>   [Map]
 * Entity  [Bean]
 * <p>
 * 本方法类提供以上类型间的转换方法参考
*/


LU：基本类型转换
1.String IntToStr(Integer i)
2.Integer StrToInt(String s)
3.List<String> StrToList(String strs)//对String格式有要求，分隔符为','
4.List<String> StrToList(String strs,String regex)



ListUtil:List对象转换
1.<T> void ListToBean(List<Object> list, T t)//实体方法中排除首字母的大小写影响，排除Integer和int的区别(返回数据为t)
2.List<Object> ListMask(List<Object> org, List<Integer> mask)//将原List中的数据 根据mask 的下标数据重新取出重组 下标从0开始
3.Map<String, Object> ListToMap(List<Object> org, Map<String, Integer> mask)//List转Map，并对数据进行组装




MapUtil：Map对象转换
1.<T> Map<String, Object> BeanToMap(T myEntity)//将Entity转为Map<String,Object>,Entity中的空数据自动忽略
2.<T> void MapToBean(Map<String, Object> map, T t)//实体方法中排除首字母的大小写影响，排除Integer和int的区别(返回数据为t)





