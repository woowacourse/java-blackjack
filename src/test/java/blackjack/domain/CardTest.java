package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    void ofTest() {
        Card card = Card.of(Suit.CLUB, Denomination.TEN);
        assertThat(card).isSameAs(Card.of(Suit.CLUB, Denomination.TEN));
    }
}
