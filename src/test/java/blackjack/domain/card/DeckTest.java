package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("덱의 카드가 모두 떨어지면 카드를 더 뽑을 수 없다")
    @Test
    void emptyDeck() {
        Deck deck = new Deck(CardFactory.generateCards());
        assertThatThrownBy(() -> {
            for (int i = 1; i <= 53; i++) {
                deck.pop();
            }
        })
            .isInstanceOf(RuntimeException.class);
    }
}
