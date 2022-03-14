package blackjack.domain;


import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @Test
    @DisplayName("48장의 카드를 모두 뽑은 뒤 더 뽑으려고 할 경우 에러가 발생한다.")
    void no_more_card() {
        Deck deck = new Deck();
        for (int i = 0; i < 48; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("더이상 뽑을 수 있는 카드가 없습니다.");
    }

}
