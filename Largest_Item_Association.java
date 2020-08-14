class PairString {
    String first;
    String second;

    PairString(final String first, final String second) {
        this.first = first;
        this.second = second;
    }
}

class Group {
        String parent;
        List<String> items;

        Group(final String parent) {
            this.parent = parent;
            this.items = new ArrayList<>();
        }
    }

class UnionFind {
    public final Map<String, Group> map = new HashMap<>();

    public void initialize(final List<PairString> pairs) {

        for (final PairString pair : pairs) {
            final Group first = new Group(pair.first);
            final Group second = new Group(pair.second);

            first.items.add(pair.first);
            second.items.add(pair.second);

            map.put(pair.first, first);
            map.put(pair.second, second);
        }
    }

    public void union(final String a, final String b) {
        final String parentA = find(a);
        final String parentB = find(b);

        if (!parentA.equals(parentB)) {
            map.get(parentB).parent = parentA;
            for(String childOfB : map.get(parentB).items) {
                map.get(parentA).items.add(childOfB);
            }
        }
    }

    public String find(final String a) {
        if (map.get(a).parent.equals(a)) {
            return a;
        }

        return find(map.get(a).parent);
    }
}

public class Main {
        
    private static List<String> largestItemAssociation(final List<PairString> itemAssociation) {

        final UnionFind uf = new UnionFind();
        uf.initialize(itemAssociation);

        for (PairString pair : itemAssociation) {
            uf.union(pair.first, pair.second);
        }

        String largest = "";
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Group> entry : uf.map.entrySet()) {
            if (entry.getValue().items.size() > max) {
                max = entry.getValue().items.size();
                largest = entry.getKey();
            } else if(entry.getValue().items.size() == max) {
                final String key = String.join("", uf.map.get(largest).items);
                if(String.join("", uf.map.get(entry.getKey()).items).compareTo(key) < 0) {
                    largest = entry.getKey();
                }
            }
        }

        return uf.map.get(largest).items;
    }

    public static void main(final String[] args) {
            final PairString p1 = new PairString("Item1", "Item2");
            final PairString p2 = new PairString("Item3", "Item4");
            final PairString p3 = new PairString("Item4", "Item5");
            final List<PairString> list = Arrays.asList(p1, p2, p3);

            final PairString p21 = new PairString("item3","item4");
            final PairString p22 = new PairString("item1","item2");			
            final PairString p23 = new PairString("item5","item6");
            final PairString p24 = new PairString("item4","item5");
            final PairString p25 = new PairString("item2","item7");
            final PairString p26 = new PairString("item7","item8");
            final PairString p27 = new PairString("item2","item3");
            final PairString p28 = new PairString("item6","item7");
            final PairString p29 = new PairString("item0","item2");
            final List<PairString> list2 = Arrays.asList(p21, p22, p23, p24, p25, p26, p27, p28, p29);
        
            final PairString p31 = new PairString("item8","item9");
            final PairString p32 = new PairString("item7","item8");			
            final PairString p33 = new PairString("item5","item6");
            final PairString p34 = new PairString("item4","item5");
            final List<PairString> list3 = Arrays.asList(p31, p32, p33, p34);

            System.out.println(largestItemAssociation(list));
            System.out.println(largestItemAssociation(list2));
            System.out.println(largestItemAssociation(list3));
    }
}
