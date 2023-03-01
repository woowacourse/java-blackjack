package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DeckTest {
    @Test
    @DisplayName("덱이 비어있을 때 카드를 뽑으면 예외가 발생한다.")
    void throwExceptionWhenDeckIsEmpty() {
        final int cardNumberSize = CardNumber.values().length;
        final int cardShapeSize = CardShape.values().length;
        final int deckSize = cardNumberSize * cardShapeSize;
        final Deck deck = new Deck();
        for (int count = 0; count < deckSize; count++) {
            deck.draw();
        }
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("카드를 뽑는다.")
    void drawCard() {
        assertThat(new Deck().draw())
                .isNotNull();
    }
}