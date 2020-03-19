package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HandTest {

    @Test
    @DisplayName("hand에 카드 추가")
    void add() {
        Hand hand = new Hand();
        assertThatThrownBy(() -> hand.add(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 추가할 수 없습니다.");
    }

    @Test
    @DisplayName("카드 리스트 출력")
    void getCardStatus() {
        Hand hand = new Hand();
        hand.add(new Card(CardSymbol.ACE, CardType.SPADE));
        hand.add(new Card(CardSymbol.FIVE, CardType.HEART));

        assertThat(hand.getCardStatus().size()).isEqualTo(2);
    }
}