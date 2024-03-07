package blackjack.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("자신이 가지고 있는 카드 중 한 장을 플레이어에게 제공할 수 있다.")
    void draw() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card(Shape.SPADE, Value.ACE),
                new Card(Shape.CLOVER, Value.FOUR),
                new Card(Shape.HEART, Value.KING)
        ));
        Deck deck = Deck.from(cards);

        Card expected = new Card(Shape.SPADE, Value.ACE);
        assertThat(deck.draw()).isEqualTo(expected);
    }

    @Test
    @DisplayName("중복되는 카드가 있다면 예외가 발생한다.")
    void duplicatedCardsTest() {
        List<Card> cards = List.of(
                new Card(Shape.DIAMOND, Value.FOUR),
                new Card(Shape.DIAMOND, Value.FOUR)
        );

        assertThatThrownBy(() -> Deck.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되는 카드가 있습니다.");
    }
}
