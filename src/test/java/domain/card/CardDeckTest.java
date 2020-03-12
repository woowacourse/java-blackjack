package domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    private CardDeck cardDeck;

    @BeforeEach
    void resetVariable() {
        cardDeck = new CardDeck();
    }

    @DisplayName("개수 이상 드로우시 예외발생")
    @Test
    void cardDraw() {
        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }
        assertThatThrownBy(cardDeck::draw).isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessageContaining("남아있지");
    }
}
