package com.xo.util;

import java.util.ArrayList;
import java.util.List;

public interface ListTreeBaseEntity {

    String getTreeID();
//    void setTreeID(String id);
//    void setTreeID(Integer id);
    String getFatherTreeID();
//    void setFatherTreeID(String fid);
//    void setFatherTreeID(Integer fid);
    void setListTreeChildren(Object child);
    void setListTreeChildren(List<Object> children);
    List<Object> getListTreeChildren();

}
