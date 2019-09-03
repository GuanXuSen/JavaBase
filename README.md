# JavaBase
***
* base-starters  基础工具类等
* spring-boot-presto-demo   presto分布式查询测试用例
* scala-spark-demo  scala 和 spark 测试用例


***
## 笔记 （md格式）


>标记

### 代码块

`UUID.randomUUID().toString().replace("-","");`

``` 代码块多行
public static <T> List<List<T>> averageAssign(List<T> list, int n){
         List<List<T>> result=new ArrayList<List<T>>();
         int remaider=list.size()%n; 
         int number=list.size()/n;  
         int offset=0;
         for(int i=0;i<n;i++){
             List<T> value=null;
             if(remaider>0){
                 value=list.subList(i*number+offset, (i+1)*number+offset+1);
                 remaider--;
                 offset++;
             }else{
                 value=list.subList(i*number+offset, (i+1)*number+offset);
             }
             result.add(value);
         }
         return result;
     }  
```
### 行内式
* [presto](http://www.baidu.com)是什么？

[部署]:(http://www.baidu.com) "presto部署"
* presto怎么[部署]？