package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitTest {

    @DisplayName("예외: 지정된 무늬 값만 유효하다.")
    @Test
    void testCard_StringError() {
        assertThatThrownBy(() -> Suit.of("조커"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 카드 무늬입니다.");
    }
}
