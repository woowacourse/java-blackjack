package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckFactoryTest {

    @Test
    void createDeck() {
        assertThat(DeckFactory.createDeck()).isNotNull();
    }
}