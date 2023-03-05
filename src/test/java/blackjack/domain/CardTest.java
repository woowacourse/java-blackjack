package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class CardTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        assertThatNoException()
                .isThrownBy(() -> new Card(Suit.SPADE, CardNumber.TWO));
    }
}
