package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    @Test
    @DisplayName("카드 리스트 출력")
    void getCardStatus() {
        Hand hand = new Hand();
        hand.add(new Card(CardSymbol.ACE, CardType.SPADE));
        hand.add(new Card(CardSymbol.FIVE, CardType.HEART));

        assertThat(hand.getCardStatus()).isEqualTo("A스페이드, 5하트");
    }
}