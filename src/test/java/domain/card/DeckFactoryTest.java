package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckFactoryTest {

    @DisplayName("52장보다 더 많은 카드를 뽑을 수 없다")
    @Test
    void createDeck() {
        Deck deck = DeckFactory.create();
        for (int i = 0; i < 52; i++) {
            assertThat(deck.draw()).isNotNull();
        }
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("남은 카드가 없습니다.");
    }
}
