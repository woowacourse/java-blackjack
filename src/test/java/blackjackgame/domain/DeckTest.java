package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @DisplayName("현재 인덱스가 가르키는 카드를 뽑는지 확인한다.")
    @Test
    void Should_ReturnCursorIndexCard_When_PickOneCard() {
        Card spade5Card = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8Card = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Deck deck = new Deck(List.of(spade5Card, clover8Card));

        assertThat(deck.pickOne()).isEqualTo(spade5Card);
        assertThat(deck.pickOne()).isEqualTo(clover8Card);
    }
}
