package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("참여자 이름은 한 글자 이상이 아니면 예외가 발생한다")
    void validatePlayerNameLengthTest() {
        // when & then
        assertThatThrownBy(() -> new Player("", () -> 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
