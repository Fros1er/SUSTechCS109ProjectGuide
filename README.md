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

随项目还会提供一个库，里面有音乐播放器，可设背景图的JPanel和永远是方形的JPanel。

这篇文章的博客链接：https://blog.froster.icu/SUSTechCS109ProjectGuide/  
这篇文章的github链接：https://github.com/Fros1er/SUSTechCS109ProjectGuide/  

<!-- more -->

## 免责声明
我今年(2022/11/16)大三。有一直在关注这门课...

话先说在这：
<font size=6><b>不查重！！！！！！</b></font>

两年四个学期里我没听说过任何一位被查重的人。更没听说过因为用了什么库被查重的人。放心。

当然如果这学期有变动或者你复制粘贴，你真被查了，别找我。

实在不放心，用什么东西前问老师或者sa。

## 答辩流程
在15或16周会有答辩。时间不会太长，老师或者sa对着要求让你演示一遍功能，你展示一下bonus，结束。基本不会看你代码。

所以你如果什么东西没写好，演示的时候避开是完全可以的（不排除老师挑一个地方指挥你演示，但总体没这么多时间）。

## 项目管理和一些杂项
### 版本控制
如果你要进寄系，请一定在project开始前学习git。如果你不进寄系，推荐在project开始前学习git。

git的重要性...可以帮你们省去几个小时合代码的时间，同时在写出问题的时候知道自己动过哪里。还能给你们一分bonus。

最重要的是，它是一项基本技能，只要你们在做计算机相关的事情就一定绕不开它。

推荐阅读：[廖雪峰的git教程](https://www.liaoxuefeng.com/wiki/896043488029600/896067008724000)

### 依赖管理
如果不做bonus，可以跳过这一段。但是如果你想播放个音频，大概是绕不开别人写好的库的。

推荐使用maven。在idea里创建项目的时候可以直接选择maven，然后就可以去搜maven教程了(逃)。

也有一个比较简单的方法导入别人的库。把jar包下载到本地，然后：
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



# 设计

## 运行流程


## 全局访问
虽然实际的大工程不推荐这么做，但那是你工作（或者至少大三）以后的事情了。

更新UI不推荐这么做，稍后会讲到。

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

## 事件系统
咕。

# 三个神奇的class

## 导入
1. 去 [github release](https://github.com/Fros1er/SUSTechCS109ProjectGuide/releases) 页面下载最新的jar包。现在可能还没发。
2. IDEA左上角File-Project Structure
3. 点击窗口左边的Modules，然后点击窗口偏上方的Dependencies
4. 下面Export的上面有个+号，点击，然后选jars那个选项
5. 选择你下载的jar包，然后**点窗口最下面的确认**
6. 在需要的时候`import com.froster.utils.*`。

你也可以直接复制[这里](https://github.com/Fros1er/SUSTechCS109ProjectGuide/tree/main/src/main/java/com/froster/utils)的代码到自己的项目里。这样的话需要手动导入几个音乐相关的包，具体见 https://odoepner.wordpress.com/2013/07/19/play-mp3-or-ogg-using-javax-sound-sampled-mp3spi-vorbisspi/ 。

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
