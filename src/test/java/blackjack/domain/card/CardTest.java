package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void createCard() {
        Card card = Card.from(CardNumber.ACE, Symbol.HEART);
        Assertions.assertThat(card).isEqualTo(Card.from(CardNumber.ACE, Symbol.HEART));
    }
}