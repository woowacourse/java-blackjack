package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("처음 두장을 뽑는다.")
    void drawInitialHands() {
        Deck deck = new Deck();

        assertThat(deck.drawInitialHands()).hasSize(2);
    }
}
