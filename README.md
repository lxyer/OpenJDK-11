# OpenJDK-11

## 项目介绍
`OpenJDK-11`源码，可供阅读学习使用。

* `src`目录存放OpenJDK-11源码。
* `test`目录存放源码阅读过程中写的一些测试文件，使用时可以连同相应的资源文件复制到新项目中。
* `resource`目录存放测试时用到一些资源文件。

## 源码来源
[OpenJDK-11官网](http://jdk.java.net/11/)

[OpenJDK存档-第三方网站](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)

## 源码版本                                                   
> **openjdk version "11" 2018-09-25**
>
> **OpenJDK Runtime Environment 18.9 (build 11+28)**
>
> **OpenJDK 64-Bit Server VM 18.9 (build 11+28, mixed mode)**

## 使用说明
1. 该源码主要包括`src.zip`里提供的源码，**不支持**直接编译。
   
   如想完整编译整个`OpenJDK`项目，请自行到`OpenJDK`项目官网下载链接库和编译工具。
   
2. 此源码与`Oracle JDK`源码并非完全一致。
   
   `Oracle JDK`仅开源了一部分代码。想深入学习JDK的话建议阅读此源码。

3. 源码原本采用了`JDK 9`之后的`module`模块系统组织，此处修改为熟悉的包组织形式，以便阅读。（根本原因是我以module的形式导入IDEA后有些module识别不来）

4. 因为本源码已经包含了`OpenJDK`中绝大多数公开的源码，所以**不建议**再为此项目重复关联JDK，因为这会造成在代码跳转中，跳转到关联JDK的源码中而不是直接跳转到本项目的源码中。
   
   此外，本项目还有一些源码是JDK里不提供的，所以关联了JDK可能会导致报错信息，提示找不到相关的代码。
   
   如果出于某些特殊需要必须关联JDK，建议在Project Structure->Project Settings->Modules->Dependencies选项卡下调整源码识别优先级，将\<Module source\>调整到列表首位，可以使得源码跳转中优先跳转到本项目自身的源码里。

5. 由于时间关系，本人未对所有代码一一测试。所以，如果因为某个源文件异常或缺失而导致IDE报警告信息或者报错误提示的话，解决方案有两个：
   * 如果代码对你很重要，影响到你对某个方法的理解，请自行到Google搜索相关源文件导入到项目中合适的位置
   * 如果仅仅是因为显示的异常信息造成了视觉上的干扰，请在IDE中自行设置语法高亮形式。比如在IDEA中阅读此项目时，我会关闭Settings->Editor->Color Scheme->General中关于错误、警告、非法符号的报错提示，并且关闭Settings->Editor->Inspections处的语法检测信息。（当然，这两个地方的设置均支持备份与自定义，所以建议不要直接在原设置上修改，而是新建一份设置再去修改比较好，以方便在不同项目之间切换规则）

6. **欢迎反馈好的想法、建议、意见。**

## 附录

#### 测试文件简介
