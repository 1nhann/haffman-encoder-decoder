# 哈弗曼编码译码系统

[TOC]

## **1.**   **设计题目与设计任务**

> 任务：设计一个利用哈夫曼算法的编码和译码系统，重复地显示并处理以下项目，直到选择退出为止。
>
> 要求：将权值数据存放在数据文件(文件名为data.txt，位于执行程序的当前目录中) 分别采用动态和静态存储结构。
>
> 初始化：键盘输入字符集大小n、n个字符和n个权值，建立哈夫曼树；
>
> 编码：利用建好的哈夫曼树生成哈夫曼编码；输出编码；
>
> 译码功能；显示哈夫曼树；
>
> 设字符集及频度如下表：
>
> 字符 空格 A B C D E F G H I J K L M
>
> 频度 186 64 13 22 32 103 21 15 47 57 1 5 32 20
>
> 字符 N O P Q R S T U V W X Y Z 
>
> 频度 57 63 15 1 48 51 80 23 8 18 1 16 1 
>
> 界面设计的优化。
>
> 0 23 8 18 1 16 1 
>
> 界面设计的优化。

## **2.**   **前言**

众所周知，一个字符在计算机中对应一个01序列。一个字符集中，每个字符有不同的权重（通常以在文本中出现的频率为权重），而为了使得
 ∑liwi（01序列的长度和字符权重的乘积之和）
 最小，于是使用哈夫曼编码算法，根据字符的权重，计算出每个字符对应的01序列。理论上，使用经哈夫曼编码后的01序列传递信息，可以使得总数据传输量最小。

本次数据结构课设，我用Java写了个Web应用，支持用户在线自定义字符权重，快速进行哈夫曼编码、译码，使得用户可以快速完成字符串和01序列之间的转换。

Java是面向对象的语言，其数据结构用class 实现，且Java中没有指针，因此就实现而言，Java的数据结构和C语言差别较大。

## **3.**   **设计主体（各部分设计内容、分析、结论等）**

### 3.1需求分析

就需求方面而言，本次程序设计主要需满足两个需求，其一是哈夫曼编码，即将给定字符，依据自定义的权值编码成01序列，其二是哈夫曼译码，即将01序列，根据给定的权值，译码成字符，并且显示哈夫曼树。并且还有一个要求，将权值数据存放在数据文件(文件名为data.txt，位于执行程序的当前目录中) 分别采用动态和静态存储结构，即权值数据的确定有两种情况，一种是用户自己主动输入的，是程序运行时才确定的，而另一种是静态的，即在没有用户使用的时候就已经确定好了。

主要有两个功能模块：

编码模块（Encode）。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image002.jpg)

图 1 encode模块

译码模块（Decode）。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image004.jpg)

图 2 decode模块

 

两个模块的输入输出均在文本框内以文本的形式，而输入输出的的长度为所使用的浏览器的上限，很大。

关于权重的读取，我添加了一个use default weights选项，如果点击了，那么程序在构建哈夫曼树的时候，其权值将会读取data.txt的值，而当没有选中，程序在构建哈夫曼树的时候其权值将来自用户的输入。

