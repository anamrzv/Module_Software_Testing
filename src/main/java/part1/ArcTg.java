package part1;


public class ArcTg {
    public static double getPowerSeriesConvergent(double x, double eps) {
        if (eps >= 1 || eps <= 0) {
            throw new UnsupportedOperationException("Функция действительна только для 0 < эпсилон < 1");
        }
        double sum = 0;
        if (x == 0) return sum;
        if (Math.abs(x) <= 1) {
            double previous = 0;
            for (int n = 1; n == 1 || Math.abs(sum - previous) > eps; n++) {
                previous = sum;
                sum += Math.pow(-1, n - 1) * (Math.pow(x, 2 * n - 1)) / (2 * n - 1);
            }
            return sum;
        } else throw new UnsupportedOperationException("Функция действительна только для -1 <= x <= 1");
    }
}
