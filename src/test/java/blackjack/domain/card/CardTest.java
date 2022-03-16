package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("에이스인지 판단")
    void isAceTest() {
        Card card = new Card(Denomination.ACE, Suit.CLUBS);

        assertThat(card.isAce()).isTrue();
    }
}
