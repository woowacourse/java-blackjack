package blackjack.model;

import java.util.Deque;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("카드 덱을 생성한다.")
    void createDeck() {
        Deque<Card> deck = Deck.getDeck();
        Assertions.assertThat(deck).hasSize(52);
    }
}
