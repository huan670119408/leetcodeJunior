/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.lang;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;


public class ThreadLocal<T> {
    /**
     * ThreadLocals rely on per-thread linear-probe hash maps attached
     * to each thread (Thread.threadLocals and
     * inheritableThreadLocals).  The ThreadLocal objects act as keys,
     * searched via threadLocalHashCode.  This is a custom hash code
     * (useful only within ThreadLocalMaps) that eliminates collisions
     * in the common case where consecutively constructed ThreadLocals
     * are used by the same threads, while remaining well-behaved in
     * less common cases.
     */
    private final int threadLocalHashCode = nextHashCode();

    /**
     * The next hash code to be given out. Updated atomically. Starts at
     * zero.
     */
    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    /**
     * The difference between successively generated hash codes - turns
     * implicit sequential thread-local IDs into near-optimally spread
     * multiplicative hash values for power-of-two-sized tables.
     */
    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    protected T initialValue() {
        return null;
    }

    /**
     * Creates a thread local variable. The initial value of the variable is
     * determined by invoking the {@code get} method on the {@code Supplier}.
     *
     * @param <S> the type of the thread local's value
     * @param supplier the supplier to be used to determine the initial value
     * @return a new thread local variable
     * @throws NullPointerException if the specified supplier is null
     * @since 1.8
     */
    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new SuppliedThreadLocal<>(supplier);
    }

    public ThreadLocal() {
    }

    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        // 如果map不为null初始化一个key为当前threadLocal值为null的ThreadLocalMap对象
        return setInitialValue();
    }

    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }

    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }

    public void remove() {
        ThreadLocalMap m = getMap(Thread.currentThread());
        if (m != null)
            m.remove(this);
    }

    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }

    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }

    static ThreadLocalMap createInheritedMap(ThreadLocalMap parentMap) {
        return new ThreadLocalMap(parentMap);
    }

    /**
     * Method childValue is visibly defined in subclass
     * InheritableThreadLocal, but is internally defined here for the
     * sake of providing createInheritedMap factory method without
     * needing to subclass the map class in InheritableThreadLocal.
     * This technique is preferable to the alternative of embedding
     * instanceof tests in methods.
     */
    T childValue(T parentValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * An extension of ThreadLocal that obtains its initial value from
     * the specified {@code Supplier}.
     */
    static final class SuppliedThreadLocal<T> extends ThreadLocal<T> {

        private final Supplier<? extends T> supplier;

        SuppliedThreadLocal(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue() {
            return supplier.get();
        }
    }

    static class ThreadLocalMap {

        /**
         * The entries in this hash map extend WeakReference, using
         * its main ref field as the key (which is always a
         * ThreadLocal object).  Note that null keys (i.e. entry.get()
         * == null) mean that the key is no longer referenced, so the
         * entry can be expunged from table.  Such entries are referred to
         * as "stale entries" in the code that follows.
         */
        // 此哈希映射中的条目使用其引用字段作为键（它始终是ThreadLocal对象）继承WeakReference。
        // 注意，null键（即entry.get（）== null）表示不再引用该键，因此可以从表中删除该条目。这些条目在下面的代码中称为“旧条目”。
        // 这些“旧条目”就是脏对象，因为存在引用不会被GC，为避免内存泄露需要代码里清理，将引用置为null，那么这些对象之后就会被GC清理。
        // 实际上后面的代码很大程度上都是在描述如何清理“旧条目”的引用
        static class Entry extends WeakReference<ThreadLocal<?>> {
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }

        private static final int INITIAL_CAPACITY = 16;

        private Entry[] table;

        private int size = 0;

        private int threshold; // Default to 0

        private void setThreshold(int len) {
            threshold = len * 2 / 3;
        }

        private static int nextIndex(int i, int len) {
            return ((i + 1 < len) ? i + 1 : 0);
        }

        private static int prevIndex(int i, int len) {
            return ((i - 1 >= 0) ? i - 1 : len - 1);
        }
        
        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
            table = new Entry[INITIAL_CAPACITY];
            int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
            table[i] = new Entry(firstKey, firstValue);
            size = 1;
            setThreshold(INITIAL_CAPACITY);
        }

        /**
         * Construct a new map including all Inheritable ThreadLocals
         * from given parent map. Called only by createInheritedMap.
         *
         * @param parentMap the map associated with parent thread.
         */
        private ThreadLocalMap(ThreadLocalMap parentMap) {
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];

            for (int j = 0; j < len; j++) {
                Entry e = parentTable[j];
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                    if (key != null) {
                        Object value = key.childValue(e.value);
                        Entry c = new Entry(key, value);
                        int h = key.threadLocalHashCode & (len - 1);
                        while (table[h] != null)
                            h = nextIndex(h, len);
                        table[h] = c;
                        size++;
                    }
                }
            }
        }

        private Entry getEntry(ThreadLocal<?> key) {
            int i = key.threadLocalHashCode & (table.length - 1);
            Entry e = table[i];
            if (e != null && e.get() == key)
                return e;
            else // 直接散列找不到的情况，调用getEntryAfterMiss线性探测查找期望条目
                return getEntryAfterMiss(key, i, e);
        }

        private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e) {
            Entry[] tab = table;
            int len = tab.length;
            // 线性探测找到符合的元素，若遇到旧条目则进行清理
            while (e != null) {
                ThreadLocal<?> k = e.get();
                if (k == key)
                    return e;
                if (k == null)
                    expungeStaleEntry(i);
                else
                    i = nextIndex(i, len);
                e = tab[i];
            }
            return null;
        }

        private void set(ThreadLocal<?> key, Object value) {
            // We don't use a fast path as with get() because it is at
            // least as common to use set() to create new entries as
            // it is to replace existing ones, in which case, a fast
            // path would fail more often than not.
            // 我们不像get（）那样先使用快速路径（直接散列）判断
            // 因为使用set（）创建新条目至少与替换现有条目一样频繁，在这种情况下，快速路径会更频繁地失败。
            // 所以直接先线性探测
            Entry[] tab = table;
            int len = tab.length;

            // 根据hashcode散列到数组位置
            int i = key.threadLocalHashCode & (len-1);
            // 开放地址法处理散列冲突，线性探测找到可以存放的位置
            // 遍历数组找到下一个可以存放条目的位置，这种位置包含三种情况
            // 1.条目的key已存在，直接赋值value
            // 2.条目的key位null，说明k作为弱引用被GC清理，该位置为旧数据，需要被替换
            // 3.遍历到一个数组位置为null的位置赋值
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                ThreadLocal<?> k = e.get();

                if (k == key) {//key已存在则直接更新
                    e.value = value;
                    return;
                }
                if (k == null) { //e不为null但k为null说明k作为弱引用被GC，是旧数据需要被清理
                    // i为旧数据位置，清理该位置并依据key/value合理地散列或替换到数组中，重新散列i后面的元素，并顺便清理i位置附近的其他旧条目
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }
            // 遍历到一个数组位置为null的位置赋值
            tab[i] = new Entry(key, value);
            int sz = ++size;
            // 调用cleanSomeSlots尝试性发现并清理旧条目，如果没有发现且旧条目当前容量超过阈值，则调用rehash
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                // 此时认为表空间不足，全量遍历清理旧条目，清理后判断容量若大于阈值的3/4，若是则扩容并从新散列
                rehash();
        }

        // 根据key移除旧条目，调用expungeStaleEntry清理i位置的同时，清理i附近的旧条目
        private void remove(ThreadLocal<?> key) {
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                if (e.get() == key) {
                    e.clear();
                    expungeStaleEntry(i);
                    return;
                }
            }
        }


        private void replaceStaleEntry(ThreadLocal<?> key, Object value,
                                                int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;
            // Back up to check for prior stale entry in current run.
            // We clean out whole runs at a time to avoid continual
            // incremental rehashing due to garbage collector freeing
            // up refs in bunches (i.e., whenever the collector runs).
            // 向前检查是否存在旧条目，一次性彻底清理由于GC清除的弱引用key导致的旧数据，避免多次执行
            int slotToExpunge = staleSlot;
            // 向前遍历找到entry不为空且key为null的位置赋值给slotToExpunge
            for (int i = prevIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = prevIndex(i, len))
                if (e.get() == null)
                    slotToExpunge = i;

            // Find either the key or trailing null slot of run, whichever
            // occurs first
            // staleSlot位置向后遍历如果位置不为空，判断key是否已经存在
            // 回想前面我们是set实例的时候，碰到旧条目的情况下调用该方法，所以很可能在staleSlot后面key是已经存在的
            for (int i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();

                // If we find key, then we need to swap it
                // with the stale entry to maintain hash table order.
                // The newly stale slot, or any other stale slot
                // encountered above it, can then be sent to expungeStaleEntry
                // to remove or rehash all of the other entries in run.
                // 如果我们找到键，那么我们需要将它与旧条目交换以维护哈希表顺序。
                // 然后可以将交换后得到的旧索引位置或其上方遇到的任何其他旧索引位置发送到expungeStaleEntry以清理旧条目或重新运行运行中的所有其他条目
                // 如果碰到key相同的值则覆盖value
                if (k == key) {
                    e.value = value;
                    // i位置与staleSlot旧数据位置做交换，将数组条目位置规范化，维护哈希表顺序
                    // 这里维护哈希表顺序是必要的，举例来说，回想前面threadLocal.set实例的判断，是线性探测找到可以赋值的位置
                    // 如果哈希顺序不维护，可能造成同一个实例被赋值多次的情况
                    // 包括后面清理旧条目的地方都要重新维护哈希表顺序
                    tab[i] = tab[staleSlot];
                    tab[staleSlot] = e;
                    // Start expunge at preceding stale entry if it exists
                    // 开始清理前面的旧条目
                    // 如果前面向前或向后查找的旧条目不存在，也就是slotToExpunge == staleSlot，此时slotToExpunge = i，此时位置i的条目是旧条目，需要被清理
                    // slotToExpunge用来存储第一个需要被清理的旧条目位置
                    if (slotToExpunge == staleSlot)
                        slotToExpunge = i;
                    // 清理完slotToExpunge位置及其后面非空连续位置后，通过调用cleanSomeSlots尝试性清理一些其他位置的旧条目
                    // cleanSomeSlots不保证清理全部旧条目，它的时间复杂度O(log2n)，他只是全量清理旧条目或不清理的折中
                    cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                    return;
                }

                // If we didn't find stale entry on backward scan, the
                // first stale entry seen while scanning for key is the
                // first still present in the run.
                // 如果前面向前查找的旧条目不存在，也就是slotToExpunge == staleSlot,而此时位置i为旧条目，所以将i赋值给slotToExpunge
                // slotToExpunge用来存储第一个需要被清理的旧条目位置
                if (k == null && slotToExpunge == staleSlot)
                    slotToExpunge = i;
            }

            // If key not found, put new entry in stale slot
            // 如果向后遍历非空entry都没有找到key，则直接赋值给当前staleSlot旧条目位置
            tab[staleSlot].value = null;
            tab[staleSlot] = new Entry(key, value);

            // If there are any other stale entries in run, expunge them
            // 通过前面根据staleSlot向前/向后遍历，如果发现有旧条目则清理
            if (slotToExpunge != staleSlot)
                // 清理完slotToExpunge位置及其后面非空连续位置后，通过调用cleanSomeSlots尝试性清理一些其他位置的旧条目
                // cleanSomeSlots不保证清理全部旧条目，它的时间复杂度O(log2n)，他只是全量清理旧条目或不清理的折中
                cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
        }

        // 清理staleSlot位置，以及将清理staleSlot后所有非空位置重新散列，staleSlot后所有非空位置如果有旧条目也会被清理
        // 该方法的总结就是：清理包括staleSlot位置后面的所有旧条目并重新散列，返回staleSlot后面首个空位置
        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            // expunge entry at staleSlot
            // 清空staleSlot位置的条目
            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            size--;

            // Rehash until we encounter null
            // 旧位置清理后，后面的条目需要重新散列到数组里，直到遇到数组位置为null。即维护哈希顺序。
            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();
                if (k == null) { // k == null说明此位置也是旧数据，需要清理
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    int h = k.threadLocalHashCode & (len - 1);
                    // 将staleSlot后面不为空位置重新散列，如果与当前位置不同，则向前移动到h位置后面（包括h）的首个空位置
                    if (h != i) {
                        tab[i] = null;
                        // Unlike Knuth 6.4 Algorithm R, we must scan until
                        // null because multiple entries could have been stale.
                        while (tab[h] != null)
                            h = nextIndex(h, len);
                        tab[h] = e;
                    }
                }
            }
            return i;
        }

        // 尝试性地寻找一些旧条目。添加新元素或删除另一个旧元素时会调用此方法。
        // 它执行对数扫描log2(n)，作为无扫描（快速但保留垃圾）和多次扫描之间的平衡(执行效率与元素数量成正比，可以找到所有元素垃圾，但会导致一些插入需要O（n）时间)
        // 若发现旧条目返回true
        private boolean cleanSomeSlots(int i, int n) {
            boolean removed = false;
            Entry[] tab = table;
            int len = tab.length;
            do {
                i = nextIndex(i, len);
                Entry e = tab[i];
                if (e != null && e.get() == null) {
                    n = len;
                    removed = true;
                    i = expungeStaleEntry(i);
                }
            } while ( (n >>>= 1) != 0);// 无符号右移，即执行次数以n的二进制最高位的1的位置为基准，所以时间复杂度log2(n)
            return removed;
        }

        // rehash全量地遍历清理旧条目，然后判断容量若大于阈值的3/4，则扩容并从新散列
        // 程序认为表空间不足时会调用该方法
        private void rehash() {
            // 全量遍历清理旧条目
            expungeStaleEntries();
            // Use lower threshold for doubling to avoid hysteresis
            // 适当的扩容，以避免hash散列到数组时过多的位置冲突
            if (size >= threshold - threshold / 4)
                // 2倍扩容并重新散列
                resize();
        }

        // 二倍扩容
        private void resize() {
            Entry[] oldTab = table;
            int oldLen = oldTab.length;
            int newLen = oldLen * 2;
            Entry[] newTab = new Entry[newLen];
            int count = 0;

            for (int j = 0; j < oldLen; ++j) {
                Entry e = oldTab[j];
                if (e != null) {
                    ThreadLocal<?> k = e.get();
                    if (k == null) {
                        e.value = null; // Help the GC
                    } else {
                        int h = k.threadLocalHashCode & (newLen - 1);
                        while (newTab[h] != null)
                            h = nextIndex(h, newLen);
                        newTab[h] = e;
                        count++;
                    }
                }
            }

            setThreshold(newLen);
            size = count;
            table = newTab;
        }

        // 全量遍历清理旧条目
        private void expungeStaleEntries() {
            Entry[] tab = table;
            int len = tab.length;
            for (int j = 0; j < len; j++) {
                Entry e = tab[j];
                if (e != null && e.get() == null)
                    expungeStaleEntry(j);
            }
        }
    }
}