> 测试数据：
>
> 正确的输入与结果：
>
> 1.
>
> 输入：
>
> 左边：helloworld
>
> 权重：点击use default weights
>
> 结果：
>
> ​        右边：01101001111011110110100110110101011111011111
>
> 2.
>
> ​    输入：
>
> ​       右边：00100111101110110011
>
> ​       权重：点击use default weights
>
> ​    结果：
>
> ​       左边：flag
>
> ​       下面：展示哈夫曼树
>
> 3.
>
> ​    输入：
>
> ​       左边：hello
>
> ​       权重：(a,8.2),(b,1.5),(c,2.8),(d,4.3),(e,13),(f,2.2),(g,2),(h,6.1),(i,7),(j,0.15),(k,0.77),(l,4),(m,2.4),(n,6.7),(o,7.5)
>
> ​    结果：
>
> ​       右边：110111100110011011
>
>  
>
> 4.
>
> ​    输入：
>
> ​        右边：10101001110010100
>
> ​       权重：(a,8.2),(b,1.5),(c,2.8),(d,4.3),(e,13),(f,2.2),(g,2),(h,6.1),(i,7),(j,0.15),(k,0.77),(l,4),(m,2.4),(n,6.7),(o,7.5)
>
> ​    结果：
>
> ​       左边：flag
>
> ​       下面：展示哈夫曼树
>
> 错误的输入与结果：
>
> 1.
>
> 输入：
>
> ​        左边：123456
>
> ​        权重：点击use default weights
>
> 结果：
>
> ​        报错：ERROR!!!Character not found!! 
>
> 2.
>
> ​    输入：
>
> ​       右边：00100111101110110011111
>
> ​       权值：点击use default weights
>
> ​    结果：
>
> ​       报错：ERROR!!!invalid digits!! unable to encode!!
>
> 3.
>
> ​       输入：
>
> ​           左边：aaaa
>
> ​           权值：(a,8.2),(b,1.5),(c,2.8),(d,4.3.1.1)
>
> ​       结果：
>
> ​           报错：ERROR!!!please check your char-weight pairs again !!!
>
> 4. 
>
> ​       输入：
>
> ​           右边：11111abcd
>
> ​           权值：点击use default weights
>
> ​       结果：
>
> ​           报错：ERROR!!!Invalid digit found!!! please input 0 and 1 to encode
>
>  

 本程序为web应用，在能够准确编码、译码的基础上，能够同时供多个用户同时使用。并且界面简洁美观，就应用的质量而言比较好。

### **3.2**系统设计

#### 3.2.1 所用到的数据结构及其说明

所用到的数据结构大致如下：BinTree（二叉树）、Comparator（比较器）、ComplBinTree（完全二叉树）、Dequeue（双端队列）、Entry（项）、EqualityTester（判等器）、Map（映射表）、Position（位置）、PriorityQueue（优先队列）、Queue（队列）、Sequence（序列）、Tree（树）、Vector（向量）、HaffmanTree（哈夫曼树）、HaffmanTreeBuilder（哈夫曼树构造器）、Char（字符）、WeightComparator（权重比较器）。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image006.jpg)

图 3 所有数据结构概览

1.BinTree（二叉树）

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image008.jpg)

图 4 BinTree的文件结构

BinTree，即二叉树，主要由BinTreeNode即二叉树节点组成，二叉树节点的接口如图5所示，其属性如图6所示。

Element表示节点存储的元素，parent表示节点的双亲节点，lChild表示节点的左孩子，rChild表示节点的右孩子，size表示以该节点为根的树的所有节点的数量，height表示以该节点为根的树的高度，depth表示以该节点为根的树的深度。 

BinTree的接口如图7所示，BinTree的属性如图8所示，其属性为root，表示二叉树的根节点。

2.Comparator（比较器）

比较器，顾名思义，主要用来实现对象之间的比较，其接口如图9所示。

可见只有一个方法，即comapare方法，当a理应排在b之前时，返回负数，当b理应排在a之前时，返回正数。

3.ComplBinTree（完全二叉树）

完全二叉树，继承自二叉树(BinTree)，即能调用BinTree所有public的方法，同时因为结构的特殊性，增添了其他几个方法，其接口如图10所示。

本次程序中，逻辑上，一个完全二叉树由ComplBinTreeNode，即完全二叉树节点组成，而ComplBinTreeNode用的Vector存储，即用一个顺序存储结构来存储，ComplBinTree的文件结构如图11所示：

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image010.jpg)

图 5 BinTreeNode的接口

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image012.jpg)

图 6 BinTreeNode的属性

 

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image014.jpg)

图 7 BinTree的接口

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image016.jpg)

图 8 BinTree的属性

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image018.jpg)

图 9 Comparator 的接口

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image018.jpgclip_image020.jpg)

图 10 ComplBinTree的接口

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image022.jpg)

图 11 ComplBinTree的文件结构

而本程序中，完全二叉树非常重要，因为还有另一个重要的数据结构，优先队列，是依靠完全二叉树实现的。另外，进行显示树这一功能的代码编写时，也用到了完全二叉树。

4.Deque（双端队列）

本程序实际上没有地方直接用到双端队列，但是逻辑上组成双端队列的DoubleLinkedNode 却非常有用，DoubleLinkedNode 用于List 和 Sequence的实现，而List，即双端链表，在本程序中也起到了不可或缺的作用。

这里简单介绍一下DoubleLinkedNode，其声明和属性如图12所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image024.jpg)

图 12 DoubleLinkedNode的声明和属性

