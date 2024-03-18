package blackjack.model.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    @DisplayName("카드는 끗수와 슈트의 조합이다")
    void generateCardTest() {
        // when
        Card card = new Card(Suit.HEART, Denomination.TWO);

        // then
        assertThat(card.suit()).isEqualTo(Suit.HEART);
        assertThat(card.denomination()).isEqualTo(Denomination.TWO);
    }
}
