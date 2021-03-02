package blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @Test
    void DeckSize() {
        assertThat(Deck.getCards().size()).isEqualTo(52);
    }
}
