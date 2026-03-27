package domain.card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void 덱_생성_시_52장의_서로_다른_카드를_가져야_한다() {
        Deck deck = new Deck();

        assertDoesNotThrow(() -> {
            for (int i = 0; i < 52; i++) {
                deck.pull();
            }
        });
    }

    @Test
    void 뽑을_수_있는_카드가_존재하지_않으면_예외가_발생한다() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.pull();
        }

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> deck.pull())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
