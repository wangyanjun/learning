# summary of pointer

# smart pointer unique_ptr shared_ptr weak_ptr

## 1. std::tr1::shared_ptr & std::tr1::make_shared
std::shared_ptr 是通过指针保持对象共享所有权的智能指针。多个 shared_ptr 对象可占有同一对象。下列情况之一出现时销毁对象并解分配其内存：

最后剩下的占有对象的 *shared_ptr* 被销毁；
最后剩下的占有对象的 *shared_ptr* 被通过 operator= 或 reset() 赋值为另一指针。
用 *delete* 表达式或在构造期间提供给 *shared_ptr* 的定制删除器销毁对象。

*shared_ptr* 能在存储指向一个对象的指针时共享另一对象的所有权。此特性能用于在占有其所属对象时，指向成员对象。存储的指针为 get() 、解引用及比较运算符所访问。被管理指针是在 use_count 抵达零时传递给删除器者。

*shared_ptr* 亦可不占有对象，该情况下称它为空 (empty) （空 shared_ptr 可拥有非空存储指针，若以别名使用构造函数创建它）。

shared_ptr 的所有特化满足可复制构造 (CopyConstructible) 、可复制赋值 (CopyAssignable) 和可比较小于 (LessThanComparable) 的要求并可按语境转换为 bool 。

多个线程能在 shared_ptr 的不同实例上调用所有成员函数（包含复制构造函数与复制赋值）而不附加同步，即使这些实例是副本，且共享同一对象的所有权。若多个执行线程访问同一 shared_ptr 而不同步，且任一线程使用 shared_ptr 的非 const 成员函数，则将出现数据竞争；原子函数的 shared_ptr 特化能用于避免数据竞争。

~~~c++
#include <iostream>
#include <memory>
#include <thread>
#include <chrono>
#include <mutex>
 
struct Base
{
    Base() { std::cout << "  Base::Base()\n"; }
    // 注意：此处非虚析构函数 OK
    ~Base() { std::cout << "  Base::~Base()\n"; }
};
 
struct Derived: public Base
{
    Derived() { std::cout << "  Derived::Derived()\n"; }
    ~Derived() { std::cout << "  Derived::~Derived()\n"; }
};
 
void thr(std::shared_ptr<Base> p)
{
    std::this_thread::sleep_for(std::chrono::seconds(1));
    std::shared_ptr<Base> lp = p; // 线程安全，虽然自增共享的 use_count
    {
        static std::mutex io_mutex;
        std::lock_guard<std::mutex> lk(io_mutex);
        std::cout << "local pointer in a thread:\n"
                  << "  lp.get() = " << lp.get()
                  << ", lp.use_count() = " << lp.use_count() << '\n';
    }
}
 
int main()
{
    std::shared_ptr<Base> p = std::make_shared<Derived>();
 
    std::cout << "Created a shared Derived (as a pointer to Base)\n"
              << "  p.get() = " << p.get()
              << ", p.use_count() = " << p.use_count() << '\n';
    std::thread t1(thr, p), t2(thr, p), t3(thr, p);
    p.reset(); // 从 main 释放所有权
    std::cout << "Shared ownership between 3 threads and released\n"
              << "ownership from main:\n"
              << "  p.get() = " << p.get()
              << ", p.use_count() = " << p.use_count() << '\n';
    t1.join(); t2.join(); t3.join();
    std::cout << "All threads completed, the last one deleted Derived\n";
}
~~~