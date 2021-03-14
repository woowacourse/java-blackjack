package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void createCard() {
        Card card = Card.of(CardNumber.ACE, Symbol.HEART);
        Assertions.assertThat(card).isEqualTo(Card.of(CardNumber.ACE, Symbol.HEART));
    }
}