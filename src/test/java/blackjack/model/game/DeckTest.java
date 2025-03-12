package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import java.util.List;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 카드로_덱을_만든다() {
        // given
        Deck deck = new Deck(List.of(
                new Card(CardShape.CLOVER, CardType.ACE),
                new Card(CardShape.CLOVER, CardType.ACE),
                new Card(CardShape.CLOVER, CardType.ACE),
                new Card(CardShape.CLOVER, CardType.ACE)
        ));

        // when & then
        assertThat(deck.getCardCount()).isEqualTo(4);
    }

    @Test
    void 카드를_하나_드로우한다() {
        Deck deck = new Deck(List.of(
                new Card(CardShape.DIAMOND, CardType.ACE),
                new Card(CardShape.CLOVER, CardType.ACE),
                new Card(CardShape.CLOVER, CardType.ACE),
                new Card(CardShape.CLOVER, CardType.ACE)
        ));

        Card card = deck.drawCard();
        assertThat(card.getShape()).isEqualTo(CardShape.DIAMOND);
    }

    @Test
    void 카드가_다_떨어졌을_경우() {
        Deck deck = new Deck(List.of(
                new Card(CardShape.DIAMOND, CardType.ACE)
        ));
        deck.drawCard();

        assertThatThrownBy(() -> deck.drawCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 있는 카드를 모두 사용했습니다.");
    }
}
