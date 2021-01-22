## 学习笔记

### GC总结

#### 常用的GC参数
- 打印GC日志详情：-XX:+PrintGCDetails
- 指定GC生成日志文件: -Xloggc:fileName.log
- 开启GC日志打印时间戳: -XX:+PrintGCTimeStamps
- 指定堆内存大小: -Xms4g -Xmx4g
- 输出每次GC持续时间和程序暂停时间: -XX:+PrintGCApplicationStoppedTime
- 输出GC清理多少引用类型:-XX:+PrintReferenceGC

#### GC事件类型
##### Minor GC(小型GC、YGC): 收集年轻代内存的GC事件
- JVM无法为新对象分配内存时，如果对象分配速率快，Minor GC次数会变多
- 不会清理老年代，老年代指向年轻代的引用会被当做GC Root, 年轻代指向老年代的引用忽略
- 会引起STW,挂起所有进程，Minor GC引起的暂停通常可以忽略不计，但当Eden区大量新创建对象不能被清理时会产生很大的停顿

##### Major GC: 清理老年代空间(Old Space)
##### Full GC: 清理整个堆内存，包括年轻代和老年代

#### Serial GC 
- 命令：-XX:+UseSerialGC
- 类型：串行GC
- 适合场景：堆内存较低及对延迟不敏感的系统。
- GC日志含义:
	GC:表示年轻代GC后面跟触发GC的原因，
		使用垃圾收集器DefNew：单线程、标记-复制、STW垃圾收集器
	Full GC：对老年代及metaspace进行清理，
		使用垃圾收集器Tenured：单线程、标记-清除-整理、STW垃圾收集器

#### Paraller GC
- 命令：
	开启：-XX:+UseParallelGC
	指定GC线程：-XX:ParallerGCThreads=N
- 类型：并行GC
- 适合场景：多核服务器，对延迟要求不严格的系统
- 使用算法：
	年轻代：标记-复制
	老年代：标记-清除-整理
- 特点：年轻代和老年代GC都会触发STW，在标记和复制/整理时使用多线程

#### CMS GC
- 命令: -XX:+UseConcMarkSweepGC
- 类型:并行GC
- 适合场景: 多核cpu服务器，调优目标是降低GC停顿造成的系统卡顿
- 算法: 年轻代使用**mark-copy(标记-复制)**、老年代使用**mark-sweep(标记清除)**
- 特点: 不对老年代进行整理，使用空闲列表管理内存空间的回收;在标记-清除阶段大部分工作线程和应用线程并发执行,减少很多垃圾收集器执行时的停顿时间.在堆内存较大是会产生老年代内存碎片，造成不可预估的暂停时间.

##### Full GC 过程:

1.初始标记(Initial Mark):初始**标记所有根对象**(包含GC ROOT引用对象)及**被年轻代存活对象引用**的对象，会STW但时间可以忽略不计

2.并发标记(Concurrent Mark):从1阶段标记的ROOT开始**遍历老年代并标记所有存活对象**

3.并发预清理(Concurrent Preclean):统计上一步标记执行过程发生更改的对象，使用卡片标记统计脏区

4.可取消的并发预清理(Concurrent Abortable Preclean):尝试在Final Remark之前干更多活,此阶段运行时间取决于很多因素,完成的工作可能对最终STW有较大影响,有**配置选项和失败模式选择**

5.最终标记(Final  Remark):此次GC**最后一次STW**,完成老年代中所有存活对象标记,在STW之后执行重新扫描(Rescan)来完成存活对象标记(并行),此阶段分为4个子阶段

6.Concurrent Sweep(并发清除):删除不再使用的对象,回收内存空间不会引起STW

7.并发重置(Concurrent Reset):重置CMS算法相关的数据结构

###### 最终标记(Final  Remark)4个子阶段
- weak refs processing: 处理弱引用
- class unloading: 对不使用的类进行卸载
- scrub symbol table: 清理符号表
- scrub string table: 清理内联字符串对应string table

#### G1 GC
##### 命令: 
- **开启**:-XX:+UseG1GC
- 初始年轻代大小默认5%heap：-XX:G1NewSizePercent
- 最大年轻代占堆内存大小: -XX:G1MaxNewSizePercent
- 与Java线程一起执行的线程数量,影响回收效率: -XX:ConcGCThreads
- 设置Region大小单位MB(1,2,4,8,16,32): -XX:G1HeapRegionSize
- **回收循环启动阈值(IHOP)**,默认堆内存45%: -XX:+InitiatingHeapOccupancyPercent
- 停止回收最小内存大小默认5%: -XX:G1HeapWasterPercent
- **预期GC暂停时间**默认200ms: -XX:MaxGCPauseMills
##### GC过程

1.初始标记(Initial Mark):初始标记所有GC根对象可达对象,不需要STW

2.Root区扫描(Roo Region Scan): 标记所有根区域(非空区域及不得不收集的区域)可达存活对象

3.并发标记(Concurrent Mark): 遍历对象毒，标记可访问对象

4.再标记(Remark): 停止应用线程(STW),标记前一步未标记的存活对象，也会进行类卸载和引用处理

5.清理(cleanup): 为转移阶段准备，统计堆块所有存活对象并排序，回收不包含存活对象的堆块，会有短暂的stw

