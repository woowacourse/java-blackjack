package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("문자열을 받아서 Player를 생성한다.")
    @Test
    void createPlayerWhenGivenString() {
        // given
        String input = "test";

        // when & then
        assertDoesNotThrow(() -> new Player(input));
    }
}
