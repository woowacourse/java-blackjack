package blackjack_statepattern;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("원하는 카드를 받아올 수 있다.")
    void of() {
        Card card = Card.of(Suit.SPADES, Denomination.ACE);
        assertThat(card).isSameAs(Card.of(Suit.SPADES, Denomination.ACE));
    }
}
