package blackjack.model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {
    @Test
    @DisplayName("카드는 끗수와 슈트의 조합이다")
    void generateCardTest() {
        // when
        Card card = new Card(Suit.HEART, Denomination.TWO);

        // then
        assertThat(card.getSuit()).isEqualTo(Suit.HEART);
        assertThat(card.getDenomination()).isEqualTo(Denomination.TWO);
    }
}
