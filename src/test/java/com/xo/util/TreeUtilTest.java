package com.xo.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * TreeUtil Tester.
 *
 * @author <Authors Fan>
 * @version 1.0
 * @since <pre>12, 5, 2019</pre>
 */
public class TreeUtilTest {

    @Test
    public void testgetListTree(){
        List<TestTreeNode> listNode = new ArrayList<>();
        listNode.add(new TestTreeNode(1,0,"A"));
        listNode.add(new TestTreeNode(2,1,"B"));
        listNode.add(new TestTreeNode(3,1,"C"));
        listNode.add(new TestTreeNode(4,2,"D"));
        listNode.add(new TestTreeNode(5,2,"E"));
        listNode.add(new TestTreeNode(6,2,"F"));
        listNode.add(new TestTreeNode(7,0,"G"));
        TestTreeNode ret = new TestTreeNode();
        TreeUtil.getRootListTree(listNode,"0",ret);
        System.out.println("ret:"+JSON.toJSONString(ret.listTreeChildren));
    }



    class TestTreeNode implements ListTreeBaseEntity{

        int treeID;
        int fatherTreeID;
        String name;
        List<Object> listTreeChildren = new ArrayList<>();
        TestTreeNode(){

        }
        TestTreeNode(int id,int fid,String name){
            treeID = id;
            fatherTreeID = fid;
            this.name =name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name=name;
        }

        @Override
        public String getTreeID() {
            return TU.IntToStr(treeID);
        }

//        @Override
//        public void setTreeID(String id) {
//            treeID = TU.StrToInt(id);
//        }

//        @Override
//        public void setTreeID(Integer id) {
//            treeID =id;
//        }

        @Override
        public String getFatherTreeID() {
            return TU.IntToStr(fatherTreeID);
        }

//        @Override
//        public void setFatherTreeID(String fid) {
//            fatherTreeID = TU.StrToInt(fid);
//        }

//        @Override
//        public void setFatherTreeID(Integer fid) {
//            fatherTreeID = fid;
//        }

        @Override
        public void setListTreeChildren(Object child) {
            this.listTreeChildren.add(child);
        }

        @Override
        public void setListTreeChildren(List<Object> children) {
            this.listTreeChildren.addAll(children);
        }

        @Override
        public List<Object> getListTreeChildren() {
            return this.listTreeChildren;
        }
    }
}
