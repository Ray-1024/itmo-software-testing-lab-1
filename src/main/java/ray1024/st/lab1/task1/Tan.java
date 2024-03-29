package ray1024.st.lab1.task1;

public class Tan {

    private static double from0to2pi(double x) {
        double pi2 = Math.PI * 2.0;
        if (x > pi2) x %= pi2;
        else if (x < 0) x = pi2 - ((-x) % pi2);
        return x;
    }

    private static double powerSeries(double x, double initial, double pow, int index) {
        final double x2 = x * x;

        double f0;
        double f1 = initial;
        double delta;
        double fact = 1.0;
        double sign = 1.0;

        do {
            f0 = f1;
            pow *= x2;
            fact /= index;
            fact /= index + 1;
            index += 2;
            sign = -sign;
            delta = sign * pow * fact;
            f1 = f0 + delta;
        } while (Math.abs(f0 - f1) > Double.MIN_VALUE || Math.abs(delta) > Double.MIN_VALUE);
        return (f0 + f1) / 2.0;
    }

    private static double sin(double x) {
        return powerSeries(x, x, x, 2);
    }

    private static double cos(double x) {
        return powerSeries(x, 1.0, 1.0, 1);
    }

    public static double calculate(double x) {
        x = from0to2pi(x);
        double basePrecision = 1e-8;
        if (Math.abs(x - Math.PI / 2.0) < basePrecision || Math.abs(x - 3.0 * Math.PI / 2.0) < basePrecision)
            throw new IllegalArgumentException("Point of discontinuity");
        return sin(x) / cos(x);
    }
}
