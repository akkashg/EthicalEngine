import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class SortHashmap {
	static DecimalFormat f = new DecimalFormat("0.00");
	public static void sortbykey(Map<String,Float> map)
    {
        // TreeMap to store values of HashMap
        TreeMap<String, Float> sorted = new TreeMap<>();
        
        // Copy all data from hashMap into TreeMap
        sorted.putAll(map);
        Map<String, Float> finalsorted =valueSort(sorted);
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<String, Float> entry : finalsorted.entrySet())
            System.out.println(entry.getKey() +": "+f.format(entry.getValue()));       
    }
	public static <K, V extends Comparable<V> > Map<K, V>valueSort(final Map<K, V> map)
	    {
	        // Static Method with return type Map and
	        // extending comparator class which compares values
	        // associated with two keys
	        Comparator<K> valueComparator = new Comparator<K>()
	        {
	              
	            public int compare(K k1, K k2)
	            {
	  
	                int comp = map.get(k2).compareTo(map.get(k1));
	  
	                if (comp == 0)
	                     return 1;
	  
	                else
	                     return comp;
	            }
	        };
	  
	        // SortedMap created using the comparator
	        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);
	  
	        sorted.putAll(map);
	  
	        return sorted;
	    }
}
