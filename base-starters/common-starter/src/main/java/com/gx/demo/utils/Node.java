package com.gx.demo.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName : Node
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/17 9:42
 * @Version : 1.0
 */

public class Node implements Serializable {

    private static final long serialVersionUID = -2721191232926604726L;

    private int id;

    private int parentId;

   // private Node parent;

    private String name;

    private int level;

  //  private int sort;

    private int rootId;

    private String type;

    private boolean isLeaf;

  //  private String description;

    private List<Node> children;

    public Node (int id,int parentId ,String name){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

//    public Node getParent() {
//        return parent;
//    }
//
//    public void setParent(Node parent) {
//        this.parent = parent;
//    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

   /* public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }*/

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

   /* public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + parentId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id != other.id)
            return false;
        if (parentId != other.parentId)
            return false;
        return true;
    }

//    @Override
//    public String toString() {
//        return "Node {id=" + id + ", parentId=" + parentId + ", children="
//                + children + ", name=" + name + ", level =" + level + ", isLeaf =" + isLeaf + "}";
//    }
}
