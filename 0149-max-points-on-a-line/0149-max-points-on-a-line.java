import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int maxPoints = 2;

        for (int i = 0; i < n; i++) {
            Map<Double, Integer> slopeCount = new HashMap<>();

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                // Calculate slope (angle) relative to points[i]
                double slope;
                if (dx == 0) {
                    slope = Double.POSITIVE_INFINITY; // Vertical line
                } else if (dy == 0) {
                    slope = 0.0; // Horizontal line (avoid -0.0)
                } else {
                    slope = (double) dy / dx;
                }

                slopeCount.put(slope, slopeCount.getOrDefault(slope, 1) + 1);
                maxPoints = Math.max(maxPoints, slopeCount.get(slope));
            }
        }

        return maxPoints;
    }
}