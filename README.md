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
请参考示例


依赖库
---------------------
* [destinyd/android-archetypes][android-archetypes]


[android-archetypes]: https://github.com/destinyd/android-archetypes
