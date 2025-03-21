package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("예외: 지정된 랭크 값만 유효하다.")
    @Test
    void testCard_StringError() {
        assertThatThrownBy(() -> Rank.of("X"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 카드 랭크입니다.");
    }
}
