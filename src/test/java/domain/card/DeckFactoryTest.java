package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckFactoryTest {

    @Test
    @DisplayName("생성 확인")
    void createDeck() {
        assertThat(DeckFactory.createDeck()).isNotNull();
    }
}