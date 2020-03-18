package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckFactoryTest {

    @Test
    @DisplayName("생성 확인")
    void createDeck() {
        assertThat(DeckFactory.createDeck()).isNotNull();
    }
}