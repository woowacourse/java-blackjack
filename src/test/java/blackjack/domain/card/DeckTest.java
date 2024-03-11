package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드 덱에서 카드를 한 장 뽑는다.")
    @Test
    void drawCard() {
        Deck deck = new Deck(new TestDeckFactory());

        Card card = deck.pop();

        assertThat(card).isEqualTo(new Card(Number.KING, Suit.DIAMOND));
    }

    @DisplayName("카드 덱에 카드가 다 떨어졌을 때 카드를 뽑으면 예외가 발생한다.")
    @Test
    void occurExceptionWhenDeckIsEmpty() {
        Deck deck = new Deck(new TestDeckFactory());

        int size = deck.getDeck().size();
        for (int i=0; i<size; i++) {
            deck.pop();
        }

        assertThatThrownBy(() -> deck.pop())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(Deck.ERROR_DECK_IS_EMPTY);
    }
}
