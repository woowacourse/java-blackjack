package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    Deck deck;

    @BeforeEach
    void beforeEach() {
        deck = new Deck();
    }

    @DisplayName("덱을 생성하면 52장이다")
    @Test
    void 덱을_생성하면_52장이다() {
        assertThat(deck.size()).isEqualTo(52);
    }

    @DisplayName("카드를 뽑으면 덱이 줄어든다")
    @Test
    void 카드를_뽑으면_덱이_줄어든다() {
        deck.draw();
        assertThat(deck.size()).isEqualTo(51);
    }

    @DisplayName("빈 덱에서 카드를 뽑으면 예외가 발생한다")
    @Test
    void 빈_덱에서_카드를_뽑으면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            for (int i = 0; i <= 52; i++) {
                deck.draw();
            }
        }).isInstanceOf(IllegalStateException.class);
    }
}
