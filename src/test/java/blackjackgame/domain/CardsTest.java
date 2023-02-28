package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @DisplayName("현재 인덱스가 가르키는 카드를 뽑는지 확인한다.")
    @Test
    void Should_ReturnCursorIndexCard_When_PickOneCard() {
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        List<Card> sampleCards = List.of(card1, card2);
        Cards cards = new Cards(sampleCards);

        assertThat(cards.pickOne()).isEqualTo(card1);
        assertThat(cards.pickOne()).isEqualTo(card2);
    }
}
