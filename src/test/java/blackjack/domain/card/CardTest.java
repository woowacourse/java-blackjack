package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> Card.of(CardRank.EIGHT, CardShape.CLOVER))
                .doesNotThrowAnyException();
    }
}
