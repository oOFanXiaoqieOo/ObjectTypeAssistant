package com.xo.util;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.util.JAXBSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    /**
     * 函数getListTree
     * 在具有父子节点信息的数据中 根据输入的树节点ID 构建树结构数据
     * 参数listNode：树信息数据,List中的结构元素需要继承ListTreeBaseEntity 并实现相应接口
     * 参数 treeID：树节点ID
     * 参数 result：返回参数，推荐类型与List中的类型一致
     * */
    public static void getListTree(List<? extends ListTreeBaseEntity> listNode, String treeID, Object result) {
        int len=listNode.size();
        if (len > 0) {
            Map<String, List<Object>> listNodeGroup=new HashMap<>();
            for (int i=len - 1; i >= 0; i--) {
                if (treeID.equals(listNode.get(i).getTreeID())) {//筛选需要构造的树节点ID
                    if (result instanceof ListTreeBaseEntity) {
                        ((ListTreeBaseEntity) result).setListTreeChildren(listNode.get(i));//添加根节点
                        listNode.remove(listNode.get(i));
                    }
                } else {
                    //getListTree(listNode,listNode.get(i).getFatherTreeID(),result);
                    if (listNodeGroup.containsKey(listNode.get(i).getFatherTreeID())) {//按照fatherTreeID 进行分组
                        listNodeGroup.get(listNode.get(i).getFatherTreeID()).add(listNode.get(i));
                    } else {
                        listNodeGroup.put(listNode.get(i).getFatherTreeID(), new ArrayList<>());
                        listNodeGroup.get(listNode.get(i).getFatherTreeID()).add(listNode.get(i));
                    }
                    listNode.remove(listNode.get(i));
                }
            }

            if (((ListTreeBaseEntity) result).getListTreeChildren().size() > 0) {
                for (int i=0; i < ((ListTreeBaseEntity) result).getListTreeChildren().size(); i++) {
                    if (((ListTreeBaseEntity) result).getListTreeChildren().get(i) instanceof ListTreeBaseEntity) {
                        getTreeChildren(listNodeGroup, ((ListTreeBaseEntity)((ListTreeBaseEntity)result).getListTreeChildren().get(i)).getTreeID(), ((ListTreeBaseEntity) result).getListTreeChildren().get(i));
                    }
                }
            }
            System.out.println("listNodeGroup"+JSON.toJSONString(listNodeGroup));
        }
    }

    /**
     * 函数getRootListTree
     * 获得父节点为rootID 的树结构数据，处理方法与getListTree一致
     * 参数listNode：树信息数据,List中的结构元素需要继承ListTreeBaseEntity 并实现相应接口
     * 参数 rootID：父节点ID
     * 参数 result：返回参数，推荐类型与List中的类型一致
     * */
    public static void getRootListTree(List<? extends ListTreeBaseEntity> listNode, String rootID, Object result) {
        int len=listNode.size();
        if (len > 0) {
            Map<String, List<Object>> listNodeGroup=new HashMap<>();
            for (int i=len - 1; i >= 0; i--) {
                if (rootID.equals(listNode.get(i).getFatherTreeID())) {//筛选需要构造的树节点ID
                    if (result instanceof ListTreeBaseEntity) {
                        ((ListTreeBaseEntity) result).setListTreeChildren(listNode.get(i));//添加根节点
                        listNode.remove(listNode.get(i));
                    }
                } else {
                    //getListTree(listNode,listNode.get(i).getFatherTreeID(),result);
                    if (listNodeGroup.containsKey(listNode.get(i).getFatherTreeID())) {//按照fatherTreeID 进行分组
                        listNodeGroup.get(listNode.get(i).getFatherTreeID()).add(listNode.get(i));
                    } else {
                        listNodeGroup.put(listNode.get(i).getFatherTreeID(), new ArrayList<>());
                        listNodeGroup.get(listNode.get(i).getFatherTreeID()).add(listNode.get(i));
                    }
                    listNode.remove(listNode.get(i));
                }
            }

            if (((ListTreeBaseEntity) result).getListTreeChildren().size() > 0) {
                for (int i=0; i < ((ListTreeBaseEntity) result).getListTreeChildren().size(); i++) {
                    if (((ListTreeBaseEntity) result).getListTreeChildren().get(i) instanceof ListTreeBaseEntity) {
                        getTreeChildren(listNodeGroup, ((ListTreeBaseEntity)((ListTreeBaseEntity)result).getListTreeChildren().get(i)).getTreeID(), ((ListTreeBaseEntity) result).getListTreeChildren().get(i));
                    }
                }
            }
            System.out.println("listNodeGroup"+JSON.toJSONString(listNodeGroup));
        }
    }

    /**
     * 函数getTreeChildren
     * 在父节点信息listNodeGroup中 递归查找父节点为fatherID 的节点数据
     * 参数listNodeGroup：以父节点为Key值的 树信息数据
     * 参数fatherID：父节点ID
     * 参数result：返回值（需要继承ListTreeBaseEntity类 ）
     *
     *
     * 该递归函数主要提供给函数getListTree和getRootListTree使用
     * */
    public static void getTreeChildren(Map<String, List<Object>> listNodeGroup, String fatherID, Object result) {
        if (listNodeGroup.containsKey(fatherID)) {
            if (result instanceof ListTreeBaseEntity) {
                List<Object> newList = listNodeGroup.get(fatherID);
                ((ListTreeBaseEntity) result).setListTreeChildren(newList);//添加子节点
                listNodeGroup.remove(fatherID);//删除节点数据

//            int len = result.listTreeChildren.size();
                if (((ListTreeBaseEntity) result).getListTreeChildren().size() > 0) {
                    for (int i=0; i < ((ListTreeBaseEntity) result).getListTreeChildren().size(); i++) {
                        getTreeChildren(listNodeGroup, ((ListTreeBaseEntity)((ListTreeBaseEntity) result).getListTreeChildren().get(i)).getTreeID(), ((ListTreeBaseEntity) result).getListTreeChildren().get(i));
                    }
                }
            }
        }
    }
}
