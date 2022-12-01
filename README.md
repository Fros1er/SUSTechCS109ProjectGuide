---
title: 妮可java project指南
date: 2022-11-16 16:56:01
tags:
---

# SUSTechCS109ProjectGuide
南方科技大学CS109计算机程序设计基础期末project的一些指南

仍在持续更新中。欢迎讨论和PR。

作者QQ号是`815559068`。有问题私戳或者直接在互助群@Froster问。

## 写在前面

每学期都会看到基本所有的同学写project的时候都会在屎山里挣扎...

于是就有了这个东西。基本上是写代码需要知道，但是上课不会教你的内容。你也可以把它当作一个Q&A。

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
在15或16周会有答辩。时间不会太长，老师或者sa会对着要求让你演示一遍功能，然后你展示一下bonus，结束。基本不会看你代码。

所以你如果什么东西没写好，演示的时候避开是完全可以的（不排除老师挑一个地方指挥你演示，但总体没这么多时间）。

## 给自己打广告
如果你想基本不写UI几百行速通这个project.....

看看我的框架。用了就基本满分。

<https://github.com/Fros1er/2DBoardGameFrame/>

用框架是有风险的，并且也需要一定的学习和理解。做选择的时候考虑清楚。

当然，欢迎借鉴()

## 提问的智慧
<https://github.com/ryanhanwu/How-To-Ask-Questions-The-Smart-Way>

大概影响我一生的一篇文章。一定要读一读...

如果觉得太长不看，那就....  
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

还有一个叫**单例**的更好用的设计模式，好奇的话可以去看看。

### 清空全局
重开游戏的时候记得把全局的所有东西恢复默认。

## 简单，或者优雅
### 耦合
前后端不分离，让你的整个棋盘变成一个全局的继承JPanel的类。棋盘的每一个格子（象棋棋盘也可以是背景是十字线的格子）继承JPanel，存在棋盘类里面。格子上的棋子（继承JLabel）存在格子里面。因为你的格子可以全局访问，所以走棋的时候可以拿到任意格子和任意棋子。把当前格子的棋子删掉，放到目标上。没了。

大体是写一个`void moveTo(Point from, Point target) {...}`。开一个全局的临时变量存上一次点击有棋子位置时记下的`from`，如果下一次点击的格子是能走的，调用`moveTo`。如果不能，清空临时变量。结束。

简单粗暴比用SA给的框架更好写的一种设计。MVC？Observer？那是什么（  

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

如果你用了序列化，老师会直接给你错误存档检查的分，因为错误文件根本读不进来。（最好自己去向老师确认一下）

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

# 速通java swing
~~网上的教程普遍不太行...在这挖个坑。~~

## 组件
swing最主要的就三种组件：`JPanel`（容器），`JLabel`（字和图），`JButton`（按钮）。它们的共同父类是`Component`。

容器给里面装的东西一个空间并决定里面的东西如何排列(见布局管理器)，字、图、按钮、其他容器是“东西”。

所以设计UI的时候，想想哪些部分是强关联的。比如，你需要展示棋盘和游戏状态。显然这里有两个JPanel，一个是棋盘，另一个是某一个状态栏。状态栏里面有行棋方、得分等JLabel，棋盘里的每个格子是一个JPanel，格子里面是棋子的JLabel。

你的窗口是`JFrame`，里面自带一个`JPanel`。

具体有什么方法...还是得网上的教程。我写不全。

## 显示出问题了
多见于改了什么东西不起作用。这时只需要对有问题的组件写下两行神奇的代码：  
``` java
component.revalidate();
component.repaint();
```

它就有极大概率恢复正常。

## 继承？
大部分时候继承swing的组件不是个好选择，因为你不需要重写里面的方法。找个地方，new一个组件，调用方法，然后放在那就行（如果以后不需要改动甚至可以不找个地方放）。

## Listener
一个词：回调函数(callback)。

一句话：在Listener条件达成时，执行你写在Listener里面的代码。

有各种Listener。用的多的是：Button的`ActionListener`（按钮点击），JPanel的`MouseListener`。写`MouseListener`记得用`MouseAdapter`。不要用任何像是clicked的东西，否则会变得不幸。

给个示例：
``` java
panel.addMouseListener(new MouseAdapter() {
    private boolean pressed = false;
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        pressed = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        // your code here
    }
});
```

## JDialog
可以自定义的一个弹窗，直接往里放组件就行。

