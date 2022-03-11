package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드는 value object이다.")
    void create() {
        Card card = Card.valueOf(Denomination.ACE, Suit.CLOVER);
        Assertions.assertThat(card).isEqualTo(Card.valueOf(Denomination.ACE, Suit.CLOVER));
    }
}