它有三个属性，分别表示存储的元素(element)，直接前驱(previous)，直接后继(next)，而它的方法，主要也与这几个属性的读写有关，其方法如图13所示，是很简单的get 和 set 。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image026.jpg)

图 13 DoubleLinkedNode的方法

5.Entry（项）

Entry 实际上也是作为另个数据结构的基本组成元素而存在，主要作为优先队列(priority queue) 和 映射表(map) 的组成元素，逻辑上，一个项，即一个Entry，具有一对元素，分别是关键字和值，而本程序中的Entry接口也很简单，就是关于关键字和值的读写，如图14所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image028.jpg)

图 14 Entry的接口

6.EqualityTester（判等器）

EqualityTester ，判等器，也是作为另个一个数据结构实现的工具性数据结构而存在，主要功能是判断两个东西是否相等，功能上实际上完全可以被Comparator替代。判等器主要出现在数据结构映射表中，用来判断两个Entry 的key，即关键码是否相等，因此，可想而知，EqualityTester的接口也非常简单，如图15所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image030.jpg)

图 15 EqualityTester的接口

7.Iterator（迭代器）

迭代器，面向对象的设计模式中的一种，这个数据结构的核心思想在于把遍历后的数据用顺序存储结构按遍历顺序搜集起来，这样，当还需遍历的时候，就可以简单地按顺序一个个访问而实现遍历，尤其对于树和图而言，就不用进行复杂的代码的考量。Iterator的接口也非常简单，如图16所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image032.jpg)

图 16 Iterator的接口

hasNext方法，判断迭代器所用的序列是不是空的，getNext，顾名思义，就是得到序列中下一个元素。

本程序中的Iterator都用双端链表，即List来实现，即遍历后得到的元素放在List中存储。本程序实现了两种迭代器，一种是以Position为元素的，另外一种是以Position中的element 为元素。

两种实现，都有两个属性，如图17所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image034.jpg)

图 17 两种实现的两个属性

其中，list用来存储元素 ，而nextPosition指向访问迭代器时所指向的下一个位置。

8.List（双端链表）

双端链表，最为基础的数据结构，在数据结构中，用于直接存储数据，其接口如图18所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image036.jpg)

图 18 List的接口

就其具体实现而言，逻辑上，一个双端链表，由DoubleLinkedNode，即双端节点组成，List的属性如图19所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image038.jpg)

图 19 DoubleLinkedNode的属性

其中，size 表示的是当前这个链表存储的所有元素的个数，header、tailer表示头节点、尾节点。List的文件结构如图20所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image040.jpg)

图 20 List的文件结构

 

9.Map（映射表）

本程序中，只有一处用到了Map这个数据结构，用在HaffmanTree这个数据结构的encode方法，即用于解码的方法中。Map的接口如图21所示。

逻辑上，Map由Entry构成，支持快速根据Entry的关键码，得到Entry。

就实现而言，Map有两种实现方式，一种是用List实现，即将Entry组织成线性结构，一种是用哈希表实现，而哈希表用分离链策略解决冲突。用List实现的Map ，属性如图22所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image042.jpg)

图 21 Map的接口

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image044.jpg)

图 22 Map的属性

其中List用来存储Entry，而tester用来判定Entry的关键字是否相等。Map的增删改查都基于List，即双端链表的增删改查操作。

而用hashtable实现的Map，属性如图23所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image046.jpg)

图 23 Map的属性

其中，buckets数组，称为桶数组，用来存放一个以链表实现的Map（实际上也可以直接用List来实现）。maxLamda，指的是填装因子的最大值，当填装因子大于这个最大值时，桶数组就要扩容。Size表示已经存储的Entry的个数，bucketNum表示桶数组的大小。Tester用于判定Entry是否相等。

10.Position（位置）

这是个比较抽象的数据结构，简单地说，Position表示一个存储结构中的特定位置，所有Node们，无论是链表的节点、树的节点，都继承自Position，本程序中，Position没有具体的实现，只有一个接口，接口如图24所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image048.jpg)

图 24

两个方法，分别用于读写Position中存储的元素。

PQueue（优先队列）

优先队列，可以理解为就是小顶堆，而堆结构由完全二叉树实现，逻辑上，调用delMin方法，每次输出所有存储的元素中最小的元素。接口如图25所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image050.jpg)

图 25 PQueue的接口

