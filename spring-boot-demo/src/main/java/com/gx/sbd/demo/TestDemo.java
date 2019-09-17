package com.gx.sbd.demo;

import com.alibaba.fastjson.JSON;
import com.gx.demo.utils.Node;
import com.gx.demo.utils.TreeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : TestDemo
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/17 10:16
 * @Version : 1.0
 */
public class TestDemo {

    public static void main(String[] args) {
        TreeBuilder tb = new TreeBuilder();
        List<Node> allNodes = new ArrayList<Node>();
        allNodes.add(new Node(1, 0, "节点1"));
        allNodes.add(new Node(2, 0, "节点2"));
        allNodes.add(new Node(3, 0, "节点3"));

        allNodes.add(new Node(4, 1, "节点4"));
        allNodes.add(new Node(5, 1, "节点5"));
        allNodes.add(new Node(6, 1, "节点6"));

        allNodes.add(new Node(7, 4, "节点7"));
        allNodes.add(new Node(8, 4, "节点8"));

        allNodes.add(new Node(9, 5, "节点9"));
        allNodes.add(new Node(10, 5, "节点10"));

        allNodes.add(new Node(11, 7, "节点11"));
        allNodes.add(new Node(12, 7, "节点12"));
        // 显示所有节点
        List<Node> roots = tb.buildListToTree(allNodes);
        for (Node n : roots) {
            System.out.println(JSON.toJSONString(n));
        }
        System.out.println("------------------");
        // 查找所有子节点
        List<Node> children = tb.findChildren(new Node(1, 0, "节点1"), allNodes);
        for (Node n : children) {
            System.out.println(JSON.toJSONString(n));
        }
        // 查找所有叶子节点
        System.out.println("------------------");
        List<Node> resultList = tb.getLeafChildren(new ArrayList<Node>(), children);
        for (Node n : resultList) {
            System.out.println(JSON.toJSONString(n));
        }

    }
}

