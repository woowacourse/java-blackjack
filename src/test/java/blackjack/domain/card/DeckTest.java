package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK));

    @Test
    @DisplayName("덱에서 첫번째 카드를 하나 뽑는다")
    void drawTest() {
        final Deck deck = new Deck(new TestNonShuffledDeckGenerator(testCards));

        final Card card = deck.draw();

        assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
    }

    @Test
    @DisplayName("덱이 비어있는데 뽑으려고 하는 경우 IllegalStateException 예외처리")
    void drawEmptyDeckTest() {
        final Deck deck = new Deck(new TestNonShuffledDeckGenerator(Collections.emptyList()));

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(Deck.DECK_EMPTY_EXCEPTION_MESSAGE);
    }
}