本程序中，使用堆来实现优先队列，堆是个完全二叉树，而这个完全二叉树用线性结构Vector来实现，建堆使用Robert-Floyd算法，而每次delMin都采用了下滤算法。具体而言，优先队列的属性如下：

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image052.jpg)

图 26 PQueue的属性

Heap，即用完全二叉树实现的堆，comaparator，用来比较heap中的entry。

11.Queue（队列）

Queue，队列，实际上也没有直接用于本次程序，而是作为其他数据结构的组成和工具性数据结构，用于包括但不限于二叉树的层次遍历、双端队列的实现等。

12.Sequence（序列）

Sequence是一个结合了List和Vector的功能强大的线性结构，同时具有向量和双端链表的特性，即既能够随机访问所存储的数据，元素本身又存储在Position中。接口如图27所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image054.jpg)

图 27 Sequence的接口

除了继承Vector和List的方法外，Sequence添加了两个方法，rank2Pos和pos2Rank，顾名思义，使得rank 和 position对应起来，通过一者可以得到所指的同一元素的另一者。

13.Tree（树）

树，本程序中最为基本的数据结构，是实现二叉树、完全二叉树、哈夫曼树的基础，逻辑上，一棵树由TreeNode组成，可以理解为，TreeNode是一个三叉链表的节点，TreeNode的接口如图28所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image056.jpg)

图 28 TreeNode的接口

TreeNode属性如图29所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image058.jpg)

图 29 TreeNode的属性

Element用来存放元素，parent表示父节点，firstChild表示第一个孩子，nextSibling表示这个节点的下一个兄弟。

而Tree本身的属性，只有一个，如图30所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image060.jpg)

图 30 Tree的属性

Tree的接口如图31所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image062.jpg)

图 31 Tree的接口

而实现Tree的这些方法，主要基于root节点的操作，如图32所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image064.jpg)

图 32 Tree的方法

14.Vector（向量）

又是一个非常简单基础的数据结构，作为其他数据结构的基础，Vector底层用数组实现，使得能够快速随机访问所存储的元素。Vector的接口如图33所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image066.jpg)

图 33 Vector的接口

对于Vector的实现，主要有两种类型，其基础都是数组，其中一种类型是变长的Vector，即当数组填满时，就扩充这个向量的容量，另外一种是不变长的Vector。而很明显，变长的Vector更为实用，因而本程序中用到可变长的Vector，其属性如图34所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image068.jpg)

图 34 Vector的属性

其中，CAPACITY 表示的是向量当前所能存储的元素的最大数量，size表示当前向量已经存储的元素总数，elements数组作为元素实际的存放结构，存放元素。      

15.Char（字符）

本程序中自定义的一个数据结构，其中有两个重要元素，一个是字符，一个是字符所对应的权重，其接口如图35所示，其实现如图36所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image070.jpg)

图 35 Char的接口

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image072.jpg)

图 36 Char的实现

16.HaffmanTree

本程序的重中之重，前面提到的基本数据结构都是为了最终实现这个HaffmanTree而存在，本程序中，一个haffman树对象一共有三个主要的方法：encode（编码），decode（解码），showTree（展示树），其接口如图37所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image074.jpg)

图 37 HaffmanTree的接口

HaffmanTree有三个属性，如图38所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image076.jpg)

图 38 HaffmanTree的属性

其中，haffman作为存储元素的树结构，charSequence用来存放所有的Char，即所有的字符，而leaves中存放的是haffman中所有的叶子节点，即leaves是一个BinaryTreeNode的线性存储结构。

Encode方法的伪代码，如图39所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image078.jpg)

图 39 Encode的伪代码

Decode方法的伪代码如图40所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image080.jpg)

图 40 Decode的伪代码

showLeaf方法的伪代码如图41所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image082.jpg)

图 41 binTree2ComplBinTree的伪代码

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image084.jpg)

图 42 showTree的伪代码

Haffman的encode和decode还有showTree的具体算法实现，将在3.3系统实现下自习探讨。

17.HaffmanTreeBuilder

这是个用来辅助构造HaffmanTree的数据结构，属于一个工具性质的数据结构，其中最重要的是里面的几个算法，其接口如图43所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image086.jpg)

图 43 HaffmanTree的接口

其中有两个最为关键的算法，buildTreePQueue和buildHuffmanTree方法。buildTreePQueue的伪代码如图44所示，buildHaffmanTree的伪代码如图45所示。

