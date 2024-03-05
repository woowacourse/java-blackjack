package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("카드의 합을 계산할 수 있다.")
    @Test
    void testCardSummation() {
        Card card1 = new Card(CardShape.HEART, CardNumber.TWO);
        Card card2 = new Card(CardShape.CLUB, CardNumber.TWO);
        Card card3 = new Card(CardShape.DIAMOND, CardNumber.TWO);

        Hand hand = new Hand(List.of(card1, card2, card3));
        int expected = hand.sum();

        assertThat(expected).isEqualTo(6);
    }
}
