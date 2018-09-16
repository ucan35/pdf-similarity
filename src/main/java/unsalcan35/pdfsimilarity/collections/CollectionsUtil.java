package unsalcan35.pdfsimilarity.collections;

import java.util.*;

/**
 * +
 * Bazı veri yapıları için çeşitli metotlar bulunduran yardımcı sınıf
 * Singleton yapısında
 */
public class CollectionsUtil<K extends Comparable, V extends Comparable> {
    private static final CollectionsUtil instance = new CollectionsUtil();

    private CollectionsUtil() {
    }

    public static CollectionsUtil getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public LinkedHashMap<K, V> sortMapByValue(Map<K, V> map, boolean descending) {
        List<K> keys = new ArrayList<>(map.keySet());
        List<V> vals = new ArrayList<>(map.values());
        Collections.sort(keys);
        Collections.sort(vals);
        if (descending) {
            Collections.reverse(keys);
            Collections.reverse(vals);
        }
        LinkedHashMap<K, V> sortedMap = new LinkedHashMap<>();
        for (V val : vals) {
            Iterator<K> keyIt = keys.iterator();
            while (keyIt.hasNext()) {
                K key = keyIt.next();
                V comp1 = map.get(key);
                if (comp1.compareTo(val) == 0) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    /**
     * +
     * String.subString metotuna benzer
     * Index'ler kullandığı için girdi harita LinkedHashMap olmalı(Diğer harita türevlerinde veri sırasız tutulur)
     *
     * @param map
     * @param fromIndex Kaçıncı elemandan itibaren
     * @param toIndex   Kaçıncı elemana kadar
     * @return Belirtilen değerlere göre ilk haritanın alt kümesini döndürür
     */
    public Map<K, V> subMap(LinkedHashMap<K, V> map, int fromIndex, int toIndex) {
        List<K> keys = new ArrayList<>(map.keySet());
        if (fromIndex < 0 || toIndex > keys.size()) {
            throw new IndexOutOfBoundsException();
        }
        Map<K, V> subMap = new LinkedHashMap<>();
        for (int i = fromIndex; i < toIndex; i++) {
            K key = keys.get(i);
            subMap.put(key, map.get(key));
        }
        return subMap;
    }

    @SuppressWarnings("unchecked")
    public List<V> sortList(List<V> list, boolean descending) {
        Collections.sort(list);
        if (descending) {
            Collections.reverse(list);
        }
        return list;
    }
}
