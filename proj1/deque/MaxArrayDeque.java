package deque;

import javax.net.ssl.CertPathTrustManagerParameters;
import java.util.Comparator;
import java.util.Map;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> defaultComparator;

    public MaxArrayDeque(Comparator<T> c) {
       super();
       defaultComparator = c;
    }


    public T max() {
        return max(defaultComparator);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T maxItem = this.get(0); // 假设你有 get(int index) 方法
        for (int i = 1; i < this.size(); i++) {
            T current = this.get(i);
            if (c.compare(current, maxItem) > 0) {
                maxItem = current;
            }
        }
        return maxItem;
    }

}
