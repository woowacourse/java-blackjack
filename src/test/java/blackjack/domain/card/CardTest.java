package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("카드가 에이스인지 확인한다.")
    @Test
    void isAce() {
        // given
        Card card = new Card(CardNumber.ACE, CardShape.CLOVER);

        // when
        boolean isAce = card.isAce();

        // then
        assertThat(isAce).isTrue();
    }

    @DisplayName("카드가 에이스가 아닌지 확인한다.")
    @Test
    void isNotAce() {
        // given
        Card card = new Card(CardNumber.EIGHT, CardShape.CLOVER);

        // when
        boolean isAce = card.isAce();

        // then
        assertThat(isAce).isFalse();
    }
}
