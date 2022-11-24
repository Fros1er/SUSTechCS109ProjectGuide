---
title: 妮可java project指南
date: 2022-11-16 16:56:01
tags:
---

# SUStechCS109ProjectGuide
南方科技大学CS109计算机程序设计基础期末project的一些指南

## 写在前面

每学期都会看到基本所有的同学写project的时候在屎山里挣扎...

于是就有了这个东西。基本上是写代码需要知道但是上课不会教你的内容。你也可以把它当作一个Q&A。

作者QQ号是`815559068`。有问题私戳或者直接在互助群@Froster问。

随项目还会提供一个库，里面有音乐播放器，EventCenter，可设背景图的JPanel和永远是方形的JPanel。

这篇文章的博客链接：<https://blog.froster.icu/SUSTechCS109ProjectGuide/>  
这篇文章的github链接：<https://github.com/Fros1er/SUSTechCS109ProjectGuide/>  

<!-- more -->

## 免责声明

话先说在这：
<font size=7><b>不查重！！！！！！</b></font>

两年四个学期里我没听说过任何一位被查重的人。更没听说过因为用了什么库被查重的人。放心。

当然如果这学期有变动或者你复制粘贴，你真被查了，别找我。

实在不放心，用什么东西前问老师或者sa。

## 答辩流程
在15或16周会有答辩。时间不会太长，老师或者sa对着要求让你演示一遍功能，你展示一下bonus，结束。基本不会看你代码。

所以你如果什么东西没写好，演示的时候避开是完全可以的（不排除老师挑一个地方指挥你演示，但总体没这么多时间）。

## 提问的智慧
<https://github.com/ryanhanwu/How-To-Ask-Questions-The-Smart-Way>

大概影响我一生的一篇文章。一定要读一读...

如果觉得太常不看，那就....  
<font size=5><b>记得看报错，用搜索引擎，翻聊天记录。</b></font>

## 项目管理和一些杂项
### 版本控制
如果你要进寄系，请一定在project开始前学习git。如果你不进寄系，推荐在project开始前学习git。作用详见下面的链接。

git的重要性...可以帮你们省去几个小时合代码的时间，同时在写出问题的时候知道自己动过哪里。还能给你们一分bonus。

最重要的是，它是一项基本技能，只要你们在做计算机相关的事情就一定绕不开它。

