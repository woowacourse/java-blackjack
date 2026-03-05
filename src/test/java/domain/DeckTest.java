package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("Deck를 생성할 때 오류 발생 안함")
    void deck_create_success() {
        Assertions.assertDoesNotThrow(
                Deck::createDeck
        );
    }
}