package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTypeTest {

    @Test
    @DisplayName("카드는 4가지 문양을 가질 수 있다")
    void cardTypeTest() {
        // when
        CardType[] values = CardType.values();

        // then
        assertThat(values).hasSize(4);
    }
}
