package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    private Deck deck;

    @Test
    @DisplayName("Deck 생성")
    void create() {
        assertThat(Deck.create()).isNotNull();
    }
}