###### 混合收集: 在标记完成之后执行,将年轻代和老年大加入回收集
- 算法: 标记-整理 + 复制算法
- 适用场景: 对延迟要求低的系统及堆内存较大的多核心服务器

#### ZGC
##### 命令:
- 启用实验性质VM: -XX:+UnlocakExperimentalVmOptions
- 开启: -XX: +UseZGC
- 固定间隔进行GC默认为0：-XX:ZCollectionInterval
- 内存分配速率修正因子默认2: -XX:ZAllocationSpikeTolerance
- 主动回收策略(boolean): -XX:ZProactive
- 不归还OS内存: -XX:ZUncommit
- 使用大内存页: -XX:+UseLargePages -XX:ZPath
- 启用NUMA支持： -XX:UseNUMA
##### 特点
- 最大GC停顿时间不超过10ms
- jdk13堆内存最高支持16TB
- 与G1比较应用吞吐量下降不超过15%
##### 原理：
- 暂停-标记开始阶段：STW,标记根对象集合指向的对象
- 并发标记/重映射: 遍历对象图，标记对象
- 暂停-标记结束阶段：STW,同步清楚弱根对象
- 并发准备重定位阶段: 引用处理，若对象清理
- 暂停-重定位开始阶段：STW, 根对象指向重定向集合
- 并发重定位阶段: 重定向集合中对象重定向
##### 这六个阶段大部分时间都是并发执行要考虑下面两种情况
- 需要把一个对象拷贝到另一个地址，这时可能存在另外的线程使用和修改此对象
- 及时拷贝完成，堆中引用指向了老地址，也需要修改引用
##### ZGC使用"着色指针"和"读屏障"技术解决这两个问题
- 着色指针：使用着色指针标记GC阶段，因为着色指针是64位指针所以不支持32位系统，从标记就可以直接了解对象状态
- 读屏障：可以理解为一段代码，后面挂载着对应处理函数

#### Shenandoah GC
##### 命令:
- 使用内存分页: -XX:+AlwaysPreTouch
- 使用-XX:+UseTransparentHugePages提高堆性能
- 启发式参数告诉GC何时处理：-XX:ShenanadoahGCHeuristics=<name>
	
###### 启发式参数
1.自适应模式(adaptive):
- 触发"学习"集合的初始阈值: -XX:ShenandoahInitFreeThreshold=#
- 启发式无条件触发GC的可用空间阈值: -XX:ShenandoahMinFreeThreshold=#
- 保留堆内存应对内存分配峰值: -XX:ShenandoahAllocSpikeFactor=#
- 设置在将区域标记收集之前需要包含的垃圾百分比: -XX:ShenandoahGarbageThreshold=#

2.静态模式(static):根据堆内存使用率和内存分配压力决定是否启动GC周期
- 设置空闲堆百分比阈值: -XX:ShenandoahFreeThreshold=#
- 设置内存分配量百分百阈值: -XX:ShenandoahAllocationThreshold=#
- 设置小堆块标记为可回收百分百阈值: -XX:ShenandoahGarbageThreshold=#
- 设置GC启动周期时的可用堆百分比阈值: -XX:ShenandoahAllocationThreshold=#
- 设置从上个GC周期到新的GC周期开始之前的内存分配百分比阈值: -XX:ShenandoahAllocationThreshold=#
- 设置在将区域标记为收集之前需要包含的垃圾百分比阈值: -XX:ShenandoahGarbageThreshold=#

3.紧凑模式(compact): 只要有内存分配，就会连续运行GC回收，并在上一个周期结束后立即开始下一个周期，有吞吐量开销但能提供迅速的内存回收
- 设置并发GC线程数： -XX:ConcGCThreads=#
- 设置从上个GC周期到新的GC周期开始之前的内存分配百分比: -XX:ShenandoahAllocationThreshold=#

4.被动模式(passive): 内存用完发送stw，用于系统诊断和功能测试

5.积极模式(aggressive): 在上一个GC周期完成时启动新的GC周期，并将所有存活对象归集到一起，这将严重影响性能，但可以用来测试GC自身

##### 特点:
- GC线程和应用线程并行执行堆压缩、标记和整理消除大部分暂停时间。
- 保证无论堆大小都有很低的暂停时间
##### 原理:
- 初始标记阶段(Init Mark):STW,并发标记扫描根对象集，由于根对象集很小所以暂停时间很小。
- 并发标记阶段(Concurrent Mark): 并发标记遍历堆，跟着可达对象，持续时间取决于存活对象数量及推中对象结构
- 最终标记(Final Mark): 排空所有等待中的标记/更新队列，重新扫描根对象集。STW时间主要消耗在排空队列和扫描根对象上
- 并发清理阶段(Concurrent Cleanup): 回收即使的垃圾区域
- 并发转移(Concurrent Evacuation): 将对象从各个不同区域复制到指定区域
- 初始引用更新(Init Update Refs): stw,确保所有GC和程序线程都完成转移，为下一步做准备
- 并发引用更新(Concurrent Update References): 遍历堆，并发更新引用，将引用更新为在并发期间转移的对象(不在乎对象图结构,线性扫描)
- 最终引用更新(Final Update Refs): 更新现有根对象集合来完成更新引用，stw时间取决于根对象集大小
- 并发清理(Concurretn Cleanup): 回收没有引用的区域
