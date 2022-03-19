package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱 카드가 모두 소진된 상황에서 뽑기를 시도할 경우 뽑을 수 없다는 예외 처리 확인")
    void drawTest() {
        Deck deck = Deck.create();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("덱이 더 이상 뽑을 수 있는 카드가 없습니다.");
    }
}
