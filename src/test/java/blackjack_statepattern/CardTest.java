package blackjack_statepattern;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void of() {
        Card card = Card.of(Suit.SPADES, Denomination.ACE);
        assertThat(card).isSameAs(Card.of(Suit.SPADES, Denomination.ACE));
    }
}
