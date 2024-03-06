package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드는 끗수와 슈트의 조합이다")
    void generateCardTest() {
        // when
        Card card = new Card((number) -> 0);

        // then
        assertThat(card.getDenomination()).isEqualTo(Denomination.TWO);
        assertThat(card.getSuit()).isEqualTo(Suit.HEART);
    }
}
