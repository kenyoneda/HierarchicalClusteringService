package hierarchicalclustering.org.geworkbench.components.hierarchicalclustering.computation;



public class EuclideanDistance implements Distance {
    // Singleton
    public final static EuclideanDistance instance = new EuclideanDistance();

    private EuclideanDistance() {
    }

    public static double distance(double[] a, double[] b) {
        double d = 0;
        int n = Math.min(a.length, b.length);
        for (int i = 0; i < n; ++i) {
            double t = a[i] - b[i];
            // The following is a very efficient replacement for !Double.isNaN(t).
            if (t == t) {
                d += t * t;
            }
        }
        return Math.sqrt(d);
    }

    public double compute(double[] a, double[] b) {
        return distance(a, b);
    }
}
