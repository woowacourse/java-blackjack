import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ApplicationTest {
    @Test
    void 숫자_카드는_그대로_반환() {
        int result = Application.calculateCardScore("7");
        assertEquals(7, result);
    }

    @Test
    void J는_10을_반환() {
        assertEquals(10, Application.calculateCardScore("J"));
    }

    @Test
    void K는_10을_반환() {
        assertEquals(10, Application.calculateCardScore("K"));
    }
}