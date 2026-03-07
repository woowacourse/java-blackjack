package domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("카드 한 장 뽑아 반환하는 테스트")
    @Test
    void drawTest_notNull_ReturnCard() {
        Deck deck = new Deck();

        deck.init();

        assertNotNull(deck.draw());
    }

    @DisplayName("Deck이 비어있을 경우 예외 테스트")
    @Test
    void drawTest_deckIsEmpty_ThrowException() {
        Deck deck = new Deck();

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(NoSuchElementException.class);
    }
}
