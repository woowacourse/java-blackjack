package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    @Test
    void 중복되지_않은_52장의_카드_묶음을_만든다() {
        Deck deck = Deck.of();
        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    void 덱이_비었을_때_예외를_발생한다() {
        Deck deck = Deck.of();
        int deckSize = deck.size();

        for (int i = 0; i < deckSize; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(Exception.class);
    }

    @Test
    void 덱에서_카드를_한_장_뽑으면_덱의_사이즈가_감소한다() {
        Deck deck = Deck.of();
        int size = deck.size();

        deck.draw();
        int changedSize = deck.size();

        assertThat(changedSize).isEqualTo(size - 1);
    }
}
