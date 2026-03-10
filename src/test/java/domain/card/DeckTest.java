package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @DisplayName("덱이 비었을 때 pop하면 예외가 발생한다")
    @Test
    void 빈_덱에서_pop_예외_테스트() {
        final List<Card> testCards = List.of(
                new Card(CardShape.HEART, CardRank.ACE),
                new Card(CardShape.SPADE, CardRank.TWO));
        final Deck deck = new Deck(testCards);

        for (int i = 0; i < testCards.size(); i++) {
            deck.pop();
        }

        assertThatThrownBy(deck::pop)
                .isInstanceOf(IllegalStateException.class);
    }
}
