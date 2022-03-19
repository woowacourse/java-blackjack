package blackjack_statepattern;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void of() {
        Card card = Card.of(Suit.SPADES, Denomination.ACE);
        Assertions.assertThat(card).isSameAs(Card.of(Suit.SPADES, Denomination.ACE));
    }
}
