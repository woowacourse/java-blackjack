package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.game.Deck;
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
        assertThat(card.getShape()).isEqualTo(CardShape.CLOVER);
    }
}
