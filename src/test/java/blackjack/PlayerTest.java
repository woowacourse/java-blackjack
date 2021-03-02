package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PlayerTest {
    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada"))
            .doesNotThrowAnyException();
    }
}
