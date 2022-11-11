# SUStechCS109ProjectGuide
南方科技大学CS109计算机程序设计基础期末project的一些指南

## 写在前面

每学期都会看到基本所有的同学写project的时候在屎山里挣扎...

于是就有了这个东西。基本上是写代码需要知道但是上课不会教你的内容。你也可以把它当作一个Q&A。

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

# 设计

## 运行流程


## 全局访问
虽然实际的大工程不推荐这么做，但那是你工作（或者至少大三）以后的事情了。

更新UI不推荐这么做，稍后会讲到。

总的来说，让你的棋盘等程序很多地方都需要的东西能直接被拿到。缺点是这会导致debug变困难一点，但逻辑清晰的话这不是问题。
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