函数之间、类之间的相互调用如图46和图47所示。

### **3.3**系统实现

本程序是个web应用，接下来将描述这个web是怎么写的。首先将介绍HaffmanTree和HaffmanTreeBuilder这两个最重要的类中的重要算法，他们实现了本程序的业务逻辑。随后将介绍以下如何使用Servlet-JSP的架构，构造起这个web应用，我会讲讲重要的两个servlet，即Encode和Decode，究竟做了什么，也会讲讲前端是如何编写的，并且我会重点讲述如何实现在前端显示树行结构。

#### 3.3.0 概述

用**Java**写的后端，用到**maven**开发，因为是数据结构课设，所以每个用到的数据结构都是自己实现的，包括但不限于**BinTree**(二叉树)，**ComplBinTree**(完全二叉树)，**Map**(哈希表)，**PQueue**(优先队列)，**Deque**(双端队列)，**List**(链表)，**Vector**(向量)，**Entry**(项)。

用的**MVC** 模式，因为简单，所以用**JSP-Servlet**开发，前端写在JSP中，里面有必要的**javascript**代码，**html**代码，也写了必要的**css**文件

写完的代码最后打成 **war **包，放我的服务器上的一个配备了**tomcat**的**docker **容器当中，并映射到了宿主机的端口，使得可以远程访问(http://inhann.top:25555/haffman/)

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image088.jpg)

图 44 buildTreePQueue的伪代码：

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image090.jpg)

图 45 buidHuffmanTree的伪代码

​    ![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image092.jpg)

图 46 Encode 类、函数相互调用简图

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image094.jpg)

图 47 Decode 类、函数相互调用简图

#### 3.3.1 HaffmanTreeBuilder中的算法

#### 3.3.1.1 buildHuffmanTree算法

HaffmanTreeBuilder的主要功能是构建一个haffman树，构建haffman树的方法如图48所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image096.jpg)

图 48 构架haffman的方法

这个方法以存放Char的Sequence作为传入的参数，返回一个BinTree，实际上这返回的值将作为HaffmanTree的haffman属性。

本方法共进行两步操作。第一步操作，是构建一个优先队列，放在优先队列里的东西是Entry，而Entry的关键码是Char的权重，Entry的值是一个以Char为元素的BinaryTreeNode。

这一步的伪代码如图49所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image088.jpg)

图 49 buildTreePQueue的伪代码

这一步的具体实现如图50所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image098.jpg)

图 50 buildTreePQueue的具体代码

非常简单，简单地遍历chars就行了。在这个过程中还有一件事要做，就是将所生成的作为Entry的值的BinaryTreeNode作为叶子，加入到leaves这个属性当中，leaves是HaffmanTreeBuilder唯一的属性，用来存放接下来要实现的HaffmanTree的叶子节点们。第二步，也非常简单，就是根据贪心算法，每一次都取优先队列中的最小的，出来两个Entry，然后根据这两个Entry构建一个新的Entry，怎么构建的呢？非常简单，就是取出这两个Entry的关键码，关键码是Double，把两个Double加在一起，得到的值就是要新建的Entry的关键码了，名为key，而Entry的值是一个BinaryTreeNode，这个BinaryTreeNode中的元素是Char，这个Char有两个属性一个是Character类型的c，一个是Double类型的key，那么Entry的值就是一个c=null而key=新Double的Char。这样，就很容易地构造出了新的BinaryTreeNode，名为newNode，最后把key和newNode作为参数传回优先队列就行了。

整个伪代码如图51所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image099.jpg)

图 51 buildHuffmanTree的伪代码

整个具体算法如图52所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image101.jpg)

图 52

##### 3.3.1.2 str2Sequence算法

这个算法也比较重要，根据业务逻辑，用户需要输入的字符-权值对需要是这样的形式：(a,1),(b,2),(c,3)

从而，需要从这样原生的字符串中解析出一个个字符-权值对，用以生成Char的Sequence。这里就要用到Java中正则表达式相关的库。将原生字符解析之后，针对每一对字符-权重对确定一个Char，并将这个Char插入到charSequence中。

伪代码如图53所示。实际代码如图54所示。

#### 3.3.2HaffmanTreeImpl中的算法

##### 3.3.2.1 encode算法

