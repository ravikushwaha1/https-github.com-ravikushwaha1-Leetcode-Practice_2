import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
    // Max-heap stores the smaller half of numbers
    private PriorityQueue<Integer> small;
    // Min-heap stores the larger half of numbers
    private PriorityQueue<Integer> large;

    public MedianFinder() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Add to max-heap first
        small.add(num);

        // Ensure every element in small is <= every element in large
        if (!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
            large.add(small.poll());
        }

        // Handle size imbalance (small can have at most 1 extra element)
        if (small.size() > large.size() + 1) {
            large.add(small.poll());
        } else if (large.size() > small.size()) {
            small.add(large.poll());
        }
    }

    public double findMedian() {
        if (small.size() > large.size()) {
            return small.peek();
        }
        return (small.peek() + large.peek()) / 2.0;
    }
}