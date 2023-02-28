package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayerTest() {
        Assertions.assertDoesNotThrow(() -> new Player("pobi"));
    }
}
