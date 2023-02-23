package part1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArcTgTest {

    @Test
    @DisplayName("Should calculate power series for not convergent function via general function")
    public void testNotConvergentFunc() {
        double actualPos = ArcTg.getPowerSeriesAny(32.0, 5);
        double expectedPos = 32 - Math.pow(32, 3) / 3 + Math.pow(32, 5) / 5 - Math.pow(32, 7) / 7 + Math.pow(32, 9) / 9;
        assertEquals(expectedPos, actualPos, 0.001);

        double actualNeg = ArcTg.getPowerSeriesAny(-100.1, 3);
        double expectedNeg = -100.1 - Math.pow(-100.1, 3) / 3 + Math.pow(-100.1, 5) / 5;
        assertEquals(expectedNeg, actualNeg, 0.001);

        double actualPosLow = ArcTg.getPowerSeriesAny(1.00001, 5);
        double expectedPosLow = 1.00001 - Math.pow(1.00001, 3) / 3 + Math.pow(1.00001, 5) / 5 - Math.pow(1.00001, 7) / 7 + Math.pow(1.00001, 9) / 9;
        assertEquals(expectedPosLow, actualPosLow, 0.001);

        double actualNegLow = ArcTg.getPowerSeriesAny(-1.001, 3);
        double expectedNegLow = -1.001 - Math.pow(-1.001, 3) / 3 + Math.pow(-1.001, 5) / 5;
        assertEquals(expectedNegLow, actualNegLow, 0.001);
    }

    @Test
    @DisplayName("Should calculate power series for convergent function via general function")
    public void testConvergentFunc() {
        double actualPos = ArcTg.getPowerSeriesAny(1, 50);
        double actualNeg = ArcTg.getPowerSeriesAny(-1.0, 50);
        assertEquals(Math.atan(1), actualPos, 0.01);
        assertEquals(Math.atan(-1), actualNeg, 0.01);

        double actualSomeBigNeg = ArcTg.getPowerSeriesAny(-0.9999, 50);
        double actualSomeBigPos = ArcTg.getPowerSeriesAny(0.99, 50);
        assertEquals(Math.atan(-0.9999), actualSomeBigNeg, 0.01);
        assertEquals(Math.atan(0.99), actualSomeBigPos, 0.01);

        double actualSomeNeg = ArcTg.getPowerSeriesAny(-0.5522, 50);
        double actualSomePos = ArcTg.getPowerSeriesAny(0.3672, 50);
        assertEquals(Math.atan(-0.5522), actualSomeNeg, 0.01);
        assertEquals(Math.atan(0.3672), actualSomePos, 0.01);

        double actualSomeLowNeg = ArcTg.getPowerSeriesAny(-0.0001, 50);
        double actualSomeLowPos = ArcTg.getPowerSeriesAny(0.01, 50);
        assertEquals(Math.atan(-0.0001), actualSomeLowNeg, 0.01);
        assertEquals(Math.atan(0.01), actualSomeLowPos, 0.01);

        double actualZero = ArcTg.getPowerSeriesAny(0, 3);
        assertEquals(Math.atan(0), actualZero, 0.0001);
    }

    @Test
    @DisplayName("Should calculate power series for convergent function via special function")
    public void testConvergentFuncSpecial() {
        double actualPos = ArcTg.getPowerSeriesConvergent(1, 0.001);
        double actualNeg = ArcTg.getPowerSeriesConvergent(-1.0, 0.001);
        assertEquals(Math.atan(1), actualPos, 0.001);
        assertEquals(Math.atan(-1), actualNeg, 0.001);

        double actualSomeBigNeg = ArcTg.getPowerSeriesConvergent(-0.9999, 0.0003);
        double actualSomeBigPos = ArcTg.getPowerSeriesConvergent(0.99, 0.01);
        assertEquals(Math.atan(-0.9999), actualSomeBigNeg, 0.0003);
        assertEquals(Math.atan(0.99), actualSomeBigPos, 0.01);

        double actualSomeNeg = ArcTg.getPowerSeriesConvergent(-0.5522, 0.1);
        double actualSomePos = ArcTg.getPowerSeriesConvergent(0.3672, 0.005);
        assertEquals(Math.atan(-0.5522), actualSomeNeg, 0.1);
        assertEquals(Math.atan(0.3672), actualSomePos, 0.005);

        double actualSomeLowNeg = ArcTg.getPowerSeriesConvergent(-0.0001, 0.0002);
        double actualSomeLowPos = ArcTg.getPowerSeriesConvergent(0.01, 0.001);
        assertEquals(Math.atan(-0.0001), actualSomeLowNeg, 0.0002);
        assertEquals(Math.atan(0.01), actualSomeLowPos, 0.001);

        double actualZero = ArcTg.getPowerSeriesConvergent(0, 0.001);
        assertEquals(Math.atan(0), actualZero, 0.001);
    }

    @Test
    @DisplayName("Should calculate power series with accuracy equals or more than epsilon")
    public void testEps() {
        double actualSomeNeg = ArcTg.getPowerSeriesConvergent(-0.789, 0.1);
        assertNotEquals(Math.atan(-0.789), actualSomeNeg, 0.01);
        assertEquals(Math.atan(-0.789), actualSomeNeg, 0.1);
        assertEquals(Math.atan(-0.789), actualSomeNeg, 0.15);
        assertEquals(Math.atan(-0.789), actualSomeNeg, 0.9);

        double actualSomePos = ArcTg.getPowerSeriesConvergent(0.2222, 0.0007);
        assertNotEquals(Math.atan(0.2222), actualSomePos, 0.000001);
        assertEquals(Math.atan(0.2222), actualSomePos, 0.00007);
        assertEquals(Math.atan(0.2222), actualSomePos, 0.0001);
        assertEquals(Math.atan(0.2222), actualSomePos, 0.001);
        assertEquals(Math.atan(0.2222), actualSomePos, 0.01);
        assertEquals(Math.atan(0.2222), actualSomePos, 0.1);
        assertEquals(Math.atan(0.2222), actualSomePos, 0.9);
    }

    @Test
    @DisplayName("Should throw exception when number of steps is less then 1 and doesn't throw otherwise")
    public void testThrowingStepException() {
        UnsupportedOperationException thrownMinus = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesAny(2842, -199238),
                "Expected getPowerSeriesAny(2842, -199238) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownMinusOne = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesAny(-1983.324, -1),
                "Expected getPowerSeriesAny(-1983.324, -1) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownZero = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesAny(-0.5, 0),
                "Expected getPowerSeriesAny(-0.5, 0) to throw exception, but it didn't"
        );

        assertDoesNotThrow(
                () -> ArcTg.getPowerSeriesAny(0.76, 1),
                "Expected getPowerSeriesAny(0.76, 1) not to throw exception, but it did"
        );

        assertDoesNotThrow(
                () -> ArcTg.getPowerSeriesAny(23.03, 34),
                "Expected getPowerSeriesAny(23.03, Integer.MAX_VALUE) not to throw exception, but it did"
        );

        assertTrue(thrownMinus.getMessage().contentEquals("Функция действительна только для числа шагов от 1"));
        assertTrue(thrownMinusOne.getMessage().contentEquals("Функция действительна только для числа шагов от 1"));
        assertTrue(thrownZero.getMessage().contentEquals("Функция действительна только для числа шагов от 1"));
    }

    @Test
    @DisplayName("Should throw exception when epsilon more than 1 or less than 0 doesn't throw otherwise")
    public void testThrowingEpsException() {
        UnsupportedOperationException thrownOne = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(0.6, 1),
                "Expected getPowerSeriesConvergent(2842, -199238) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownGreaterOne = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(-0.287, 21),
                "Expected getPowerSeriesConvergent(-1983.324, -1) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownZero = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(-0.9, 0),
                "Expected getPowerSeriesConvergent(-0.9, 0) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownLessZero = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(0.98, -0.9237),
                "Expected getPowerSeriesConvergent(0.98, -0.9237) to throw exception, but it didn't"
        );

        assertDoesNotThrow(
                () -> ArcTg.getPowerSeriesConvergent(0.76, 0.999),
                "Expected getPowerSeriesConvergent(0.76, 1) not to throw exception, but it did"
        );

        assertTrue(thrownOne.getMessage().contentEquals("Функция действительна только для 0 < эпсилон < 1"));
        assertTrue(thrownGreaterOne.getMessage().contentEquals("Функция действительна только для 0 < эпсилон < 1"));
        assertTrue(thrownZero.getMessage().contentEquals("Функция действительна только для 0 < эпсилон < 1"));
        assertTrue(thrownLessZero.getMessage().contentEquals("Функция действительна только для 0 < эпсилон < 1"));
    }

    @Test
    @DisplayName("Should throw exception when abs(x) > 1 and doesn't throw otherwise")
    public void testThrowingXException() {
        UnsupportedOperationException thrownGreaterOne = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(1.00001, 0.001),
                "Expected getPowerSeriesConvergent(1.00001, 0.001)) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownLessOne = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(-1.0000001, 0.001),
                "Expected getPowerSeriesConvergent(-1.0000001, 0.001) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownGreaterBigOne = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(287.23, 0.001),
                "Expected getPowerSeriesConvergent(287.23, 0.001)) to throw exception, but it didn't"
        );

        UnsupportedOperationException thrownLessBigOne = assertThrows(
                UnsupportedOperationException.class,
                () -> ArcTg.getPowerSeriesConvergent(-9999.9, 0.001),
                "Expected getPowerSeriesConvergent(-9999.9, 0.001) to throw exception, but it didn't"
        );

        assertDoesNotThrow(
                () -> ArcTg.getPowerSeriesConvergent(0.76, 0.999),
                "Expected getPowerSeriesConvergent(0.76, 1) not to throw exception, but it did"
        );

        assertTrue(thrownLessOne.getMessage().contentEquals("Функция действительна только для -1 <= x <= 1"));
        assertTrue(thrownGreaterOne.getMessage().contentEquals("Функция действительна только для -1 <= x <= 1"));
        assertTrue(thrownGreaterBigOne.getMessage().contentEquals("Функция действительна только для -1 <= x <= 1"));
        assertTrue(thrownLessBigOne.getMessage().contentEquals("Функция действительна только для -1 <= x <= 1"));
    }


}
