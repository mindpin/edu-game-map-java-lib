edu game map java lib
============
edu game map java library

如何引用此组件：
-------------
**安装**
测试可能因为被墙，不能正常完成，所以安装的时候跳过测试。

```
git clone https://github.com/mindpin/edu-game-map-java-lib
cd edu-game-map-java-lib
mvn clean install -Dmaven.test.skip=true 
```

**maven引用**

在maven项目，pom.xml添加以下依赖引用：

```
<dependency>
    <groupId>com.mindpin.java.edu-game-map</groupId>
    <artifactId>edu-game-map</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <type>jar</type>
</dependency>
```

使用说明
---------------------
```java
Map map = Map.from_json(json) //从json获取map对象
Map m1 = Map.from_http("http://www.mocky.io/v2/547300140beca2ed0223c2e4"); //从url获取map对象
Map m2 = Map.from_file(new File("/m2.json")); //从json file获取map对象
```


依赖库
---------------------
* [destinyd/android-archetypes][android-archetypes]


[android-archetypes]: https://github.com/destinyd/android-archetypes
