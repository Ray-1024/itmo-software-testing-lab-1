package ray1024.st.lab1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TanTest {

    private final double precision = 1e-6;

    public static DoubleStream piDots() {
        return DoubleStream.iterate(-10.0 * Math.PI, d -> d + Math.PI).limit(21);
    }

    @ParameterizedTest(name = "tan({0})")
    @MethodSource("piDots")
    void tanPiDots(double x) {
        assertEquals(Math.tan(x), Tan.calculate(x), precision);
    }


    public static DoubleStream nearPi2Dots() {
        return DoubleStream.concat(DoubleStream.iterate(Math.PI / 2.0 - 10.0 * Math.PI - 1e-4, d -> d + Math.PI).limit(21), DoubleStream.iterate(Math.PI / 2.0 - 10.0 * Math.PI + 1e-4, d -> d + Math.PI).limit(21));
    }

    @ParameterizedTest(name = "tan({0})")
    @MethodSource("nearPi2Dots")
    void tanNearPi2(double x) {
        assertEquals(Math.tan(x), Tan.calculate(x), precision);
    }


    public static DoubleStream pi2Dots() {
        return DoubleStream.iterate(Math.PI / 2.0 - 10.0 * Math.PI, d -> d + Math.PI).limit(21);
    }

    @ParameterizedTest(name = "tan({0})")
    @MethodSource("pi2Dots")
    void tanPi2(double x) {
        assertThrows(IllegalArgumentException.class, () -> Tan.calculate(x));
    }


    public static DoubleStream randomDots() {
        return DoubleStream.iterate(0.0, d -> d + 0.1).limit(100);
    }

    @ParameterizedTest(name = "tan({0})")
    @MethodSource("randomDots")
    void tanRandom(double x) {
        assertEquals(Math.tan(x), Tan.calculate(x), precision);
    }
}