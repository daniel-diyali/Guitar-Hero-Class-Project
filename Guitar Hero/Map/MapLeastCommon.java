import java.util.*;

public class MapLeastCommon {
/* Write a method leastCommon that accepts a HashMap whose keys
are strings and whose values are integers as a parameter and 
returns the integer value that occurs the fewest times in the map.
For example, if a map name m contains
{Alyssa = 22, Char=25, Dan=25, Jeff=20, Kasey=20, Kim=20, Mogran=25,
Ryan=25, Stef==22}, the call of leastCommon(m) returns 22. 
If there is a tie, return smaller integer value.
If the map is empty, throw an IllegalArgumentException
*/
   public static int leastCommon (Map<String, Integer> map) {
      if (map.isEmpty()) throw new IllegalArgumentException();
      // build reversed map
      Map<Integer, Set<String>> reversedMap = new HashMap<>();
      for (String name : map.keySet()) {
         int age = map.get(name);
         
         if (!reversedMap.containsKey(age)) {
            reversedMap.put (age, new HashSet<>());
         }
         reversedMap.get(age).add(name);
      }
      int minSize = Integer.MAX_VALUE, minAge = -1;
      for (int age : reversedMap.keySet()) {
         int setSize = reversedMap.get(age).size();
         if (setSize < minSize) {
            minSize = setSize;
            minAge = age;
         }
      }
      return minAge;                    
   }
    
    public static void main (String [] args) {
      Map<String, Integer> map = new HashMap <>();
      map.put ("Alyssa", 22);  
      map.put ("Char", 25);
      map.put ("Dan", 25);
      map.put ("Jeff", 20);
      map.put ("Kasey", 20);
      map.put ("Kim", 20);
      map.put ("Mogran", 25);
      map.put ("Ryan", 25); 
      map.put ("Stef", 22);
      
      System.out.println (leastCommon(map));
    }
}
