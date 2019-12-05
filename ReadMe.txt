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

4.boolean IsListEqual(List<Object> listA, List<Object> listB) //判断两个数组是否相等（值+顺序）;空数组比较返回false
4-1.boolean IsListEqual(List<Object> listA, List<Object> listB, List<Integer> index)	//判断两个数组index中的数据是否相等 自动忽略index中的异常值
5.boolean IsListValueEqual(List<Object> listA, List<Object> listB) //判断两个数组是否相等（值，不对顺序进行比较）;空数组比较返回false
6.boolean IsListContain(List<Object> listData, List<Object> listArea)  //判断数组ListArea是否包含listData,listData为空返回false


MapUtil：Map对象转换
1.<T> Map<String, Object> BeanToMap(T myEntity)//将Entity转为Map<String,Object>,Entity中的空数据自动忽略
2.<T> void MapToBean(Map<String, Object> map, T t)//实体方法中排除首字母的大小写影响，排除Integer和int的区别(返回数据为t)


升级.简介（1.1）
增加TreeUtil工具方法，实现将List数据转换为树结构数据
List元数据需要继承ListTreeBaseEntity接口类，并手动绑定ID，fatherID和ListChildren数据
1.getListTree 在具有父子节点信息的数据中 根据输入的树节点ID 构建树结构数据
2.getRootListTree 获得父节点为rootID 的树结构数据