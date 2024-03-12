package domain.card;

import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    private final int DECK_COUNT = 6;

    @Test
    @DisplayName("모든 카드를 뽑았을 때 서로 다른 카드의 개수는 52개다.")
    void draw() {
        final Cards deck = Cards.makeDecks();
        final Set<Card> cards = new HashSet<>();

        for (int i = 0; i < 52 * DECK_COUNT; i++) {
            cards.add(deck.draw());
        }

        Assertions.assertThat(cards).size().isEqualTo(52);
    }

    @Test
    @DisplayName("정해진 수 이상의 카드를 뽑을 경우 예외를 발생한다.")
    void handsSize() {
        final Cards cards = Cards.makeDecks();

        for (int i = 0; i < 52 * DECK_COUNT; i++) {
            cards.draw();
        }

        Assertions.assertThatCode(cards::draw).isInstanceOf(IllegalStateException.class);
    }
}
