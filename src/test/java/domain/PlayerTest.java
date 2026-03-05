package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 플레이어가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Player("minseo"));
    }

}
