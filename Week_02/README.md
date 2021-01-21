## 学习笔记

### GC总结
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