推荐阅读：[廖雪峰的git教程](https://www.liaoxuefeng.com/wiki/896043488029600/896067008724000)

### 依赖管理
如果不做bonus，可以跳过这一段。但是如果你想播放个音频，大概是绕不开别人写好的库的。

导入方法：把jar包下载到本地，然后：
1. IDEA左上角File-Project Structure  
2. 点击窗口左边的Modules，然后点击窗口偏上方的Dependencies  
3. 下面Export的上面有个+号，点击，然后选jars那个选项  
4. 选择你下载的jar包，然后**点窗口最下面的确认**  

然后你import的内容应该就不红了。

### 奇怪的问题
有时候会遇见一些代码全都变红的情况。。。尤其是把你队友的代码复制过来的时候。
提供三种解决方法。不一定有用。
1. IDEA左上角File-Open，然后确认你和你队友打开的文件夹是一样的。  
2. File-Project Structure-Project-SDK。确认一下版本是不是一样的。  
3. File-Invalidate Caches。可以解决一些玄学问题，大概率没用。  

### 如何使用搜索引擎
前面加上java swing，后面跟类名或者简略问题。比如：  
java swing JButton  
java swing listener  
java swing make jPanel square

推荐在百度以外的地方搜英文！！！！

能用谷歌用谷歌，用不了就必应国际版，不要用百度！

然后你会看到一个叫stackoverflow的网站。点就对了。

# 速通java swing
网上的教程普遍不太行...在这挖个坑。

## 组件
坑慢慢填

### JDialog
可以自定义的一个弹窗，直接往里放组件就行。

## 布局管理器
<https://blog.csdn.net/u011291072/article/details/127009177>  
常用GridLayout和BorderLayout。

### CardLayout
你会需要主界面，设置界面和游戏界面的。用CardLayout。

# 设计

## 放弃demo
前排提醒：理解demo不一定比你从头写一个简单。demo的有些设计可能会拖累你的项目进度。

## 全局访问
虽然实际的大工程不推荐这么做，但那是你工作（或者至少大三）以后的事情了。

总的来说，让你的**棋盘，GUI，玩家列表，AI**等程序很多地方都需要的东西能直接被访问并修改。这会导致debug变困难一点，但逻辑清晰的话这不是问题。

新建一个类，把全局变量设为public static就好。
```java
public class SomeClassname {
    public static Grid[][] yourBoard; // init elsewhere
}

// somewhere in your code...
public class ElseWhere {
    public void someMethod() {
        if (SomeClassname.yourBoard[0][0].sth) {
            //do something
        }
    }
}
```

还有一个叫单例的更好用的设计模式，好奇的话可以去看看。

### 清空全局
重开游戏的时候记得把全局的所有东西恢复默认。

## 简单，或者优雅
### 耦合
前后端不分离，让你的整个棋盘变成一个全局的继承JPanel的类。棋盘的每一个格子（象棋棋盘也可以是背景是十字线的格子）继承JPanel，存在棋盘类里面。格子上的棋子（继承JLabel）存在格子里面。因为你的格子可以全局访问，所以走棋的时候可以拿到任意格子和任意棋子。把当前格子的棋子删掉，放到目标上。没了。

大体是写一个`void moveTo(Point from, Point target) {...}`。开一个全局的临时变量存上一次点击有棋子位置时记下的`from`，如果下一次点击的格子是能走的，调用`moveTo`。如果不能，清空临时变量。结束。

简单粗暴好写的一种设计。MVC？Observer？那是什么（  
某种意义上比用SA给的框架更好写。

缺点也很多。比如，你走完一步棋要判断被吃的是不是将/帅，要切换玩家，要通知你的AI该走下一步棋了，还有撤回/重做，联网对战，其他UI组件的更新……这些全部被扔进了你的走棋方法里，让它变得又臭又长。这个设计在这个体量的project下是完全可以用的，只是你和你的队友可能会配合的比较痛苦。
### 解耦
前后端分离。你的棋盘，格子，棋子类不再继承swing的组件。前端只管在某一个时候读取你只存储数据的棋盘数组，把整个可见的棋盘重新画一遍（暴力一点，反正一共也没几个格子）。

什么时候重新画呢？看看底下的事件系统。总之在moveTo结束后发一个`BoardChangeEvent`，让前端找个地方注册一下就好了。

好处是，前端只管调用moveTo，然后自己想怎么画怎么画。后端只管发事件出去，前端如果要加新功能，比如找个地方显示被吃的棋子，和你没关系，你只要发就好了。玩家变动发playerChangeEvent，游戏结束发GameEndEvent，前端处理起来也方便，监听不同事件绘制不同UI即可。

（总之，至少合作起来非常方便。

#### 序列化
一个额外的好处：如果你的类是独立的，只要里面所有的成员变量都可以序列化（实现Serializable接口），那就可以直接把实例连带当前成员变量的值写出到一个文件里。存档读档的时候特别方便。

java的容器（数组，ArrayList之类的）默认都可以序列化。

<https://www.runoob.com/java/java-serialization.html>

### 事件系统
前排提醒：这里说的不是Observer，是个人觉得比Observer好写一万倍的Publisher/Subscriber。强烈推荐不用demo给的Observer。

``` java
EventCenter.subscribe("event name", (e) -> {
    ExampleEvent casted = (ExampleEvent) e;
    System.out.println(casted.a);
});

// somewhere else:
EventCenter.publish("event name", new ExampleEvent(10));
```

和swing的监听器差不多。在EventCenter注册（订阅）一个函数，然后在有对应事件发生时发布这个事件。此时，所有此前注册的函数都会被调用。还是比较简单的（有问题问我）。

EventCenter的实现大概只有不到15行。可以自己去看看。

# 五个神奇的class

## 导入
1. 去 [github release](https://github.com/Fros1er/SUSTechCS109ProjectGuide/releases) 页面下载最新的jar包。现在可能还没发。
2. IDEA左上角File-Project Structure
3. 点击窗口左边的Modules，然后点击窗口偏上方的Dependencies
4. 下面Export的上面有个+号，点击，然后选jars那个选项
5. 选择你下载的jar包，然后**点窗口最下面的确认**
6. 在需要的时候`import com.froster.utils.*`。

你也可以直接复制[这里](https://github.com/Fros1er/SUSTechCS109ProjectGuide/tree/main/src/main/java/com/froster/utils)的代码到自己的项目里。这样的话需要手动导入几个音乐相关的包，具体见<https://odoepner.wordpress.com/2013/07/19/play-mp3-or-ogg-using-javax-sound-sampled-mp3spi-vorbisspi/> 。

## AudioPlayer
只支持wav格式的音频播放器。有三个静态方法：  
`public static Future<?> playBgm(String path)`  
持续播放path（文件路径）指定的音频。播放完后会重播。

`public static Future<?> playSound(String path)`  
播放path指定的音频。播放完后不会重播。

`public static void stop(Future<?> handle)`  
停止播放。上面两个方法会返回一个类型为`Future<?>`的变量。把它传给stop即可停止对应的音频播放。

## GridPanel
一个布局管理器为`GridLayout`的`JPanel`，特点是里面的格子在改变大小时永远是方形。构造时需要传入宽和高各有几个格子。

## BackgroundImagePanel
一个可以设背景图的`JPanel`。构造时传入`Image`对象。

`Image`对象可以通过`Image image = ImageIO.read(new File("path/to/image"));`拿到。

上面两个Panel都可以直接替换JPanel，除了构造函数不一样没有任何副作用。

## EventCenter和Event
见上面的事件系统。

Event要单独说一下。lambda表达式（那个长得是`(e) -> {}`的匿名函数）里的参数e是`Event`。需要手动做一个强制类型转换，把它转成你实际传进去的继承`Event`的类。
