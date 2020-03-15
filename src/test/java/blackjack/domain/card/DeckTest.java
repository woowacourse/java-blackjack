package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    public static final int SIZE_AFTER_FIRST_POP = new Deck().size() - 1;

    @DisplayName("pop()을 통해 카드 한 장을 뺴오는지")
    @Test
    void popTest() {
        Deck deck = new Deck();
        deck.pop();
        assertThat(deck.size()).isEqualTo(SIZE_AFTER_FIRST_POP);
    }

    @DisplayName("pop()을 할 수 없는 경우, 새 덱을 사용한다")
    @Test
    public void isEmptyTest() {
        Deck deck = new Deck();
        for (int i = 0, end = deck.size() + 1; i < end; i++) {
            deck.pop();
        }
        assertThat(deck.size()).isEqualTo(SIZE_AFTER_FIRST_POP);
    }
}
