package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    void ofTest() {
        Card card = Card.of(Suit.CLUB, Denomination.TEN);
        assertThat(card).isSameAs(Card.of(Suit.CLUB, Denomination.TEN));
    }
}
