package allClasses;

import java.util.Comparator;

public class programmerComparator implements Comparator<String> {

   public  int compare(String a, String b) {
    return a.compareTo(b);
    }

    public static Comparator<? super String> compare() {
        return compare();
    }
}