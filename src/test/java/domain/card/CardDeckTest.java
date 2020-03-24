package domain.card;

import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @DisplayName("최대 갯수 이상 드로우시 예외발생 테스트")
    @Test
    void cardDraw() {
        CardDeck cardDeck = new CardDeck();
        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }
        assertThatThrownBy(() -> cardDeck.draw())
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
