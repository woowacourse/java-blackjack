package domain.card;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private static final int SINGLE_DECK_SIZE = 52;
    private static final int DECK_COUNT = 6;

    @Test
    @DisplayName("모든 카드를 뽑았을 때 서로 다른 카드의 개수는 52개다.")
    void draw() {
        final Deck deck = Deck.makeDecks();
        final Set<Card> cards = new HashSet<>();

        for (int i = 0; i < SINGLE_DECK_SIZE * DECK_COUNT; i++) {
            cards.add(deck.draw());
        }

        Assertions.assertThat(cards).size().isEqualTo(SINGLE_DECK_SIZE);
    }

    @Test
    @DisplayName("덱이 비어있을 때 카드를 뽑으면 예외가 발생한다.")
    void handsSize() {
        final Deck deck = new Deck(new LinkedList<>());

        Assertions.assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 모두 소진되어 더이상 카드를 뽑을 수 없습니다");
    }
}
