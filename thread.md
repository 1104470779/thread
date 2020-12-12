# 线程池
## com.ljm.threadpool.EveryTaskOneThread
创建一个线程
## com.ljm.threadpool.ForLoop
for循环创建多个线程
## 创建线程池
### 参数
corePoolSize 指的是核心线程数，线程池在初始化完成后，默认情况下线程池没有任何线程，线程池会等待有任务来时，再创建新线程执行任务
 
maxPoolSize 线程池会核心线程的基础上增加一些额外的线程（核心线程数不足以处理任务），但是线程数会有一个上线。

需要增加线程时判断顺序：corePoolSize -> workQueue -> maxPoolSize 

keepAliveTime  当前线程数多余corePoolSize，如果多余的线程空闲时间超过keepAliveTime，他们就会被终止。

ThreadFactory 

workQueue 
    1）直接交接、2）无界队列、3）有界队列
    
|   parameter  |  fixedThreadPool  |  cachedThreadPool  |  scheduledThreadPool  |  singleThreadPool  |
|  :----: | :----:  | :----:  | :----:  | :----:  |
| corePoolSize | constructor-arg | 0 |  constructor-arg | 1|
| maxPoolSize | same as corePoolSize | Integer.MAX_VALUE | Integer.MAX_VALUE |1|
| keepAliveTime | 0 seconds | 60 seconds | 0 | 0seconds|

### 4中拒接策略

abortPolicy 抛出异常
discardPolicy 抛弃
discardOldestPolicy 抛弃老的线程
callerRunsPolicy 让调用线程执行

### 钩子方法

## ThreadLocal
## 使用场景
### 工具类（initialValue,生成时机由我们控制）
    每隔Thread内有自己的实例副本，不共享
### 避免参数传递（set，生成时机不由我们控制）
### 作用
 每隔线程都有自己独立的对象 
 
 在任何方法中都可以轻松获取到对象
## 好处
    1）线程安全；2）不需要枷锁，提高执行效率；3）节省内存开销；4）免去传参的繁琐。
# Lock
## 为什么synchronized不够用？
- 效率低：锁的释放情况少、试图获得锁时不能设定超时、不能中断一个正在试图获取锁的线程。
- 不够灵活（读写锁更灵活）：加锁和释放的时机单一，每个所仅有单一的条件（某个对象），可能是不够的。
- 无法知道是否成功获取到锁
## 锁的分类
### 线程要不要锁住同步资源（使用者的角度）
- 锁住 : 悲观锁
- 不锁住：乐观锁
### 线程能否共享同一把锁（系统的设计角度分类）
- 可以 ： 共享锁/读锁
- 不可以：独占锁/排它锁/写锁

    要么多读、要么一写。


### 多线程竞争时，是否要排队
- 排队 公平锁
- 先尝试插队，插队失败再排队 非公平锁

    提高效率，避免唤醒带来的空档期。
    
    1）写锁可以随时插队 2）读锁仅在等待队列头结点不是想获取的写锁的线程的时候可以插队。
    
 |     |  优势  |  劣势  |  
 |  :----: | :----:  | :----:  |
 | 公平锁 | 各线程公平平等，每个线程在等待一段时间后总有执行的机会 | 更慢，吞吐量小 |  
 | 非公平锁 | 更快，吞吐量大 | 有可能产生线程饥饿，也就是某些线程在长时间内始终得不到执行 |

### 是否可中断
- 可以 可中断锁
- 不可以 不可中断锁
### 等锁的过程
- 自旋 自旋锁
- 阻塞 非自旋锁

## 锁的升降级 
不能升级，可以降级
`代码演示： com.ljm.lock.reentrantlock.readwrite.UpgradingDowngrade`
### 锁的升级可能会造成死锁
## 自旋锁和阻塞锁
    阻塞或唤醒一个java线程需要操作系统切换CPU状态来完成，这种状态的转换需要耗费处理器时间。
    如果同步代码块中的内容过于简单，状态转换消耗的时间有可能比用户代码执行的时间还要长。
## 锁优化
- 缩小同步代码块
- 尽量不要锁方法
- 减少锁的次数
- 避免人为制造热点
- 锁中不要再包含锁
- 选择合适的锁类型或合适的工具类

# 原子类
## 特点
- 不可分割
## 作用
为了保证并发情况下的线程安全
## 优势
   粒度更细；效率更高（高度竞争的情况除外）
通过unsafe工具实现原子操作；底层实现原理：cas + 自旋  
##缺点
CAS带来的ABA问题和自旋时间过长问题。

## CAS原理
### 概念
CPU的特殊指令
### 原理
比较更新
### 应用场景
乐观锁、并发容器、原子类
### 缺点
ABA问题。





