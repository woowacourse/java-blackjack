package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Hands;
import org.junit.jupiter.api.Test;

class HandsTest {
    @Test
    void calculateSumTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);

        Hands hands = new Hands();

        hands.addNewCard(card1);
        hands.addNewCard(card2);

        // when
        int result = hands.calculateSum();

        // then
        assertThat(result).isEqualTo(18);
    }
}