​    Encode算法，在哈夫曼树已经建成的基础上，先建立一个辅助用的映射表cache，cache里面放的自然是Entry，而这个Entry的关键码和值有所特别。这个Entry的关键码，是Character，表示的是字符，而这个Entry的值，是这个字符所对应的01序列。这个表建立的目的，是为了使得编码的速度加快，如何加快的，后面会谈到。建好了这个cache之后，遍历传入的字符串中的每一个字符，如果遍历到的字符在cache中能对应得上，那就用其对应的01序列，否则，就从这个字符对应的叶子出发，向上溯源，从而把对应的01序列找出来，找出来后，放到cache里面去。到这里，为什么说cache能够快速实现编码功能就显而易见了，因为对于同一个字符，最开始可能需要从叶子上溯才能确定字符所对应的01序列，但是这之后，当再次遍历到相同的字符时，就不必再进行上述操作，而是以O(1)的时间，从一个哈希表中得到对应的01序列，当要编码的字符串数量很大，通过cache从而提升速度的效果就会很明显。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image103.jpg)

图 53 str2Sequence的伪代码

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image105.jpg)

图 54 str2Sequence 的实际代码

Encode 伪代码如图55所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image106.jpg)

图 55 encode的伪代码

Encode具体算法如图56所示。实际上也比较简单，编写基本上没什么难度。

##### 3.3.2.2 decode算法

​    Decode是解码功能，要由01序列得到字符串。算法的主要思想如下，遍历输入的01序列中的每个字符，即0或者1，同时反复地从根节点出发，如果访问到的是1，就到达右子树，是0就到达左子树，每访问完一个数字，都要确定一下，到达的这个节点是不是叶子节点，如果是叶子节点，就把这个叶子节点中的Char取出来，把它的c属性加入到StringBuilder当中。不断反复，如果输入的01序列确实对应着一个字符串，那么当字符串的每个01都访问完了，这个算法也就结束。最终得到译码。

​    其伪代码如图57所示。具体算法如图58所示。

##### 3.3.2.3 showTree算法

为了实现把哈夫曼树打印到前端，需要想办法把哈夫曼树的排列结构传送出去，本程序采用字符串来传递哈夫曼树的信息。其核心思想是，先把树转换成完全二叉树，然后层次遍历这个完全二叉树，从而得到一个表示了整个完全二叉树结构的字符串，然后把这个字符串传回到前端。在前端，javascript经过解析，画个树在页面当中。

本算法主要分为两个部分，第一个部分是把哈夫曼树转换成一棵完全二叉树，第二部分是层次遍历完全二叉树，并生成要传送的字符串。

用到了一个队列，算法的思想简单来说，先创建一个完全二叉树compl，然后从二叉树的根出发，先把根加入到队列中。然后访问这个队列，每dequeue出一个节点，就这个节点加入到compl中，而节点的左右孩子将被enqueue到队列中，这时候你可能要问了，一个节点又不是都会有左右孩子，那怎么办呢？很简单，有的话就把这个左右孩子加到队列中，没有的话，就创建一个孩子，然后把这个孩子加入队列中。不断进行上述的操作，就能得到一棵包含了所有原来二叉树所有节点的，且位置对应的完全二叉树，当然这样的扩展不可能一直进行，肯定得有个尽头，而这个判断while循环是否应该终止的条件也很简单，当访问到的节点是第一个所处层次刚好大于原来树的层次数的节点，那么while循环就停止。而最后，返回这个完全二叉树compl就好了，其伪代码如图59所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image108.jpg)

图 56 encode的具体算法

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image080.jpg)

图 57 decode的伪代码

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image110.jpg)

图 58 decode的具体算法

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image111.jpg)

图 59 binTree2ComplBinTree的伪代码

具体算法如图60所示。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image113.jpg)

图 60 binTree2ComplBinTree的具体算法

第二部分，要根据返回的完全二叉树，构造一个完美描述这个二叉树结构的字符串。算法思想描述如下：首先构建一个空字符串s，这个字符串将不断扩展，最终作为结果。然后层次遍历完全二叉树，这时候访问到的节点实际上有两种类型，一种是属于原来的haffman，即原来那棵没有转换的二叉树的，另外一种类型，是在转成完全二叉树的过程中新创建的。当访问到新创建的节点，我们就在s中插入null这个字符串，如果访问到的不是新创建的节点，那么又要分两种情况考虑，即这个节点是不是原来的叶子，原来的叶子，有这样的特征：其Char元素的c属性不是null，那么我们就借助这个特征来分辨是不是原来的叶子。如果是，那么s中就插入c，如果不是，那么s中就插入所节点之Char的weight。最后要返回的字符，形式如 [1.0,2.0,’a’,null,]。

