import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void 해당_규칙에_포함되지_않는_문자_A_입력_시_에러_발생() {
        assertThrows(NumberFormatException.class, () -> {
            Application.calculateCardScore("A");
        });
    }

    @Test
    void 블랙잭_규칙에_맞지_않는_문자_입력_시_에러_발생() {
        assertThrows(NumberFormatException.class, () -> {
            Application.calculateCardScore("Z");
        });
    }
}