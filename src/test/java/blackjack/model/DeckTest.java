package blackjack.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("덱의 모든 카드 52장을 반환한다")
    void getDecks() {
        Assertions.assertThat(Deck.getAll()).hasSize(52);
    }
}