为什么要返回这个形式的字符串呢？因为这个形式的字符串可以被javascript解释为数组。其伪代码如图61所示。其具体算法如图62。

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image114.jpg)

图 61 showTree的伪代码

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image116.jpg)

图 62 showTree具体算法

#### 3.3.1 Servlet做了什么？

一共两个Servlet，一个用于处理编码的请求，一个用于处理译码的请求。两个servlet有共同的一部分代码，前端会把是否勾选了use-default-weights的值传过来，即用户是否勾选了要使用默认的字符-权值对。如果判断出勾选了，那就比较复杂了。要从一个data.txt中读取一字符串，这个字符串是默认的字符权值对，之后自然是根据这个默认的、从文件中读到的字符权值对来创建一个哈夫曼树。而如果没有勾选，那就简单多了，就直接根据要encode或者decode的字符串创建一个哈夫曼树。两个servlet最后都重定向回了index.jsp 文件。

而index.jsp 的每次调用，都会检查有没有东西要写到三个框框中。有就填充，没有就不填充。

#### 3.3.2 Jsp中的javascript干了什么？

接下来说说怎么在前端显示树的。根据前文，我们知道，前端从后端得到了一个表示完全二叉树的字符串，形如[1,2,’a’,null,’b’]。众所周知，javascript中表示数组就能这样表示。这是个层次遍历得到的序列，处理的时候按类似堆排序的方式处理就行了，null会被当做空树。这段关于建树的js代码，直接写在jsp的末尾。

### **3.4 ** 用户手册

#### 3.4.1启动

这是个web应用，有两种应用的模式，一种是打开链接：http://inhann.top:25555/haffman/，另外一种是自己部署。如果自己部署的话，这里推荐种简单的方法：直接把haffman.war放到tomcat的webapps文件夹下，然后运行tomcat就行。

#### 3.4.1 使用

1. 使用默认权重

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image118.jpg)

图 63 使用默认权重

点击use default weights，自动填充权重（[权重参考自wiki百科](https://en.wikipedia.org/wiki/Letter_frequency)），如图63所示。

2. 使用自定义权重

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image120.jpg)

图 64 使用自定义权重

直接写就行。注意格式为(字符,小数/整数),(字符,小数/整数)，如图64所示。

3. **编码**(encode)

字符 —> 01序列

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image122.jpg)

图 65 编码

在左边的框，输入字符，点击Encode ，在右边的框中，就能得到对应的01序列，如图65所示。

4. **解码**(decode)

01序列 —> 字符

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image124.gif)

图 66 译码

在右边的框，输入01序列，点击Decode，在左边的框中，就能得到对应的字符，同时还能看到一个简单的哈夫曼树，如图66所示。

### **3.5**测试

#### 3.5.1 使用默认权值，encode

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image126.jpg)

图 67 使用默认权值，编码

 

点击use deafult weights，然后在左边的框框里面输入hello，然后点击encode，在右边得到序列011010011110111101101，如图67所示。

#### 3.5.2 使用默认权值，decode

点击use deafult weights，然后在右边的框框里面输入00100111101110110011，然后点击decode,在左边得到flag，同时下面能看到哈夫曼树，如图68所示。

#### 3.5.3 使用自定义权值，encode

在下面的框框里面输入(a,8.2),(b,1.5),(c,2.8),(d,4.3),(e,13),(f,2.2),(g,2),(h,6.1),(i,7),(j,0.15),(k,0.77),(l,4),(m,2.4),(n,6.7),(o,7.5)，在左边的框框里面输入hello，然后点击encode，在右边得到序列110111100110011011，如图69所示。

#### 3.5.4 使用自定义权值，decode

在右边的框框里面输入10101001110010100，然后点击decode,在左边得到flag，同时下面能看到哈夫曼树，如图70所示。

 

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image128.gif)

图 68 使用默认权值，译码

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image130.jpg)

图 69 使用自定义权值编码

![img](https://cdn.jsdelivr.net/gh/1nhann/hub@master/data/clip_image132.gif)

图 70 使用自定义权值，译码