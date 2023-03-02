package part3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {

    static Star sun;
    static Star beauty;
    static Star happy;

    static Moon full;
    static Moon half;

    static StarSystem binary;
    static StarSystem empty;

    @BeforeAll
    public static void createAllObjects() {
        sun = new Star(Color.YELLOW, Size.BIG, "Солнце");
        beauty = new Star(Color.RED, Size.PLATE_SIZE, "Красивая");
        happy = new Star(Color.BLUE, Size.SMALL, "Веселая");

        full = new Moon(Color.PINK, Size.BIG, "Полная");
        half = new Moon(Color.GREEN, Size.SMALL, "Растущая");

        binary = new StarSystem("Бинарная", 2);
        empty = new StarSystem("Пустая", 0);
    }

    @Test
    @DisplayName("Should add stars to star systems correct")
    public void testStarSystem() throws Exception {
        Set<Star> expected = new HashSet<>();
        expected.add(sun);

        assertEquals("Звезда Солнце теперь есть в звездной системе Бинарная\r\n", tapSystemOut(() -> binary.addStar(sun)));
        assertEquals(1, binary.getSize());
        assertIterableEquals(expected, binary.getStars());

        expected.add(beauty);
        assertEquals("Звезда Красивая теперь есть в звездной системе Бинарная\r\n", tapSystemOut(() -> binary.addStar(beauty)));
        assertEquals(2, binary.getSize());
        assertIterableEquals(expected, binary.getStars());

        assertEquals("Звезда Солнце уже есть в звездной системе Бинарная\r\n", tapSystemOut(() -> binary.addStar(sun)));
        assertEquals(2, binary.getSize());
        assertIterableEquals(expected, binary.getStars());

        assertEquals("Звездная система Бинарная не может вместить звезду Веселая\r\n", tapSystemOut(() -> binary.addStar(happy)));
        assertEquals(2, binary.getSize());
        assertIterableEquals(expected, binary.getStars());

        assertEquals("Звездная система Пустая не может вместить звезду Солнце\r\n", tapSystemOut(() -> empty.addStar(sun)));
        assertEquals(0, empty.getSize());
        expected.clear();
        assertIterableEquals(expected, empty.getStars());
    }

    @Test
    @DisplayName("Should not create star system with negative number of stars")
    public void testInvalidStarSystem() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> new StarSystem("Отрицательная", -1),
                "Expected graph.addVertex(\"0\") to throw exception, but it didn't"
        );

        assertTrue(thrown.getMessage().contentEquals("Звездная система не может иметь отричательный размер"));
    }

    @Test
    @DisplayName("Should add pair only when moon is not sleeping and not the same")
    public void testAddingPair() {
        full.sleep(3);
        assertFalse(sun.addPairMoon(full));

        full.wake();
        assertTrue(sun.addPairMoon(full));

        assertFalse(sun.addPairMoon(full));

        half.wake();
        assertTrue(sun.addPairMoon(half));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Should not add pair if null moon")
    public void testNullPair(Moon moon) {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> beauty.addPairMoon(moon),
                "Expected beauty.addPairMoon(moon) to throw exception, but it didn't"
        );

        assertTrue(thrown.getMessage().contentEquals("Луна не может быть null!"));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 30, Integer.MAX_VALUE})
    @DisplayName("Should change energy and state if valid time")
    public void testSleeping(int time) {
        int oldEnergy = full.getEnergy();
        full.sleep(time);
        assertEquals(oldEnergy + time * 2, full.getEnergy());
        assertTrue(full.isSleeping());
        assertFalse(full.isCrawling());
        assertFalse(full.isShining());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @DisplayName("Should not change energy and state if invalid time")
    public void testSleepingInvalid(int time) {
        full.crawl();
        int oldEnergy = full.getEnergy();
        full.sleep(time);
        assertEquals(oldEnergy, full.getEnergy());
        assertFalse(full.isSleeping());
        assertTrue(full.isCrawling());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 30, Integer.MAX_VALUE})
    @DisplayName("Shouldn't change state if moon is sleeping")
    public void testCantDoAnythingWhileSleeping(int time) {
        full.sleep(time);
        assertTrue(full.isSleeping());
        assertFalse(full.isCrawling());
        assertFalse(full.isShining());

        full.shine();
        assertTrue(full.isSleeping());
        assertFalse(full.isCrawling());
        assertFalse(full.isShining());

        full.crawl();
        assertTrue(full.isSleeping());
        assertFalse(full.isCrawling());
        assertFalse(full.isShining());

        full.wake();
        assertFalse(full.isSleeping());
        assertFalse(full.isCrawling());
        assertFalse(full.isShining());

        full.crawl();
        assertFalse(full.isSleeping());
        assertTrue(full.isCrawling());
        assertFalse(full.isShining());

        full.shine();
        assertFalse(full.isSleeping());
        assertTrue(full.isCrawling());
        assertTrue(full.isShining());
    }

    @Test
    @DisplayName("Shouldn't change state if moon has too low energy")
    public void testCantDoAnythingWhileLowEnergy() {
        sun.addPairMoon(full);
        beauty.addPairMoon(full);
        assertTrue(full.getEnergy() < 0);
        full.shine();
        full.crawl();
        assertFalse(full.isCrawling());
        assertFalse(full.isShining());
    }

    @Test
    @DisplayName("Should print correct shining")
    public void testSunShining() throws Exception {
        String sunShine = tapSystemOut(() -> sun.shine());
        String beautyShine = tapSystemOut(() -> beauty.shine());
        String happyShine = tapSystemOut(() -> happy.shine());

        assertEquals("В первый момент показалось, что ничего не произошло, затем звезда Солнце засветилась на краю огромного экрана.\r\n", sunShine);
        assertEquals("В первый момент показалось, что ничего не произошло, затем звезда Красивая засветилась на краю огромного экрана.\r\n", beautyShine);
        assertEquals("В первый момент показалось, что ничего не произошло, затем звезда Веселая засветилась на краю огромного экрана.\r\n", happyShine);

        String fullShine = tapSystemOut(() -> full.shine());
        String halfShine = tapSystemOut(() -> half.shine());

        assertEquals("Луна Полная издала розовый свет, переходящий в черноту -- ночная сторона планеты\r\n", fullShine);
        assertEquals("Луна Растущая издала зеленый свет, переходящий в черноту -- ночная сторона планеты\r\n", halfShine);
    }

    @Test
    @DisplayName("Should print correct crawling")
    public void testSunCrawling() throws Exception {
        String sunCrawl = tapSystemOut(() -> sun.crawl());
        String beautyCrawl = tapSystemOut(() -> beauty.crawl());
        String happyCrawl = tapSystemOut(() -> happy.crawl());

        assertEquals("Большая, имеющая желтый цвет, звезда Солнце ползла по экрану\r\n", sunCrawl);
        assertEquals("Размером с тарелку, имеющая красный цвет, звезда Красивая ползла по экрану\r\n", beautyCrawl);
        assertEquals("Маленькая, имеющая голубой цвет, звезда Веселая ползла по экрану\r\n", happyCrawl);

        String fullCrawl = tapSystemOut(() -> full.crawl());
        String halfCrawl = tapSystemOut(() -> half.crawl());

        assertEquals("Большая луна Полная возникла в углу картинки\r\n", fullCrawl);
        assertEquals("Маленькая луна Растущая возникла в углу картинки\r\n", halfCrawl);
    }

}
