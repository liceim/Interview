// "static void main" must be defined in a public class.
interface MyMap<K,V> {
    /**
     * put方法
     * @param k
     * @param v
     * @return
     */
    V put(K k, V v);
    /**
     * get方法
     * @param k
     * @return
     */
    V get(K k);
    int size();
    /**
     * Entry内部接口
     * @param <K>
     * @param <V>
     */
    interface MyEntry<K, V>{
        /**
         * 根据entry对象获取key值
         * @return
         */
        K getKey();
        /**
         * 根据entry对象获取value值
         * @return
         */
        V getValue();
    }
}

class Entry<K, V> implements  MyMap.MyEntry<K, V> {
        K k;
        V v;
        
        Entry<K, V> next;
        
        public Entry(K k, V v, Entry next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    
        @Override
        public K getKey() {
            return k;
        }
    
        @Override
        public V getValue() {
            return v;
        }
}

class MyHashMap<K, V> implements MyMap<K, V> {
    //默认数组大小，初始大小为16
    private static int defaultLength = 16;
    //默认负载因子，为0.75
    private static double defaultLoader = 0.75;
    //Entry数组
    private Entry<K, V>[] table = null;
    //HashMap的大小
    private int size = 0;
    
    /**
     * 自定义默认长度和负载因子
     * @param length
     * @param loader
     */
    public MyHashMap(int length, double loader) {
        defaultLength = length;
        defaultLoader = loader;
        //初始化数组
        table = new Entry[defaultLength];
    }
    
    /**
     * 使用默认值
     */
    public MyHashMap() {
        this(defaultLength, defaultLoader);
    }
    
    /**
     * 自定义哈希算法
     * 根据key的哈希值得到一个index索引，即存放到数组中的下标
     * @param k
     * @return
     */
    private int getKey(K k) {
        int m = defaultLength;
        int index = k.hashCode() % m;
        return index >= 0 ? index : -index;
    }
    
    public V put(K k, V v) {
        //判断size是否达到扩容的标准
        if (size >= defaultLength * defaultLoader) {
            expand();
        }
        //根据key和哈希算法算出数组下标
        int index = getKey(k);
        Entry<K, V> entry = table[index];
        //判断entry是否为空
        if (entry == null) {
            /*
            * 如果entry为空，则代表当前位置没有数据。
            * new一个entry对象，内部存放key，value。
            * 此时next指针没有值，因为这个位置上只有一个entry对象
            * */
            table[index] = new Entry(k, v, null);
            size++;
        } else {
            /*
            * 如果entry不为空，则代表当前位置已经有数据了
            * new一个entry对象，内部存放key，value。
            * 把next指针设置为原本的entry对象并且把数组中的数据替换为新的entry对象
            * */
            table[index] = new Entry<K, V>(k, v, entry);
        }
        return table[index].getValue();
    }

    public V get(K k) {
        //获取此key对应的entry对象所存放的索引index
        int index = getKey(k);
        //非空判断
        if (table[index] == null) {
            return null;
        }
        //调用方法找到真正的value值并返回。
        return findValueByEqualKey(k,table[index]);
    }
    
    public void delete(K k) {
        int index = getKey(k);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            return;
        } else {
            Entry<K, V> item = find(entry, k);
            if (item.next == null) {
                return;
            }
            
            item.next = item.next.next;
        }
    }
    
    // Form bucket to find prev node
	Entry<K, V> find(Entry<K, V> entry, K k) {
		Entry<K, V> n = entry;
		Entry<K, V> pre = null;
		while (n != null && n.k != k) {
			pre = n;
			n = n.next;
		}
		return pre;
	}
    
     /**
     *
     * 通过递归比较key值的方式找到真正我们要找的value值
     * @param k
     * @param entry
     * @return
     */
    public V findValueByEqualKey(K k , Entry<K,V> entry) {
        /*
        * 如果传进来的key等于这个entry的key值，说明这个就是我们要找的entry对象
        * 那么直接返回这个entry的value
        * */
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        } else {
            /*
            * 如果不相等，说明这个不是我们要找的entry对象，
            * 通过递归的方式去比较它的next指针中的entry的key值，来找到真正的entry对象
            * */
            if (entry.next != null) {
                return findValueByEqualKey(k, entry.next);
            }
        }
        return entry.getValue();
    }
    
    //返回HashMap的大小
    public int size() {
        return size;
    }
    
    //数组的扩容
    private void expand() {
        //创建一个大小是原来两倍的entry数组
        Entry<K, V>[] newTable = new Entry[2 * defaultLength];
        //重新散列
        rehash(newTable);
        
        table = newTable; 
        defaultLength = 2 * defaultLength;
    }
    
    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }
    
    //重新散列的过程
    private void rehash(Entry<K,V>[] newTable) {
        int newCapacity = newTable.length;
        //遍历整个数组
        for(int i = 0; i < table.length; i++) {
            //如果数组中的某个位置没有数据，则跳过
            if (table[i] == null) {
                continue;
            }
            
            Entry<K, V> e = table[i];               //取得旧Entry数组的每个元素
            if (e != null) {
                table[i] = null;                    //释放旧Entry数组的对象引用（for循环后，旧的Entry数组不再引用任何对象）
                do {
                    Entry<K, V> next = e.next;
                    int j = indexFor(i, newCapacity); //！！重新计算每个元素在数组中的位置
                    e.next = newTable[j]; //标记[1]
                    newTable[j] = e;      //将元素放在数组上
                    e = next;             //访问下一个Entry链上的元素
                } while (e != null);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyHashMap<String,String > map = new MyHashMap();
        Long t1 = System.currentTimeMillis();
        for (int i=0; i<1000;i++) {
            map.put("key" + i, "value" + i);
        }
        for (int i = 0; i < 1000; i++) {
            System.out.println("key: " + "key" + i +"---"+ "value: " + map.get("key" + i));
        }
        Long t2 = System.currentTimeMillis();
        System.out.println("MyHashMap耗时："+(t2-t1));
        System.out.println("-------------------HashMap-------------------------" );
        Map<String,String > hashMap = new HashMap();
        Long t3 = System.currentTimeMillis();
        for (int i=0; i<1000;i++) {
            hashMap.put("key" + i, "value" + i);
        }
        for (int i = 0; i < 1000; i++) {
            System.out.println("key: " + "key" + i + "---"+ "value: " + hashMap.get("key" + i));
        }
        Long t4 = System.currentTimeMillis();
        System.out.println("JDK的HashMap耗时："+(t4-t3));
    }
}
