package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @DisplayName("손에 들고 있는 카드의 합계를 반환한다.")
    @Test
    void calculateSum() {
        Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
        Card card2 = new Card(Letter.K, Mark.SPADE);
        List<Card> cards = List.of(card1, card2);

        Hand hand = new Hand(cards);

        assertThat(hand.calculateSum())
                .isEqualTo(card1.getValue() + card2.getValue());
    }
}
