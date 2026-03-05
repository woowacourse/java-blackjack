package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    @Test
    void 중복되지_않은_52장의_카드_묶음을_만든다() {
        Deck deck = new Deck();

        assertThat(deck.size()).isEqualTo(52);
    }
}
