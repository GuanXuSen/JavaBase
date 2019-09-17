package com.gx.demo.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : TreeBuild
 * @Description :TOO 递归遍历 子节点
 * @Author : gx
 * @Date : 2019/9/17 10:07
 * @Version : 1.0
 */
public class TreeBuilder {

    /**
     * 构建树
     * @param dirs
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Node> buildListToTree(List<Node> dirs) {
        List<Node> roots = findRoots(dirs);
        //集合相减
        List<Node> notRoots = (List<Node>) CollectionUtils.subtract(dirs, roots);
        //循环 主节点 设置子节点
        for (Node root : roots) {
            root.setChildren(findChildren(root, notRoots));
        }
        return roots;
    }


    /**
     * 获取 主节点
     * @param allNodes
     * @return
     */
    public List<Node> findRoots(List<Node> allNodes) {
        List<Node> results = new ArrayList<Node>();
        for (Node node : allNodes) {
            boolean isRoot = true;
            for (Node comparedOne : allNodes) {
                if (node.getParentId() == comparedOne.getId()) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                node.setLevel(0);
                results.add(node);
                node.setRootId(node.getId());
            }
        }
        return results;
    }

    /**
     * 设置子节点 （递归）
     * @param root
     * @param allNodes
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Node> findChildren(Node root, List<Node> allNodes) {
        List<Node> children = new ArrayList<Node>();
        // 遍历 节点  提取 节点
        for (Node comparedOne : allNodes) {
            if (comparedOne.getParentId() == root.getId()) {
                //设置主节点
              //  comparedOne.setParent(root);
                comparedOne.setLevel(root.getLevel() + 1);
                children.add(comparedOne);
            }
        }
        // 将 剩余 节点 提取出来
        List<Node> notChildren = (List<Node>) CollectionUtils.subtract(allNodes, children);

        //遍历当前 子节点  递归
        for (Node child : children) {
            List<Node> tmpChildren = findChildren(child, notChildren);
            if (tmpChildren == null || tmpChildren.size() < 1) {
                child.setLeaf(true);
            } else {
                child.setLeaf(false);
            }
            child.setChildren(tmpChildren);
        }

        return children;
    }

    /**
     * 获取 叶子节点
     * @param resultList
     * @param children
     * @return
     */
    public List<Node> getLeafChildren(List<Node> resultList, List<Node> children){
        for(Node node : children){
            if(node.isLeaf()){
                resultList.add(node);
            }else{
                getLeafChildren(resultList, node.getChildren());
            }
        }
        return resultList;
    }

}
