package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱은 52장으로 구성된다.")
    void deck_size() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.distributeCard();
        }
        assertThatThrownBy(deck::distributeCard)
                .isInstanceOf(NoSuchElementException.class);
    }
}
