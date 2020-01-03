package com.gx.sbd.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.gx.demo.utils.Node;
import com.gx.demo.utils.TreeBuilder;

import java.math.BigDecimal;
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
//        System.out.println(changeToBig(new BigDecimal("1.56")));
//        System.out.println(changeToBig(new BigDecimal("100.0")));
//        System.out.println(changeToBig(new BigDecimal("10000.0")));
//        System.out.println(changeToBig(new BigDecimal("10009.0")));
//
//        System.out.println(changeToBig(new BigDecimal("188888.1")));
//        System.out.println(changeToBig(new BigDecimal("1123101.88")));

//        System.out.println(changeToBig(new BigDecimal("100000010000.23")));

//        Double db = 55.89;
//        String str = "22.99";
//
//        System.out.println(db);
//        System.out.println(str);
//
//        System.out.println(db.toString());
//        System.out.println(str);
//
//        System.out.println(new BigDecimal(db));
//        System.out.println(new BigDecimal(str));

        test5();
    }

    /**
     * 树形测试
     */
    public static void demo1(){
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

    /**
     * 线程 锁测试
     */
    public static void demo2(){


        TaskThread taskThreadA = new TaskThread("线程 A 号： ");
        TaskThread taskThreadB = new TaskThread("线程 B 号： ");
        TaskThread taskThreadC = new TaskThread("线程 C 号： ");
        TaskThread taskThreadD = new TaskThread("线程 D 号： ");
        TaskThread taskThreadE = new TaskThread("线程 E 号： ");
        TaskThread taskThreadF = new TaskThread("线程 F 号： ");
        TaskThread taskThreadG = new TaskThread("线程 G 号： ");
        TaskThread taskThreadH = new TaskThread("线程 H 号： ");
        TaskThread taskThreadI = new TaskThread("线程 I 号： ");
        TaskThread taskThreadJ = new TaskThread("线程 J 号： ");
        TaskThread taskThreadK = new TaskThread("线程 K 号： ");
        TaskThread taskThreadL = new TaskThread("线程 L 号： ");
        taskThreadA.start();
        taskThreadB.start();
        taskThreadC.start();
        taskThreadD.start();
        taskThreadE.start();
        taskThreadF.start();
        taskThreadG.start();
        taskThreadH.start();
        taskThreadI.start();
        taskThreadJ.start();
        taskThreadK.start();
        taskThreadL.start();



    }
    /**
     * 线程 锁测试
     */
    public static void demo3(){

        for (int i = 0; i < 10 ; i++) {
            new TaskThread("线程 "+i+" 号： ").start();
        }

    }


    private static final char[] data = new char[] { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
    private static final char[] units = new char[] { '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿' };
    /**
     * 阿拉伯数字金额转中文
     * @param money
     * @return
     */
    public static String  demo4(int money){

        StringBuffer sbf = new StringBuffer();
        int unit = 0;
        while (money != 0) {
            sbf.insert(0, units[unit++]);
            int number = money % 10;
            sbf.insert(0, data[number]);
            money /= 10;
        }
        return sbf.toString();


    }

    /**
     * 阿拉伯数字金额转中文
     * @param value
     * @return
     */
    public static  String changeToBig(BigDecimal value){

        char[] hunit={'拾','佰','仟'};

        //段内位置表示
        char[] vunit={'万','亿'};

        //段名表示
        char[] digit={'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};

//        //数字表示
//        long midVal = (long)(value*100);
//
//        //转化成整形
//        String valStr = String.valueOf(midVal);

        System.out.println(value.toString());
         String valStr  = value.multiply(new BigDecimal(100)).toString();


        //转化成字符串
        String rail = "00";
        if(valStr.contains(".")){
            String[] sts = valStr.split("\\.");
            valStr = sts[0];
        }
        String head = valStr;

        if(valStr.length() >= 2){
            //取整数部分
            head = valStr.substring(0,valStr.length()-2);
            //取小数部分
            rail = valStr.substring(valStr.length()-2);
        }
        //整数部分转化的结果
        String prefix = "";
        //小数部分转化的结果
        String suffix = "";
        //处理小数点后面的数
        if(rail.equals("00")){
            //如果小数部分为0
            suffix="整";
        } else{
            //否则把角分转化出来
            suffix = digit[rail.charAt(0)-'0']+"角"+digit[rail.charAt(1)-'0']+"分";
        }
        //处理小数点前面的数
        //把整数部分转化成字符数组
        char[] chDig = head.toCharArray();
        //标志当前位的上一位是否为有效0位（如万位的0对千位无效）
        boolean preZero = false;
        //连续出现0的次数
        byte zeroSerNum = 0;
        for(int i=0; i < chDig.length; i++){
            //循环处理每个数字
            int idx=(chDig.length-i-1)%4;
            //取段内位置
            int vidx=(chDig.length-i-1)/4;
            //取段位置
            if(chDig[i] == '0'){
                //如果当前字符是0
                preZero = true;
                zeroSerNum++;
                //连续0次数递增
                if(idx==0 && vidx >0 && zeroSerNum < 4){
                    prefix += vunit[vidx-1];
                    //不管上一位是否为0，置为无效0位
                    preZero = false;
                }
            }else{
                zeroSerNum = 0;
                //连续0次数清零
                if(preZero){
                    //上一位为有效0位
                    prefix += digit[0];
                    //只有在这地方用到'零'
                    preZero = false;
                }
                prefix += digit[chDig[i]-'0'];
                //转化该数字表示
                if(idx > 0) prefix += hunit[idx-1];
                if(idx==0 && vidx > 0){
                    //段结束位置应该加上段名如万,亿
                    prefix += vunit[vidx-1];
                }
            }
        }

        //如果整数部分存在,则有圆的字样
        if(prefix.length() > 0) prefix += '元';

        //返回正确表示
        return prefix + suffix;

    }


    public static void test5(){

        MutableGraph<Integer> graphs = GraphBuilder.directed().allowsSelfLoops(true).build();

        graphs.addNode(1);
        graphs.addNode(2);
        graphs.addNode(3);
        graphs.addNode(4);
        graphs.addNode(5);

        graphs.putEdge(1,2);
        graphs.putEdge(2,3);
        graphs.putEdge(3,4);
        graphs.putEdge(4,5);
        graphs.putEdge(5,1);

        System.out.println(graphs);

    }

}