## 布局管理器
<https://blog.csdn.net/u011291072/article/details/127009177>  
常用GridLayout和BorderLayout。

建议不要把宽高写死...

## CardLayout
你会需要主界面，设置界面和游戏界面在一个窗口里的。用CardLayout。用法自己搜。

# 如何写bonus
## 基础部分。

### 打乱
`Collections.shuffle()`打乱一维数组，然后再对应到二维去。

### 

## 人机对战
如何把AI嵌到代码里是个难点。一般来说，如果你们没有把下棋的代码全扔进Listener，而是有**一个能全局访问的函数和全局访问的棋盘**，那么就只需要在切换玩家的时候检查下一个玩家是不是AI，如果是，让AI拿棋盘计算，然后直接调用那个下棋的函数就完事了。

另一个点是怎么让AI有思考过程（延时）。我直接贴代码吧。  
``` java
Thread t = new Thread(() -> {
    try {
        Thread.sleep(1000); //1000 milliseconds
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    // your code after delay here
});
t.start();
```
注意：在AI延时的时候所有Listener都是可以触发的（UI可以正常点）。记得屏蔽棋盘上的点击事件。

ab剪枝没那么好做，它是大三人工智能课上三个project之一。注意时间分配。btw，如果理解了原理，代码是可以直接抄的：<https://oi-wiki.org/search/alpha-beta/>

## 用户平台
我看project解读里写着多个frame...........it works，但真的不要这么干。

**CardLayout切换页面，JDialog弹窗。** 没了。上学期用JDialog是个bonus。

swing的表格有一点点难用。如果不会用的话可以循环添加JLabel，然后用GridLayout布局（指分隔和换行）。

## 主题切换
可以弄个`Theme`类，里面存些图片，字体啥的。然后找个地方存个`public static Themes[] themes;`，还有`public static Theme currentTheme；`。在所有设定背景图片，设定字体的时候都用`currentTheme.picForSomewhere`来拿到对应的东西。

切换主题时，更改currentTheme为themes里的某个主题，然后直接把你的JFrame或者最上层的JPanel删了重新创建一遍。简单粗暴有用。如果这个有困难，找个文件存currentTheme的编号，然后切换主题的时候提示下次重启游戏有效。

**反正你做了，虽然麻烦点，但老师不会不给你加分的。**

## 音效
调我的库，谢谢。

## 可行位置提示
我猜你们会有个点击选择棋子的环节。在那个时候计算所有可行位置，然后设置那些位置的JPanel就好了。最简单的是：
``` java
panel.setBackground(java.awt.Color.YELLOW); //设个背景色
panel.setOpaque(true); //让java把panel的背景色显示出来
```

## 悔棋 & 加载棋局步骤显示
搞一个ArrayList（其实起Stack的作用，直接用Stack更好），记录每一步从哪个位置拿的子，放到了哪个地方去。如果有子被吃了，记录被吃的是什么颜色的什么子。（或者记录翻开了哪个位置，总之记录每一步做了什么）。每走一步add这些信息到arrayList末尾。

悔棋的时候取arrayList末尾的信息出来，把子从现在的位置放回去，再把被吃的子放回棋盘，删掉arrayList末尾的信息，结束。

然后你会发现，如果开局的子位置不变，按照这个ArrayList一步步推下去就可以复原整个棋局。存档里存这个ArrayList和开局的棋盘，然后读档步骤显示和回放就都有了。

步骤显示需要延时。直接拿AI那里的代码，循环放子，放完一个sleep一下。同样，要记得屏蔽玩家点击和玩家切换（小心AI发疯）。

## 联网对战
会的人不需要我讲，不会的我讲了也没用。

实际上我没给游戏写过联网，所以没法讲。

## 可执行文件打包
除了exe4j，打成一个jar文件然后写个.bat或者.exe用命令行执行jar也是有分的。

## 其他

用swing做棋子运行动画就别想了。你的时间宝贵。（大佬请无视这句话，%%%%%%）

计时器，抄AI那里的代码，每秒改一下JPanel显示的数字就行。

## 其他（重要）

在上学期...读取存储游戏的GUI，JFileChooser选存档，用户属性（登录等），棋盘大小适应窗体缩放（用我的库谢谢喵），改棋盘图片，背景图片，鼠标划过格子或棋子有颜色变化（mouseEntered， mouseExited），计时器，这些都是有规定的bonus分的。人机不同算法拿到的bonus分也是不一样的。建议酌情去做一下。

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
